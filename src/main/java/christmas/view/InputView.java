package christmas.view;

import static christmas.constants.ViewMessage.REQUEST_VISIT_DATE;

import camp.nextstep.edu.missionutils.Console;

public class InputView {
    public String inputVisitDate() {
        System.out.println(REQUEST_VISIT_DATE);
        return requestInput();
    }

    private String requestInput() {
        return Console.readLine();
    }
}
