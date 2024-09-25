package christmas.model;

import christmas.constants.error.ErrorMessage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class OrderTest {
    @Test
    @DisplayName("메뉴에 없는 주문을 한 경우 예외가 발생한다.")
    void notInMenu() {
        // given
        String name = "딸기";
        int count = 2;
        Map<String, Integer> menu = Map.of(name, count);

        // when & then
        assertThatThrownBy(() -> new Order(menu))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ErrorMessage.NOT_ALLOWED_ORDER);
    }

    @Test
    @DisplayName("음료만 주문하는 경우 예외가 발생한다.")
    void notOnlyDrink() {
        // given
        String name = "제로콜라";
        int count = 2;
        Map<String, Integer> menu = Map.of(name, count);

        // when & then
        assertThatThrownBy(() -> new Order(menu))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ErrorMessage.NOT_ALLOWED_ONLY_DRINK);
    }

    @Test
    @DisplayName("20개 이상으로 주문하는 경우 경우 예외가 발생한다.")
    void notOver20() {
        // given
        String name = "양송이수프";
        int count = 21;
        Map<String, Integer> menu = Map.of(name, count);

        // when & then
        assertThatThrownBy(() -> new Order(menu))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ErrorMessage.TOO_MANY_ORDERS);
    }

    @Test
    @DisplayName("정상적인 주문인 경우 예외가 발생하지 않는다.")
    void validOrder() {
        // given
        String name1 = "양송이수프";
        int count1 = 2;
        String name2 = "바비큐립";
        int count2 = 3;
        Map<String, Integer> menu = Map.of(name1, count1, name2, count2);

        // when & then
        assertThatCode(() -> new Order(menu))
                .doesNotThrowAnyException();
    }
}