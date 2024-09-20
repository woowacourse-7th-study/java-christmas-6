package christmas.validator;

import christmas.constants.error.type.UserInputException;

import static christmas.constants.error.ErrorMessage.NOT_ALLOWED_JUST_BLANK;
import static christmas.constants.error.ErrorMessage.NOT_ALLOWED_STRIP;

public class UserInputValidator {
    private UserInputValidator() {
    }

    public static void validate(String input) {
        validateBlank(input);
        validateStrip(input);
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
}
