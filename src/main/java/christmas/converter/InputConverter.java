package christmas.converter;

import christmas.constants.error.type.UserInputException;

import java.util.LinkedHashMap;
import java.util.Map;

import static christmas.constants.Symbol.COMMA;
import static christmas.constants.Symbol.DASH;
import static christmas.constants.error.ErrorMessage.NOT_ALLOWED_ORDER;
import static christmas.constants.error.ErrorMessage.NOT_ALLOWED_OTHER_THAN_NUMERIC;

public class InputConverter {
    private InputConverter() {
    }

    public static int toInteger(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new UserInputException(NOT_ALLOWED_OTHER_THAN_NUMERIC);
        }
    }

    public static Map<String, Integer> toMap(String input) {
        Map<String, Integer> map = new LinkedHashMap<>();
        String[] split = input.split(COMMA);
        for (String piece : split) {
            String[] splitPiece = piece.split(DASH);
            String menu = splitPiece[0];
            int count = toInteger(splitPiece[1]);
            if (map.containsKey(menu)) {
                throw new UserInputException(NOT_ALLOWED_ORDER);
            }
            map.put(menu, count);
        }
        return map;
    }
}
