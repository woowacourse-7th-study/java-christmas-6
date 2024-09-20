package christmas.constants.error.type;

public class UserInputException extends IllegalArgumentException {
    public static final String PREFIX = "[ERROR] ";

    public UserInputException(String errorMessage) {
        super(PREFIX + errorMessage);
    }
}
