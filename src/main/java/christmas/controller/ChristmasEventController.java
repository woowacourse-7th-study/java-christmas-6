package christmas.controller;

import christmas.view.OutputView;

public class ChristmasEventController {
    public void run() {
        printGreetingMessage();
    }

    private void printGreetingMessage() {
        OutputView.printGreetingMessage();
    }
}
