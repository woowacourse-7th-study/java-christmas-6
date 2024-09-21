package christmas.controller;

import christmas.constants.error.type.UserInputException;
import christmas.converter.Converter;
import christmas.model.Order;
import christmas.model.VisitDay;
import christmas.validator.Validator;
import christmas.view.InputView;
import christmas.view.OutputView;

import java.util.Map;

public class ChristmasEventController {
    public void run() {
        printGreetingMessage();
        VisitDay visitDay = readVisitDay();
        Order order = readMenu();
    }

    private void printGreetingMessage() {
        OutputView.printGreetingMessage();
    }

    private VisitDay readVisitDay() {
        while(true) {
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
        while(true) {
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
}
