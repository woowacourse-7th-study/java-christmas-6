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

        printDay(visitDay);
        printOrderInformation(order);
        printPriceBeforeDiscount(order);
        printGiveawayMenu(order);

        Discount discount = createDiscount(visitDay, order);

        printDiscountDetails(discount);
        printTotalDiscount(discount);
        printPayment(discount);
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
        OutputView.printTotalPriceBeforeDiscount(priceBeforeDiscountResponse);
    }

    private void printGiveawayMenu(Order order) {
        OutputView.printGiveawayMenuHeader();
        if (isGiveawayQualified(order)) {
            OutputView.printGiveawayMenu(Menu.CHAMPAGNE);
            return;
        }
        OutputView.printGiveawayMenu();
    }

    private void printDiscountDetails(Discount discount) {
        OutputView.printDiscountDetailsHeader();
        boolean isEventUser = isEventUser(discount.order);
        OutputView.printDiscountDetails(DiscountDetailsResponse.of(discount, isEventUser));
    }

    private void printTotalDiscount(Discount discount) {
        OutputView.printTotalDiscountHeader();
        int totalDiscount = discount.calculateTotalDiscount();
        OutputView.printTotalDiscount(TotalDiscountResponse.of(totalDiscount));
    }

    private void printPayment(Discount discount) {
        OutputView.printPaymentHeader();
        int payment = calculatePayment(discount);
        OutputView.printPayment(PaymentResponse.of(payment));
    }

    private void printBadge(Discount discount) {
        OutputView.printBadgeHeader();
        int totalDiscount = discount.calculateTotalDiscount();
        Badge badge = badgeService.calculateBadge(totalDiscount);
        OutputView.printBadge(BadgeResponse.of(badge));
    }

    private Discount createDiscount(VisitDay visitDay, Order order) {
        boolean giveaway = giveawayService.getGiveawayStatus(order);
        return Discount.of(visitDay, order, giveaway);
    }

    private boolean isEventUser(Order order) {
        int priceBeforeDiscount = orderService.calculateTotalPriceBeforeDiscount(order);
        return priceBeforeDiscount >= EVENT_THRESHOLD;
    }

    private boolean isGiveawayQualified(Order order) {
        return isEventUser(order) && giveawayService.getGiveawayStatus(order);
    }

    private int calculatePayment(Discount discount) {
        int priceBeforeDiscount = orderService.calculateTotalPriceBeforeDiscount(discount.order);
        int totalDiscount = discount.calculateTotalDiscountForPayment();
        return priceBeforeDiscount - totalDiscount;
    }
}
