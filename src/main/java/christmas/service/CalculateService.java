package christmas.service;

import static christmas.constants.Number.GIFT_THRESHOLD;

import christmas.domain.discount.Gift;
import christmas.dto.BenefitDto;
import christmas.dto.OrdersDto;

public class CalculateService {
    public BenefitDto calculateBenefits(OrdersDto ordersDto) { // 증정 품목 부여 여부 계산
        int preTotalPrice = calculatePreTotalPrice(ordersDto);
        boolean isGiftAvailable = preTotalPrice > GIFT_THRESHOLD;
        return new BenefitDto(preTotalPrice, new Gift(isGiftAvailable));
    }

    private int calculatePreTotalPrice(OrdersDto ordersDto) {
        return ordersDto.orders().calculatePreTotalPrice();
    }
}
