package christmas.constants.error;

public class ErrorMessage {
    private ErrorMessage() {
    }

    public static final String INVALID_VISIT_DAY_RANGE = "유효하지 않은 날짜입니다. 다시 입력해 주세요.";
    public static final String NOT_ALLOWED_STRIP = "맨 앞, 맨 뒤에 공백이 포함되지 않아야 합니다.";
    public static final String NOT_ALLOWED_JUST_BLANK = "공백만 입력하지 않아야 합니다.";
    public static final String NOT_ALLOWED_OTHER_THAN_NUMERIC = "정수를 입력해야 합니다.";
    public static final String NOT_ALLOWED_ORDER = "유효하지 않은 주문입니다. 다시 입력해 주세요";
}
