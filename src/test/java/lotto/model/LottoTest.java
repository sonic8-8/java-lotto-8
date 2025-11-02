package lotto.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LottoTest {
    @DisplayName("로또 번호 개수가 6개를 넘을 경우, 예외가 발생한다.")
    @Test
    void validate_count() {
        assertThatThrownBy(() -> Lotto.of((List.of(1, 2, 3, 4, 5, 6, 7))))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 로또 번호는 6개여야 합니다.");
    }

    @DisplayName("로또 번호에 중복된 숫자가 있을 경우, 예외가 발생한다.")
    @Test
    void validate_duplicate() {
        assertThatThrownBy(() -> Lotto.of((List.of(1, 2, 3, 3, 4, 5))))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 로또 번호는 중복될 수 없습니다.");
    }

    @DisplayName("로또 번호가 1부터 45 사이의 숫자가 아닐 경우, 예외가 발생한다.")
    @Test
    void validate_range() {
        assertThatThrownBy(() -> Lotto.of((List.of(1, 2, 3, 4, 5, 47))))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 로또 번호는 1부터 45 사이여야 합니다.");
    }

    @DisplayName("로또 번호를 당첨 번호와 비교하여 등수를 계산한다.")
    @Test
    void calculateRank() {
        // given
        Lotto lotto = Lotto.of(List.of(1, 2, 3, 4, 5, 6));
        WinningNumbers winningNumbers = WinningNumbers.of(List.of(1, 2, 3, 4, 5, 6), 7);

        // when
        LottoRank lottoRank = lotto.calculateRank(winningNumbers);

        // then
        assertThat(lottoRank).isEqualTo(LottoRank.FIRST);
    }

    @DisplayName("로또 번호가 당첨 번호와 5개가 일치하면서 보너스 숫자를 포함할 경우, 2등으로 계산한다.")
    @Test
    void calculateRank_SECOND() {
        // given
        Lotto lotto = Lotto.of(List.of(1, 2, 3, 4, 5, 6));
        WinningNumbers winningNumbers = WinningNumbers.of(List.of(1, 2, 3, 4, 5, 7), 6);

        // when
        LottoRank lottoRank = lotto.calculateRank(winningNumbers);

        // then
        assertThat(lottoRank).isEqualTo(LottoRank.SECOND);
    }

    @DisplayName("로또 번호가 당첨 번호와 5개가 일치하면서 보너스 숫자는 포함하지 않는 경우, 3등으로 계산한다.")
    @Test
    void calculateRank_THIRD() {
        // given
        Lotto lotto = Lotto.of(List.of(1, 2, 3, 4, 5, 6));
        WinningNumbers winningNumbers = WinningNumbers.of(List.of(1, 2, 3, 4, 5, 7), 8);

        // when
        LottoRank lottoRank = lotto.calculateRank(winningNumbers);

        // then
        assertThat(lottoRank).isEqualTo(LottoRank.THIRD);
    }

    @DisplayName("로또 번호가 당첨 번호와 아무것도 일치하지 않을 경우, 꽝으로 계산한다.")
    @Test
    void calculateRank_NONE() {
        // given
        Lotto lotto = Lotto.of(List.of(1, 2, 3, 4, 5, 6));
        WinningNumbers winningNumbers = WinningNumbers.of(List.of(7, 8, 9, 10, 11, 12), 6);

        // when
        LottoRank lottoRank = lotto.calculateRank(winningNumbers);

        // then
        assertThat(lottoRank).isEqualTo(LottoRank.NONE);
    }

}
