package christmas.model;

import christmas.constants.error.type.UserInputException;

import static christmas.constants.error.ErrorMessage.INVALID_VISIT_DAY_RANGE;

public class VisitDay {
    private static final int DAY_MIN = 1;
    private static final int DAY_MAX = 31;

    private final int visitDay;

    public VisitDay(int visitDay) {
        validate(visitDay);
        this.visitDay = visitDay;
    }

    private void validate(int visitDay) {
        validateRange(visitDay);
    }

    private void validateRange(int visitDay) {
        if (isInRange(visitDay)) {
            return;
        }
        throw new UserInputException(INVALID_VISIT_DAY_RANGE);
    }

    private boolean isInRange(int visitDay) {
        return DAY_MIN <= visitDay && visitDay <= DAY_MAX;
    }

    public int getVisitDay() {
        return visitDay;
    }
}
