package christmas.converter;

import christmas.constants.error.type.UserInputException;

import static christmas.constants.error.ErrorMessage.INVALID_VISIT_DAY;

public class VisitDayConverter {
    private VisitDayConverter() {
    }

    public static int toInteger(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new UserInputException(INVALID_VISIT_DAY);
        }
    }
}
