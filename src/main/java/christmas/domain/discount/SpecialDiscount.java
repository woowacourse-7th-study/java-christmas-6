package christmas.domain.discount;

import christmas.domain.Orders;
import java.util.List;

public class SpecialDiscount implements Discount{
    private static final String SPECIAL_DISCOUNT_STRING = "특별 할인";
    private static final int SPECIAL_DISCOUNT_PRICE = 1000;
    private static final List<Integer> SPECIAL = List.of(3, 10, 17, 24, 25, 31);
    @Override
    public int applyDiscount(Orders orders) {
        int orderDay = orders.getDate();
        if (isSpecial(orderDay)) {
            return getDiscountPrice();
        }
        return 0;
    }

    @Override
    public String getDiscountName() {
        return SPECIAL_DISCOUNT_STRING;
    }

    private boolean isSpecial(int orderDay){
        return SPECIAL.contains(orderDay);
    }

    private int getDiscountPrice(){
        return SPECIAL_DISCOUNT_PRICE;
    }
}
