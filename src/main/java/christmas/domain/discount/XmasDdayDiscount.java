package christmas.domain.discount;

import christmas.dto.VisitDateDto;

public class XmasDdayDiscount implements Discount{
    private static final int END_DAY = 25;
    private static final int DEFAULT_DISCOUNT_PRICE = 1000;
    private static final int DISCOUNT_PER_DAY_PRICE = 100;
    private static final String XMAS_DISCOUNT_STRING = "크리스마스 디데이 할인";

    @Override
    public int applyDiscount(VisitDateDto visitDateDto){
        int orderDay = visitDateDto.date();
        return getDiscountPrice(orderDay);
    }

    private int getDiscountPrice(int orderDay) {
        if (orderDay > END_DAY) {
            return 0;
        }
        int extraDiscount = (orderDay - 1) * DISCOUNT_PER_DAY_PRICE;
        return DEFAULT_DISCOUNT_PRICE + extraDiscount;
    }

    @Override
    public String getDiscountName() {
        return XMAS_DISCOUNT_STRING;
    }
}
