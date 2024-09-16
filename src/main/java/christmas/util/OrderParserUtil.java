package christmas.util;

import static christmas.constants.Symbol.COMMA;
import static christmas.constants.Symbol.HYPHEN;

import christmas.domain.OrderProduct;
import christmas.domain.vo.Product;
import christmas.dto.OrderDto;
import christmas.validator.UserInputValidator;
import java.util.ArrayList;
import java.util.List;

public class OrderParserUtil {
    public static OrderDto parseOrder(String input) {
        UserInputValidator.validateOrders(input);

        List<OrderProduct> orderProducts = new ArrayList<>();
        List<String> orders = List.of(input.split(COMMA));

        for (String order : orders) {
            UserInputValidator.validateOrder(order);
            String[] parts = order.split(HYPHEN);

            String productName = parts[0];
            Product product = Product.findProductByName(productName);
            int count = UserInputValidator.validateInteger(parts[1]);

            orderProducts.add(new OrderProduct(product, count));
        }

        return new OrderDto(orderProducts);
    }
}
