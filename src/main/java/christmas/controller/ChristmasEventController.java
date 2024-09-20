package christmas.controller;

import christmas.converter.UserInputConverter;
import christmas.model.VisitDay;
import christmas.validator.UserInputValidator;
import christmas.view.InputView;
import christmas.view.OutputView;

public class ChristmasEventController {
    public void run() {
        printGreetingMessage();
        VisitDay visitDay = readVisitDay();
    }

    private void printGreetingMessage() {
        OutputView.printGreetingMessage();
    }

    private VisitDay readVisitDay() {
        String input = InputView.readVisitDay();
        UserInputValidator.validate(input);
        int visitDay = UserInputConverter.toInteger(input);
        return new VisitDay(visitDay);
    }
}
