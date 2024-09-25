package christmas.service;

import christmas.model.Giveaway;
import christmas.model.Order;

import static christmas.controller.ChristmasEventController.GIVEAWAY_THRESHOLD;

public class GiveawayService {
    private static GiveawayService instance;

    private GiveawayService() {
    }

    public static GiveawayService getInstance() {
        if (instance == null) {
            instance = new GiveawayService();
        }
        return instance;
    }

    public boolean getGiveawayStatus(Order order) {
        OrderService orderService = OrderService.getInstance();
        int priceBeforeDiscount = orderService.calculateTotalPriceBeforeDiscount(order);
        return priceBeforeDiscount > GIVEAWAY_THRESHOLD;
    }

    public Giveaway getGiveaway(Order order) {
        boolean giveawayStatus = getGiveawayStatus(order);
        return Giveaway.of(giveawayStatus);
    }
}
