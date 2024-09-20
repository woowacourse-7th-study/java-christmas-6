package christmas.domain.discount;

import christmas.dto.VisitDateDto;

public interface Discount {
    int applyDiscount(VisitDateDto visitDateDto);
    String getDiscountName();
}
