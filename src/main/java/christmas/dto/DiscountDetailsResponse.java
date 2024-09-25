package christmas.dto;

import christmas.model.Discount;

public record DiscountDetailsResponse(Discount discount, boolean isEventUser) {
    public static DiscountDetailsResponse of(Discount discount, boolean isEventUser) {
        return new DiscountDetailsResponse(discount, isEventUser);
    }
}
