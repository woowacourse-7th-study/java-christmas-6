package christmas.service;

import christmas.domain.discount.Gift;
import christmas.dto.BenefitDto;

public class ConvertService {
    public String priceToGift(BenefitDto benefitDto) { // TODO : 메서드명의 역할을 제대로 수행하지 못함 , CalculateService에서 사용되는 메서드와 책임 분담 필요
        return benefitDto.getGiftMessage();
    }
}
