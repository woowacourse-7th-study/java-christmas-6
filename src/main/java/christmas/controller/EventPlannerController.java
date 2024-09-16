package christmas.controller;

import christmas.domain.VisitDate;
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
        inputVisitDate();
    }

    private void printEventPlannerGuide() { // 안내 문구 출력
        outputView.printHeaderNotice();
    }

    private void inputVisitDate() { // 방문 날짜를 입력 받는다.
        final ParserUtil parserUtil = new ParserUtil();
        boolean isValid = true;
        while(isValid){
            String input = inputView.inputVisitDate();
            visitDateDto = ParserUtil.parseVisitDate(input);
            isValid = false;
        }
    }
}
