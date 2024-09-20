package christmas.dto;

import christmas.domain.discount.Gift;

public record BenefitDto(int preTotalPrice, Gift gift) {
    public String getGiftMessage() {
        return gift.getGiftMessage();
    }
}