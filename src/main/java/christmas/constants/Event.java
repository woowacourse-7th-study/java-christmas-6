package christmas.constants;

public enum Event {
    XMAS("크리스마스 디데이 할인"),
    WEEKDAYS("평일 할인"),
    WEEKEND("주말 할인"),
    SPECIAL("특별 할인"),
    GIVEAWAY("증정 이벤트");

    private final String type;

    Event(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return type;
    }
}
