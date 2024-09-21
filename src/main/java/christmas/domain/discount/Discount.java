package christmas.domain.discount;

import christmas.domain.Orders;
import christmas.dto.VisitDateDto;

public interface Discount {
    int applyDiscount(Orders orders);
    String getDiscountName();
}
