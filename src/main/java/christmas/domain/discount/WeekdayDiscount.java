package christmas.domain.discount;

import static christmas.constants.Number.EVENT_MONTH;
import static christmas.constants.Number.EVENT_YEAR;

import christmas.domain.Orders;
import christmas.domain.vo.MenuType;
import java.time.DayOfWeek;
import java.time.LocalDate;

public class WeekdayDiscount implements Discount {

    private static final int WEEKDAY_DISCOUNT_PRICE = 2023;
    private static final String WEEKDAY_DISCOUNT_STRING = "평일 할인";

    @Override
    public int applyDiscount(Orders orders) {
        int orderDay = orders.getDate();
        if (isWeekend(orderDay)) {
            return 0;
        }
        return getDiscountPrice(orders);
    }

    @Override
    public String getDiscountName() {
        return WEEKDAY_DISCOUNT_STRING;
    }

    public boolean isWeekend(int orderDay) {
        LocalDate date = LocalDate.of(EVENT_YEAR, EVENT_MONTH, orderDay);
        DayOfWeek dayOfWeek = date.getDayOfWeek();

        return (dayOfWeek == DayOfWeek.FRIDAY || dayOfWeek == DayOfWeek.SATURDAY);
    }

    private int getDiscountPrice(Orders orders) {
        int desertMenuCount = orders.findCountMenuType(MenuType.DESSERT);
        return desertMenuCount * WEEKDAY_DISCOUNT_PRICE;
    }
}
