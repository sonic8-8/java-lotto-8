package lotto.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class WinningNumbersTest {
    @DisplayName("매개변수로 받은 숫자 리스트와 당첨 번호가 일치하는 개수를 센다.")
    @Test
    void countMainMatches() {
        // given
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6);
        WinningNumbers winningNumbers = WinningNumbers.of(List.of(1, 2, 3, 4, 5, 6), 7);

        // when
        int count = winningNumbers.countMainMatches(numbers);

        // then
        assertThat(count).isEqualTo(6);
    }

    @DisplayName("매개변수로 받은 숫자 리스트와 당첨 번호가 일치하는 개수를 센다.")
    @Test
    void countMainMatches_zero() {
        // given
        List<Integer> numbers = List.of(10, 11, 12, 13, 14, 15);
        WinningNumbers winningNumbers = WinningNumbers.of(List.of(1, 2, 3, 4, 5, 6), 7);

        // when
        int count = winningNumbers.countMainMatches(numbers);

        // then
        assertThat(count).isZero();
    }

    @DisplayName("매개변수로 받은 숫자 리스트에 보너스 번호 포함 여부를 확인한다.")
    @Test
    void containsBonus() {
        // given
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6);
        WinningNumbers winningNumbers = WinningNumbers.of(List.of(1, 2, 3, 4, 5, 6), 7);

        // when
        boolean result = winningNumbers.containsBonus(numbers);

        // then
        assertThat(result).isFalse();
    }

    @DisplayName("매개변수로 받은 숫자 리스트에 보너스 번호 포함 여부를 확인한다.")
    @Test
    void containsBonus_true() {
        // given
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 7);
        WinningNumbers winningNumbers = WinningNumbers.of(List.of(1, 2, 3, 4, 5, 6), 7);

        // when
        boolean result = winningNumbers.containsBonus(numbers);

        // then
        assertThat(result).isTrue();
    }

    @DisplayName("당첨 번호 개수가 6개를 넘을 경우, 예외가 발생한다.")
    @Test
    void validateMainNumber_count() {
        assertThatThrownBy(() -> WinningNumbers.of(List.of(1, 2, 3, 4, 5, 6, 7), 8))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 당첨 번호는 6개여야 합니다.");
    }

    @DisplayName("당첨 번호 개수가 6개 미만일 경우, 예외가 발생한다.")
    @Test
    void validateMainNumber_count_under() {
        assertThatThrownBy(() -> WinningNumbers.of(List.of(1, 2, 3, 4, 5), 8))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 당첨 번호는 6개여야 합니다.");
    }

    @DisplayName("당첨 번호에 중복된 숫자가 있을 경우, 예외가 발생한다.")
    @Test
    void validateMainNumber_duplicate() {
        assertThatThrownBy(() -> WinningNumbers.of(List.of(1, 2, 3, 4, 5, 5), 8))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 당첨 번호는 중복될 수 없습니다.");
    }

    @DisplayName("당첨 번호가 1부터 45 사이의 숫자가 아닐 경우, 예외가 발생한다.")
    @Test
    void validateMainNumber_range() {
        assertThatThrownBy(() -> WinningNumbers.of(List.of(1, 2, 3, 4, 5, 46), 8))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 당첨 번호는 1부터 45 사이여야 합니다.");
    }

    @DisplayName("보너스 번호가 1부터 45 사이의 숫자가 아닐 경우, 예외가 발생한다.")
    @Test
    void validateBonusNumber_range() {
        assertThatThrownBy(() -> WinningNumbers.of(List.of(1, 2, 3, 4, 5, 6), 46))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 보너스 번호는 1부터 45 사이여야 합니다.");
    }

    @DisplayName("보너스 번호에 중복된 숫자가 있을 경우, 예외가 발생한다.")
    @Test
    void validateBonusNumber_duplicate() {
        assertThatThrownBy(() -> WinningNumbers.of(List.of(1, 2, 3, 4, 5, 6), 6))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 보너스 번호는 당첨 번호와 중복될 수 없습니다.");
    }
}