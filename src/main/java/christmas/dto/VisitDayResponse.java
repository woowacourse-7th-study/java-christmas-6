package christmas.dto;

import christmas.model.VisitDay;

public record VisitDayResponse(int day) {
    public static VisitDayResponse of(VisitDay visitDay) {
        return new VisitDayResponse(visitDay.getVisitDay());
    }
}
