package christmas.controller;

import christmas.constants.error.type.UserInputException;
import christmas.converter.Converter;
import christmas.dto.OrderResponse;
import christmas.dto.VisitDayResponse;
import christmas.model.Order;
import christmas.model.VisitDay;
import christmas.service.OrderService;
import christmas.service.VisitDayService;
import christmas.validator.Validator;
import christmas.view.InputView;
import christmas.view.OutputView;

import java.util.Map;

public class ChristmasEventController {
    public void run() {
        printGreetingMessage();
        VisitDay visitDay = readVisitDay();
        Order order = readMenu();
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
                Validator.validateVisitDay(input);
                int visitDay = Converter.toInteger(input);
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
                Validator.validateMenu(input);
                Map<String, Integer> menu = Converter.toMap(input);
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
}
