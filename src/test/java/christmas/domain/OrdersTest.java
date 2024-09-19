package christmas.domain;

import christmas.constants.exception.InputException;
import christmas.domain.vo.Product;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static christmas.constants.exception.error.ErrorMessage.INVALID_ORDER;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;

public class OrdersTest {
    @Test
    @DisplayName("유효한 주문 목록이 입력되면 예외가 발생하지 않는다.")
    void validOrdersDoesNotThrowException() {
        // given
        List<OrderProduct> validOrderProducts = List.of(
            new OrderProduct(Product.MUSHROOM_SOUP, 2),
            new OrderProduct(Product.RED_WINE, 1)
        );

        // when, then
        assertThatCode(() -> new Orders(validOrderProducts))
            .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("중복된 상품이 입력되면 예외가 발생한다.")
    void duplicateProductsThrowException() {
        // given
        List<OrderProduct> duplicateOrderProducts = List.of(
            new OrderProduct(Product.MUSHROOM_SOUP, 2),
            new OrderProduct(Product.MUSHROOM_SOUP, 1)
        );

        // when, then
        assertThatThrownBy(() -> new Orders(duplicateOrderProducts))
            .isInstanceOf(InputException.class)
            .hasMessageContaining(INVALID_ORDER.getMessage());
    }

    @Test
    @DisplayName("음료만 주문된 경우 예외가 발생한다.")
    void onlyDrinksThrowException() {
        // given
        List<OrderProduct> onlyDrinksOrderProducts = List.of(
            new OrderProduct(Product.ZERO_COKE, 2),
            new OrderProduct(Product.RED_WINE, 1)
        );

        // when, then
        assertThatThrownBy(() -> new Orders(onlyDrinksOrderProducts))
            .isInstanceOf(InputException.class)
            .hasMessageContaining(INVALID_ORDER.getMessage());
    }
}