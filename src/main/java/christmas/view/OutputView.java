package christmas.view;

import static christmas.constants.ViewMessage.EVENT_PLANNER_NOTICE;

public class OutputView {
    public void printHeaderNotice() {
        System.out.println(EVENT_PLANNER_NOTICE);
    }

    public void printResult(String result) {
        System.out.println(result);
    }
}
