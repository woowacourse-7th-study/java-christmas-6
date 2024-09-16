package christmas.validator;

import static christmas.constants.exception.error.ErrorMessage.INVALID_DATE;

import christmas.constants.exception.InputException;

public class UserInputValidator {
    public static void validateStringToDate(String input) { // 문자열-> 숫자 검증 과정
        try {
            Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new InputException(INVALID_DATE);
        }
    }
}
