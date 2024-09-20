package christmas.view;

import camp.nextstep.edu.missionutils.Console;

import static christmas.constants.ViewMessage.READ_VISIT_DAY;

public class InputView {
    private InputView() {
    }

    public static String readVisitDay() {
        System.out.println(READ_VISIT_DAY);
        return Console.readLine();
    }
}
