package christmas.controller;

import christmas.constants.Badge;
import christmas.constants.Menu;
import christmas.constants.error.type.UserInputException;
import christmas.converter.OrderConverter;
import christmas.converter.VisitDayConverter;
import christmas.dto.BadgeResponse;
import christmas.dto.DiscountDetailsResponse;
import christmas.dto.OrderResponse;
import christmas.dto.PaymentResponse;
import christmas.dto.PriceBeforeDiscountResponse;
import christmas.dto.TotalDiscountResponse;
import christmas.dto.VisitDayResponse;
import christmas.model.Discount;
import christmas.model.Giveaway;
import christmas.model.Order;
import christmas.model.VisitDay;
import christmas.service.BadgeService;
import christmas.service.GiveawayService;
import christmas.service.OrderService;
import christmas.validator.InputValidator;
import christmas.view.InputView;
import christmas.view.OutputView;

import java.util.Map;

public class ChristmasEventController {
    public static final int GIVEAWAY_THRESHOLD = 120_000;
    private static final int EVENT_THRESHOLD = 10_000;

    private final OrderService orderService;
    private final GiveawayService giveawayService;
    private final BadgeService badgeService;

    private boolean isEventUser = true;

    public ChristmasEventController(OrderService orderService,
                                    GiveawayService giveawayService,
                                    BadgeService badgeService) {
        this.orderService = orderService;
        this.giveawayService = giveawayService;
        this.badgeService = badgeService;
    }

    public void run() {
        printGreetingMessage();
        VisitDay visitDay = readVisitDay();
        Order order = readMenu();
        processEvent(visitDay, order);
    }

    private void processEvent(VisitDay visitDay, Order order) {
        printInputDataInformation(visitDay, order);
        printPriceBeforeDiscount(order);
        printGiveawayMenu(order);
        processDiscount(visitDay, order);
    }

    private void processDiscount(VisitDay visitDay, Order order) {
        boolean giveawayStatus = giveawayService.getGiveawayStatus(order);
        Giveaway giveaway = Giveaway.of(giveawayStatus);
        Discount discount = Discount.of(visitDay, order, giveaway);
        printDiscountDetails(discount, giveaway);
        printTotalDiscount(discount);
        processPayment(order, discount);
    }

    private void processPayment(Order order, Discount discount) {
        printPayment(order, discount);
        processBadge(discount);
    }

    private void processBadge(Discount discount) {
        printBadge(discount);
    }

    private void printInputDataInformation(VisitDay visitDay, Order order) {
        printDay(visitDay);
        printOrderInformation(order);
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
        OutputView.printDay(VisitDayResponse.of(visitDay));
    }

    private void printOrderInformation(Order order) {
        OutputView.printOrderHeader();
        OrderResponse orderResponse = orderService.createOrderResponse(order);
        OutputView.printOrderInformation(orderResponse);
    }

    private void printPriceBeforeDiscount(Order order) {
        OutputView.printTotalPriceBeforeDiscountHeader();
        PriceBeforeDiscountResponse priceBeforeDiscountResponse = orderService.createPriceBeforeDiscountResponse(order);
        int priceBeforeDiscount = orderService.calculateTotalPriceBeforeDiscount(order);
        if (priceBeforeDiscount < EVENT_THRESHOLD) {
            isEventUser = false;
        }
        OutputView.printTotalPriceBeforeDiscount(priceBeforeDiscountResponse);
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
        OutputView.printDiscountDetails(DiscountDetailsResponse.of(discount, giveaway, isEventUser));
    }

    private void printTotalDiscount(Discount discount) {
        OutputView.printTotalDiscountHeader();
        int totalDiscount = discount.calculateTotalDiscount();
        OutputView.printTotalDiscount(TotalDiscountResponse.of(totalDiscount));
    }

    private void printPayment(Order order, Discount discount) {
        OutputView.printPaymentHeader();
        int payment = calculatePayment(order, discount);
        OutputView.printPayment(PaymentResponse.of(payment));
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
        OutputView.printBadge(BadgeResponse.of(badge));
    }
}
