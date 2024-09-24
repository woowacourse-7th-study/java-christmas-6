package christmas.service;

import christmas.dto.VisitDayResponse;
import christmas.model.VisitDay;

public class VisitDayService {
    private static VisitDayService instance;

    private VisitDayService() {
    }

    public static VisitDayService getInstance() {
        if (instance == null) {
            instance = new VisitDayService();
        }
        return instance;
    }

    public VisitDayResponse createVisitDayResponse(VisitDay visitDay) {
        int day = visitDay.getVisitDay();
        return new VisitDayResponse(day);
    }
}
