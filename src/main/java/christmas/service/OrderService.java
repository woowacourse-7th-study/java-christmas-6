package christmas.service;

import christmas.dto.OrderResponse;
import christmas.model.Order;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class OrderService {
    public OrderResponse createOrderResponse(Order order) {
        List<String> names = extractNames(order);
        List<Integer> quantities = extractQuantities(order);
        return new OrderResponse(names, quantities);
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
}