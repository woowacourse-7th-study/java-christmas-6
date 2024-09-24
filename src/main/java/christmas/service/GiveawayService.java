package christmas.service;

import christmas.model.Order;

import static christmas.controller.ChristmasEventController.SPENDING_THRESHOLD;

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
        return priceBeforeDiscount > SPENDING_THRESHOLD;
    }
}
