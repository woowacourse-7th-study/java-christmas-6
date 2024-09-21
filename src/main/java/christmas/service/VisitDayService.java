package christmas.service;

import christmas.dto.VisitDayResponse;
import christmas.model.VisitDay;

public class VisitDayService {
    public VisitDayResponse createVisitDayResponse(VisitDay visitDay) {
        int day = visitDay.getVisitDay();
        return new VisitDayResponse(day);
    }
}
