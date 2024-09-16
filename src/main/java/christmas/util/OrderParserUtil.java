package christmas.util;

import static christmas.constants.Symbol.COMMA;
import static christmas.constants.Symbol.HYPHEN;

import christmas.domain.OrderProduct;
import christmas.domain.Orders;
import christmas.domain.vo.Product;
import christmas.dto.OrdersDto;
import christmas.validator.UserInputValidator;
import java.util.ArrayList;
import java.util.List;

public class OrderParserUtil {
    public static OrdersDto parseOrder(String input) {
        UserInputValidator.validateOrders(input);

        List<OrderProduct> orderProducts = new ArrayList<>();
        List<String> ordersInput = List.of(input.split(COMMA));

        for (String orderInput : ordersInput) {
            UserInputValidator.validateOrder(orderInput);
            String[] parts = orderInput.split(HYPHEN);

            String productName = parts[0];
            Product product = Product.findProductByName(productName);
            int count = UserInputValidator.validateInteger(parts[1]);

            orderProducts.add(new OrderProduct(product, count));
        }

        Orders orders = new Orders(orderProducts);

        return new OrdersDto(orders);
    }
}
