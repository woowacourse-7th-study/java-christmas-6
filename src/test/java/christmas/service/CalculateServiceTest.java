package christmas.service;

import christmas.domain.Orders;
import christmas.dto.BenefitDto;
import christmas.dto.OrdersDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class CalculateServiceTest {

    private CalculateService calculateService;

    @BeforeEach
    void setUp() {
        calculateService = new CalculateService();
    }

    @Test
    @DisplayName("주문 금액이 120,000원을 넘을 경우 샴페인이 증정된다.")
    void returnsChampagneOverThreshold() {
        // given
        Orders orders = mock(Orders.class);
        when(orders.calculatePreTotalPrice()).thenReturn(130000);
        OrdersDto ordersDto = new OrdersDto(orders);

        // when
        BenefitDto benefitDto = calculateService.calculateBenefits(ordersDto);

        // then
        assertThat(benefitDto.getGiftMessage()).isEqualTo("샴페인 1개");
    }

    @Test
    @DisplayName("주문 금액이 120,000원 이하일 경우 증정 품목이 없다.")
    void returnsNullUnderThreshold() {
        // given
        Orders orders = mock(Orders.class);
        when(orders.calculatePreTotalPrice()).thenReturn(100000);
        OrdersDto ordersDto = new OrdersDto(orders);

        // when
        BenefitDto benefitDto = calculateService.calculateBenefits(ordersDto);

        // then
        assertThat(benefitDto.getGiftMessage()).isEqualTo("없음");
    }
}
