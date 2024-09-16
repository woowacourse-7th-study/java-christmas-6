package christmas.controller;

import christmas.view.InputView;
import christmas.view.OutputView;

public class EventPlannerController {

    private final InputView inputView;
    private final OutputView outputView;

    public EventPlannerController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        inputVisitDate(); // 방문 날짜를 입력 받는다.
    }

    private void inputVisitDate() {
        outputView.printHeaderNotice();
    }
}
