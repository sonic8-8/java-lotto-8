package lotto.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

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
}