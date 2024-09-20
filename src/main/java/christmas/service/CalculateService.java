package christmas.service;

import christmas.dto.OrdersDto;

public class CalculateService {
    public int calculatePreTotalPrice(OrdersDto ordersDto) {
        return ordersDto.orders().calculatePreTotalPrice();
    }
}
