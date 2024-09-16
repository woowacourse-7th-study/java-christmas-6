package christmas.controller;

import christmas.constants.exception.InputException;
import christmas.dto.VisitDateDto;
import christmas.util.ParserUtil;
import christmas.view.InputView;
import christmas.view.OutputView;

public class EventPlannerController {
    private final InputView inputView;
    private final OutputView outputView;

    private VisitDateDto visitDateDto;

    public EventPlannerController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        printEventPlannerGuide();
        runUntilNoException(inputVisitDate());
    }

    private void runUntilNoException(Runnable runnable) {
        while (true) {
            try {
                runnable.run();
                break;
            } catch (InputException e) {
                outputView.printResult(e.getMessage());  // 예외 메시지 출력
            }
        }
    }

    private void printEventPlannerGuide() { // 안내 문구 출력
        outputView.printHeaderNotice();
    }

    private Runnable inputVisitDate() { // 방문 날짜를 입력 받는다.
        return () -> {
            String input = inputView.inputVisitDate();
            visitDateDto = ParserUtil.parseVisitDate(input);
        };
    }
}
