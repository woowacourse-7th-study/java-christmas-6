package christmas.service;

import christmas.constants.Menu;
import christmas.constants.error.type.UserInputException;
import christmas.dto.OrderResponse;
import christmas.dto.PriceBeforeDiscountResponse;
import christmas.model.Order;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static christmas.constants.error.ErrorMessage.NOT_ALLOWED_ORDER;

public class OrderService {
    private static OrderService instance;

    private OrderService() {
    }

    public static OrderService getInstance() {
        if (instance == null) {
            instance = new OrderService();
        }
        return instance;
    }

    public OrderResponse createOrderResponse(Order order) {
        List<String> names = extractNames(order);
        List<Integer> quantities = extractQuantities(order);
        return new OrderResponse(names, quantities);
    }

    public PriceBeforeDiscountResponse createPriceBeforeDiscountResponse(Order order) {
        int priceBeforeDiscount = calculateTotalPriceBeforeDiscount(order);
        return new PriceBeforeDiscountResponse(priceBeforeDiscount);
    }

    private List<String> extractNames(Order order) {
        List<String> names = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : order.getItems().entrySet()) {
            names.add(entry.getKey());
        }
        return names;
    }

    private List<Integer> extractQuantities(Order order) {
        List<Integer> quantities = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : order.getItems().entrySet()) {
            quantities.add(entry.getValue());
        }
        return quantities;
    }

    public int calculateTotalPriceBeforeDiscount(Order order) {
        int totalPrice = 0;
        Map<String, Integer> items = order.getItems();

        for (Map.Entry<String, Integer> entry : items.entrySet()) {
            String orderName = entry.getKey();
            int quantity = entry.getValue();

            Menu selectedMenu = Arrays.stream(Menu.values())
                    .filter(menu -> menu.getName().equals(orderName))
                    .findFirst()
                    .orElseThrow(() -> new UserInputException(NOT_ALLOWED_ORDER));

            totalPrice += selectedMenu.getPrice() * quantity;
        }

        return totalPrice;
    }
}