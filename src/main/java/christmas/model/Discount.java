package christmas.model;

import christmas.constants.Menu;
import christmas.constants.MenuType;
import christmas.constants.error.type.UserInputException;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;

import static christmas.constants.error.ErrorMessage.NOT_ALLOWED_ORDER;

public class Discount {
    private static final int XMAS_INITIAL_DISCOUNT = 1_000;
    private static final int XMAS_DISCOUNT = 100;
    private static final int D_DAY = 25;
    private static final int SPECIAL_DISCOUNT = 1_000;
    private static final int DAY_DISCOUNT = 2023;

    private final VisitDay visitDay;
    private final Order order;
    private final Set<Integer> weekend = Set.of(1, 2, 8, 9, 15, 16, 22, 23, 29, 30);

    public Giveaway giveaway;

    public Discount(VisitDay visitDay, Order order, Giveaway giveaway) {
        this.visitDay = visitDay;
        this.order = order;
        this.giveaway = giveaway;
    }

    public static Discount of(VisitDay visitDay, Order order, Giveaway giveaway) {
        return new Discount(visitDay, order, giveaway);
    }

    public int calculateXmasDiscount() {
        int xmasDiscount = XMAS_INITIAL_DISCOUNT;
        int day = visitDay.getVisitDay();

        for (int i = 1; i < day; i++) {
            xmasDiscount += XMAS_DISCOUNT;
        }

        if (day > D_DAY) {
            xmasDiscount = 0;
        }

        return xmasDiscount;
    }

    public int calculateWeekendDiscount() {
        int weekendDiscount = 0;
        if (weekend.contains(visitDay.getVisitDay())) {
            for (Map.Entry<String, Integer> entry : order.getItems().entrySet()) {
                String orderName = entry.getKey();
                int quantity = entry.getValue();

                Menu selectedMenu = Arrays.stream(Menu.values())
                        .filter(menu -> menu.getName().equals(orderName))
                        .findFirst()
                        .orElseThrow(() -> new UserInputException(NOT_ALLOWED_ORDER));

                if (selectedMenu.getType().equals(MenuType.MAIN)) {
                    weekendDiscount += DAY_DISCOUNT * quantity;
                }
            }
        }
        return weekendDiscount;
    }

    public int calculateWeekdaysDiscount() {
        int weekdaysDiscount = 0;
        if (!weekend.contains(visitDay.getVisitDay())) {
            for (Map.Entry<String, Integer> entry : order.getItems().entrySet()) {
                String orderName = entry.getKey();
                int quantity = entry.getValue();

                Menu selectedMenu = Arrays.stream(Menu.values())
                        .filter(menu -> menu.getName().equals(orderName))
                        .findFirst()
                        .orElseThrow(() -> new UserInputException(NOT_ALLOWED_ORDER));

                if (selectedMenu.getType().equals(MenuType.DESSERT)) {
                    weekdaysDiscount += DAY_DISCOUNT * quantity;
                }
            }
        }
        return weekdaysDiscount;
    }

    public int calculateSpecialDiscount() {
        int specialDiscount = 0;
        Set<Integer> specialDays = Set.of(3, 10, 17, 24, 25, 31);
        if (specialDays.contains(visitDay.getVisitDay())) {
            specialDiscount = SPECIAL_DISCOUNT;
        }
        return specialDiscount;
    }

    public int calculateTotalDiscount() {
        int totalDiscount = calculateTotalDiscountForPayment();
        totalDiscount += calculateGiveawayDiscount();
        return totalDiscount;
    }

    public int calculateTotalDiscountForPayment() {
        int totalDiscount = 0;
        totalDiscount += calculateXmasDiscount();
        totalDiscount += calculateWeekdaysDiscount();
        totalDiscount += calculateWeekendDiscount();
        totalDiscount += calculateSpecialDiscount();
        return totalDiscount;
    }

    public int calculateGiveawayDiscount() {
        int giveawayDiscount = 0;
        if (giveaway.getGiveawayStatus()) {
            giveawayDiscount = Menu.CHAMPAGNE.getPrice();
        }
        return giveawayDiscount;
    }
}
