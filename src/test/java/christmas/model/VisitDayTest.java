package christmas.model;

import christmas.constants.error.ErrorMessage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class VisitDayTest {

    @Test
    @DisplayName("사용자의 입력을 제대로 받아오는 데 성공한다.")
    void getVisitDay() {
        // given
        int expected = 1;

        // when
        VisitDay visitDay = new VisitDay(expected);
        int real = visitDay.getVisitDay();

        // then
        assertThat(real).isEqualTo(expected);
    }

    @Test
    @DisplayName("사용자의 입력이 허용 범위 밖인 경우 예외가 발생한다.")
    void validateVisitDayRange() {
        // given
        int outOfRange = 32;

        // when & then
        assertThatThrownBy(() -> new VisitDay(outOfRange))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ErrorMessage.INVALID_VISIT_DAY);
    }

    @Test
    @DisplayName("사용자의 입력이 허용 범위 내인 경우 예외가 발생하지 않는다.")
    void validVisitDay() {
        // given
        int inRange = 31;

        // when & then
        assertThatCode(() -> new VisitDay(inRange))
                .doesNotThrowAnyException();
    }
}