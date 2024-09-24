package christmas.controller;

import christmas.constants.Badge;
import christmas.constants.Menu;
import christmas.constants.error.type.UserInputException;
import christmas.converter.InputConverter;
import christmas.dto.OrderResponse;
import christmas.dto.PriceBeforeDiscountResponse;
import christmas.dto.VisitDayResponse;
import christmas.model.Discount;
import christmas.model.Order;
import christmas.model.VisitDay;
import christmas.service.BadgeService;
import christmas.service.OrderService;
import christmas.service.VisitDayService;
import christmas.validator.InputValidator;
import christmas.view.InputView;
import christmas.view.OutputView;

import java.util.Map;

public class ChristmasEventController {
    private static final int SPENDING_THRESHOLD = 120_000;

    private boolean giveaway = false;

    public void run() {
        printGreetingMessage();
        VisitDay visitDay = readVisitDay();
        Order order = readMenu();
        printDay(visitDay);
        printOrderInformation(order);
        PriceBeforeDiscountResponse priceBeforeDiscountResponse = printPriceBeforeDiscount(order);
        printGiveawayMenu(priceBeforeDiscountResponse);
        Discount discount = printDiscountDetails(visitDay, order);
        int totalDiscount = printTotalDiscount(discount);
        printPayment(order, discount);
        printBadge(totalDiscount);
    }

    private void printGreetingMessage() {
        OutputView.printGreetingMessage();
    }

    private VisitDay readVisitDay() {
        while (true) {
            try {
                String input = InputView.readVisitDay();
                InputValidator.validateVisitDay(input);
                int visitDay = InputConverter.toInteger(input);
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
                Map<String, Integer> menu = InputConverter.toMap(input);
                return new Order(menu);
            } catch (UserInputException e) {
                OutputView.printErrorMessage(e.getMessage());
            }
        }
    }

    private void printDay(VisitDay visitDay) {
        VisitDayService visitDayService = VisitDayService.getInstance();
        VisitDayResponse visitDayResponse = visitDayService.createVisitDayResponse(visitDay);
        OutputView.printDay(visitDayResponse);
    }

    private void printOrderInformation(Order order) {
        OutputView.printOrderHeader();
        OrderService orderService = OrderService.getInstance();
        OrderResponse orderResponse = orderService.createOrderResponse(order);
        OutputView.printOrderInformation(orderResponse);
    }

    private PriceBeforeDiscountResponse printPriceBeforeDiscount(Order order) {
        OutputView.printTotalPriceBeforeDiscountHeader();
        OrderService orderService = OrderService.getInstance();
        int priceBeforeDiscount = orderService.calculateTotalPriceBeforeDiscount(order);
        PriceBeforeDiscountResponse priceBeforeDiscountResponse = new PriceBeforeDiscountResponse(priceBeforeDiscount);
        OutputView.printTotalPriceBeforeDiscount(priceBeforeDiscountResponse);
        return priceBeforeDiscountResponse;
    }

    private void printGiveawayMenu(PriceBeforeDiscountResponse priceBeforeDiscountResponse) {
        OutputView.printGiveawayMenuHeader();
        giveaway = isQualifiedForGiveaway(priceBeforeDiscountResponse);

        if (giveaway) {
            OutputView.printGiveawayMenu(Menu.CHAMPAGNE);
            return;
        }
        OutputView.printGiveawayMenu();
    }

    private boolean isQualifiedForGiveaway(PriceBeforeDiscountResponse priceBeforeDiscountResponse) {
        return priceBeforeDiscountResponse.priceBeforeDiscount() > SPENDING_THRESHOLD;
    }

    private Discount printDiscountDetails(VisitDay visitDay, Order order) {
        OutputView.printDiscountDetailsHeader();
        Discount discount = new Discount(visitDay, order, giveaway);
        OutputView.printDiscountDetails(discount, giveaway);
        return discount;
    }

    private int printTotalDiscount(Discount discount) {
        OutputView.printTotalDiscountHeader();
        int totalDiscount = discount.calculateTotalDiscount();
        OutputView.printTotalDiscount(totalDiscount);
        return totalDiscount;
    }

    private void printPayment(Order order, Discount discount) {
        OutputView.printPaymentHeader();
        OrderService orderService = OrderService.getInstance();
        int priceBeforeDiscount = orderService.calculateTotalPriceBeforeDiscount(order);
        int totalDiscount = discount.calculateTotalDiscount();
        int payment = priceBeforeDiscount - totalDiscount;
        OutputView.printPayment(payment);
    }

    private void printBadge(int totalDiscount) {
        OutputView.printBadgeHeader();
        BadgeService badgeService = BadgeService.getInstance();
        Badge badge = badgeService.calculateBadge(totalDiscount);
        OutputView.printBadge(badge);
    }
}
