package christmas.controller;

import christmas.constants.Badge;
import christmas.constants.Menu;
import christmas.constants.error.type.UserInputException;
import christmas.converter.OrderConverter;
import christmas.converter.VisitDayConverter;
import christmas.dto.OrderResponse;
import christmas.dto.VisitDayResponse;
import christmas.model.Discount;
import christmas.model.Giveaway;
import christmas.model.Order;
import christmas.model.VisitDay;
import christmas.service.BadgeService;
import christmas.service.GiveawayService;
import christmas.service.OrderService;
import christmas.service.VisitDayService;
import christmas.validator.InputValidator;
import christmas.view.InputView;
import christmas.view.OutputView;

import java.util.Map;

public class ChristmasEventController {
    public static final int SPENDING_THRESHOLD = 120_000;
    private static final int EVENT_THRESHOLD = 10_000;

    private final VisitDayService visitDayService;
    private final OrderService orderService;
    private final GiveawayService giveawayService;
    private final BadgeService badgeService;

    private boolean isEventUser = true;

    public ChristmasEventController(VisitDayService visitDayService,
                                    OrderService orderService,
                                    GiveawayService giveawayService,
                                    BadgeService badgeService) {
        this.visitDayService = visitDayService;
        this.orderService = orderService;
        this.giveawayService = giveawayService;
        this.badgeService = badgeService;
    }

    public void run() {
        printGreetingMessage();
        VisitDay visitDay = readVisitDay();
        Order order = readMenu();
        printDay(visitDay);
        printOrderInformation(order);
        printPriceBeforeDiscount(order);
        printGiveawayMenu(order);
        boolean giveawayStatus = giveawayService.getGiveawayStatus(order);
        Giveaway giveaway = Giveaway.of(giveawayStatus);
        Discount discount = Discount.of(visitDay, order, giveaway);
        printDiscountDetails(discount, giveaway);
        printTotalDiscount(discount);
        printPayment(order, discount);
        printBadge(discount);
    }

    private void printGreetingMessage() {
        OutputView.printGreetingMessage();
    }

    private VisitDay readVisitDay() {
        while (true) {
            try {
                String input = InputView.readVisitDay();
                InputValidator.validateVisitDay(input);
                int visitDay = VisitDayConverter.toInteger(input);
                return new VisitDay(visitDay);
            } catch (UserInputException e) {
                OutputView.printErrorMessage(e.getMessage());
            }
        }
    }

    private Order readMenu() {
        while (true) {
            try {
                String input = InputView.readMenu();
                InputValidator.validateMenu(input);
                Map<String, Integer> menu = OrderConverter.toMap(input);
                return new Order(menu);
            } catch (UserInputException e) {
                OutputView.printErrorMessage(e.getMessage());
            }
        }
    }

    private void printDay(VisitDay visitDay) {
        VisitDayResponse visitDayResponse = visitDayService.createVisitDayResponse(visitDay);
        OutputView.printDay(visitDayResponse);
    }

    private void printOrderInformation(Order order) {
        OutputView.printOrderHeader();
        OrderResponse orderResponse = orderService.createOrderResponse(order);
        OutputView.printOrderInformation(orderResponse);
    }

    private void printPriceBeforeDiscount(Order order) {
        OutputView.printTotalPriceBeforeDiscountHeader();
        int priceBeforeDiscount = orderService.calculateTotalPriceBeforeDiscount(order);
        if (priceBeforeDiscount < EVENT_THRESHOLD) {
            isEventUser = false;
        }
        OutputView.printTotalPriceBeforeDiscount(priceBeforeDiscount);
    }

    private void printGiveawayMenu(Order order) {
        OutputView.printGiveawayMenuHeader();
        if (isEventUser && giveawayService.getGiveawayStatus(order)) {
            OutputView.printGiveawayMenu(Menu.CHAMPAGNE);
            return;
        }
        OutputView.printGiveawayMenu();
    }

    private void printDiscountDetails(Discount discount, Giveaway giveaway) {
        OutputView.printDiscountDetailsHeader();
        OutputView.printDiscountDetails(discount, giveaway, isEventUser);
    }

    private void printTotalDiscount(Discount discount) {
        OutputView.printTotalDiscountHeader();
        int totalDiscount = discount.calculateTotalDiscount();
        OutputView.printTotalDiscount(totalDiscount);
    }

    private void printPayment(Order order, Discount discount) {
        OutputView.printPaymentHeader();
        int payment = calculatePayment(order, discount);
        OutputView.printPayment(payment);
    }

    private int calculatePayment(Order order, Discount discount) {
        int priceBeforeDiscount = orderService.calculateTotalPriceBeforeDiscount(order);
        int totalDiscount = discount.calculateTotalDiscountForPayment();
        return priceBeforeDiscount - totalDiscount;
    }

    private void printBadge(Discount discount) {
        OutputView.printBadgeHeader();
        int totalDiscount = discount.calculateTotalDiscount();
        Badge badge = badgeService.calculateBadge(totalDiscount);
        OutputView.printBadge(badge);
    }
}
