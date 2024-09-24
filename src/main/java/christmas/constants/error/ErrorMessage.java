package christmas.constants.error;

public class ErrorMessage {
    public static final String INVALID_VISIT_DAY = "유효하지 않은 날짜입니다. 다시 입력해 주세요.";
    public static final String NOT_ALLOWED_FIRST_LAST_BLANK = "맨 앞, 맨 뒤에 공백이 포함되지 않아야 합니다.";
    public static final String NOT_ALLOWED_JUST_BLANK = "공백만 입력하지 않아야 합니다.";
    public static final String NOT_ALLOWED_ORDER = "유효하지 않은 주문입니다. 다시 입력해 주세요.";
    public static final String TOO_MANY_ORDERS = "메뉴는 한 번에 최대 20개까지만 주문할 수 있습니다.";
    public static final String NOT_ALLOWED_ONLY_DRINK = "음료만 주문 시, 주문할 수 없습니다.";

    private ErrorMessage() {
    }
}
