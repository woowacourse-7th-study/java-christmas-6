package christmas.model;

import christmas.constants.Menu;
import christmas.constants.error.type.UserInputException;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static christmas.constants.error.ErrorMessage.NOT_ALLOWED_ORDER;

public class Order {
    Map<String, Integer> items;

    public Order(Map<String, Integer> items) {
        validate(items);
        this.items = items;
    }

    private void validate(Map<String, Integer> items) {
        validateInMenu(items);
        validateQuantity(items);
    }

    private void validateInMenu(Map<String, Integer> items) {
        Set<String> menu = Arrays.stream(Menu.values())
                .map(Menu::getName)
                .collect(Collectors.toSet());
        Set<String> order = items.keySet();

        boolean allValid = menu.containsAll(order);
        if (allValid) {
            return;
        }
        throw new UserInputException(NOT_ALLOWED_ORDER);
    }

    private void validateQuantity(Map<String, Integer> items) {
        items.forEach((name, quantity) -> {
            if (quantity < 1) {
                throw new UserInputException(NOT_ALLOWED_ORDER);
            }
        });
    }

    public void addItem(String name, int quantity) {
        items.put(name, quantity);
    }

    public int getQuantity(String name) {
        return items.get(name);
    }
}
