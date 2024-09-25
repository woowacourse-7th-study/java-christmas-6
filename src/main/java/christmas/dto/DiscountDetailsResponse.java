package christmas.dto;

import christmas.model.Discount;
import christmas.model.Giveaway;

public record DiscountDetailsResponse(Discount discount, Giveaway giveaway, boolean isEventUser) {
    public static DiscountDetailsResponse of(Discount discount, Giveaway giveaway, boolean isEventUser) {
        return new DiscountDetailsResponse(discount, giveaway, isEventUser);
    }
}
