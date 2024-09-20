package christmas.view;

import static christmas.constants.ViewMessage.GREETING_MESSAGE;

public class OutputView {
    private OutputView() {
    }

    public static void printGreetingMessage() {
        System.out.println(GREETING_MESSAGE);
    }
}
