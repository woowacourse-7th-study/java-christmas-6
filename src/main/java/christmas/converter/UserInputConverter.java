package christmas.converter;

import christmas.constants.error.type.UserInputException;

import static christmas.constants.error.ErrorMessage.NOT_ALLOWED_OTHER_THAN_NUMERIC;

public class UserInputConverter {
    private UserInputConverter() {
    }

    public static int toInteger(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new UserInputException(NOT_ALLOWED_OTHER_THAN_NUMERIC);
        }
    }
}
