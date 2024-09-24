package christmas;

import christmas.controller.ChristmasEventController;
import christmas.service.BadgeService;
import christmas.service.GiveawayService;
import christmas.service.OrderService;
import christmas.service.VisitDayService;

public class Application {
    public static void main(String[] args) {
        ChristmasEventController christmasEventController = new ChristmasEventController(
                VisitDayService.getInstance(),
                OrderService.getInstance(),
                GiveawayService.getInstance(),
                BadgeService.getInstance()
        );
        christmasEventController.run();
    }
}
