package christmas.util.format;

import static christmas.constants.Symbol.COUNT_STRING;
import static christmas.constants.Symbol.NEW_LINE;

import christmas.dto.OrdersDto;

public class OrdersFormatUtil {
    public static String formatOrdersList(OrdersDto ordersDto) {
        StringBuilder result = new StringBuilder();

        ordersDto.orders().getOrderProducts()
            .forEach(orderProduct ->
                result.append(orderProduct.getProductName())
                    .append(" ")
                    .append(orderProduct.getCount())
                    .append(COUNT_STRING)
                    .append(NEW_LINE)
            );

        return result.toString();
    }
}