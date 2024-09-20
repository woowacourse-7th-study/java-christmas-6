package christmas.model;

import christmas.constants.error.type.UserInputException;

import static christmas.constants.error.ErrorMessage.INVALID_VISIT_DATE_RANGE;

public class VisitDay {
    private int visitDay;

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
        throw new UserInputException(INVALID_VISIT_DATE_RANGE);
    }

    private boolean isInRange(int visitDate) {
        return 1 <= visitDate && visitDate <= 31;
    }
}
