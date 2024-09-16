package christmas.constants.exception.error;

public enum ErrorMessage {
    INVALID_DATE("유효하지 않은 날짜입니다. 다시 입력해 주세요.");

    private static final String PREFIX = "[ERROR] "; // PREFIX는 항상 같은 값을 가짐
    private final String message;

    private ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return PREFIX + message;
    }
}
