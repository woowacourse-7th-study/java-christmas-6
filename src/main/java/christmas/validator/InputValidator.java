package christmas.validator;

import christmas.constants.error.type.UserInputException;

import static christmas.constants.Symbol.DASH;
import static christmas.constants.error.ErrorMessage.NOT_ALLOWED_JUST_BLANK;
import static christmas.constants.error.ErrorMessage.NOT_ALLOWED_ORDER;
import static christmas.constants.error.ErrorMessage.NOT_ALLOWED_STRIP;

public class InputValidator {
    private InputValidator() {
    }

    public static void validateVisitDay(String input) {
        validateBlank(input);
        validateStrip(input);
    }

    public static void validateMenu(String input) {
        validateBlank(input);
        validateStrip(input);
        validateDash(input);
    }

    private static void validateBlank(String input) {
        if (input.isBlank()) {
            throw new UserInputException(NOT_ALLOWED_JUST_BLANK);
        }
    }

    private static void validateStrip(String input) {
        String stripped = input.strip();
        if (input.equals(stripped)) {
            return;
        }
        throw new UserInputException(NOT_ALLOWED_STRIP);
    }

    private static void validateDash(String input) {
        if (input.contains(DASH)) {
            return;
        }
        throw new UserInputException(NOT_ALLOWED_ORDER);
    }
}
