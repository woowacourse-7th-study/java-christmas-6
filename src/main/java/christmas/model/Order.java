package christmas.model;

import christmas.constants.Menu;
import christmas.constants.MenuType;
import christmas.constants.error.type.UserInputException;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static christmas.constants.error.ErrorMessage.NOT_ALLOWED_ONLY_DRINK;
import static christmas.constants.error.ErrorMessage.NOT_ALLOWED_ORDER;
import static christmas.constants.error.ErrorMessage.TOO_MANY_ORDERS;

public class Order {
    private static final int MIN_QUANTITY = 1;
    private static final int MAX_QUANTITY = 20;

    private final Map<String, Integer> items;

    public Order(Map<String, Integer> items) {
        validate(items);
        this.items = items;
    }

    private void validate(Map<String, Integer> items) {
        validateInMenu(items);
        validateNotOnlyDrink(items);
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

    private void validateNotOnlyDrink(Map<String, Integer> items) {
        Set<MenuType> menuTypes = new HashSet<>();

        items.forEach((orderName, quantity) -> {

            Menu selectedMenu = Arrays.stream(Menu.values())
                    .filter(menu -> menu.getName().equals(orderName))
                    .findFirst()
                    .orElseThrow(() -> new UserInputException(NOT_ALLOWED_ORDER));

            menuTypes.add(selectedMenu.getType());
        });

        if (menuTypes.size() == 1 && menuTypes.contains(MenuType.DRINK)) {
            throw new UserInputException(NOT_ALLOWED_ONLY_DRINK);
        }
    }

    private void validateQuantity(Map<String, Integer> items) {
        items.forEach((name, quantity) -> {
            if (quantity < MIN_QUANTITY) {
                throw new UserInputException(NOT_ALLOWED_ORDER);
            }
            if (quantity > MAX_QUANTITY) {
                throw new UserInputException(TOO_MANY_ORDERS);
            }
        });
    }

    public Map<String, Integer> getItems() {
        return items;
    }
}
