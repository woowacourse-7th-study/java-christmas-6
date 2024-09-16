package christmas.dto;

import christmas.domain.OrderProduct;
import java.util.List;

public record OrderDto(List<OrderProduct> orderProducts) {
}
