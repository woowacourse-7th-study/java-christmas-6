package christmas.view;

import static christmas.constants.ViewMessage.ANNOUNCE_EVENT_BENEFITS;
import static christmas.constants.ViewMessage.EVENT_PLANNER_NOTICE;

public class OutputView {
    public void printHeaderNotice() {
        System.out.println(EVENT_PLANNER_NOTICE);
    }

    public void printHeaderEventBenefits(){
        System.out.println(ANNOUNCE_EVENT_BENEFITS);
        printWhiteSpace();
    }
    public void printResult(String result) {
        System.out.println(result);
    }

    private static void printWhiteSpace() {
        System.out.println();
    }
}
