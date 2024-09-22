package christmas.controller;

import christmas.constants.Menu;
import christmas.constants.error.type.UserInputException;
import christmas.converter.InputConverter;
import christmas.dto.OrderResponse;
import christmas.dto.PriceBeforeDiscountResponse;
import christmas.dto.VisitDayResponse;
import christmas.model.Order;
import christmas.model.VisitDay;
import christmas.service.OrderService;
import christmas.service.VisitDayService;
import christmas.validator.InputValidator;
import christmas.view.InputView;
import christmas.view.OutputView;

import java.util.Map;

public class ChristmasEventController {
    private static final int SPENDING_THRESHOLD = 120000;

    public void run() {
        printGreetingMessage();
        VisitDay visitDay = readVisitDay();
        Order order = readMenu();
        printDay(visitDay);
        printOrderInformation(order);
        PriceBeforeDiscountResponse priceBeforeDiscountResponse = printTotalPriceBeforeDiscount(order);
        printGiveawayMenu(priceBeforeDiscountResponse);
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
        VisitDayService visitDayService = new VisitDayService();
        VisitDayResponse visitDayResponse = visitDayService.createVisitDayResponse(visitDay);
        OutputView.printDay(visitDayResponse);
    }

    private void printOrderInformation(Order order) {
        OutputView.printOrderHeader();
        OrderService orderService = new OrderService();
        OrderResponse orderResponse = orderService.createOrderResponse(order);
        OutputView.printOrderInformation(orderResponse);
    }

    private PriceBeforeDiscountResponse printTotalPriceBeforeDiscount(Order order) {
        OutputView.printTotalPriceBeforeDiscountHeader();
        OrderService orderService = new OrderService();
        PriceBeforeDiscountResponse priceBeforeDiscountResponse = orderService.calculateTotalPriceBeforeDiscount(order);
        OutputView.printTotalPriceBeforeDiscount(priceBeforeDiscountResponse);
        return priceBeforeDiscountResponse;
    }

    private void printGiveawayMenu(PriceBeforeDiscountResponse priceBeforeDiscountResponse) {
        OutputView.printGiveawayMenuHeader();
        if (isQualifiedForGiveaway(priceBeforeDiscountResponse)) {
            OutputView.printGiveawayMenu(Menu.CHAMPAGNE);
            return;
        }
        OutputView.printGiveawayMenu();
    }

    private boolean isQualifiedForGiveaway(PriceBeforeDiscountResponse priceBeforeDiscountResponse) {
        return priceBeforeDiscountResponse.priceBeforeDiscount() > SPENDING_THRESHOLD;
    }
}
