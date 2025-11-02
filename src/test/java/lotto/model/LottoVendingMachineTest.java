package lotto.model;

import lotto.strategy.FixedGenerateStrategy;
import lotto.strategy.GenerateStrategy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LottoVendingMachineTest {
    @DisplayName("구입 금액에 해당하는 개수만큼 로또를 발급한다.")
    @Test
    void buyLottos() {
        // given
        GenerateStrategy fixedGenerateStrategy = new FixedGenerateStrategy();
        int lottoPrice = 1000;

        LottoVendingMachine lottoVendingMachine = new LottoVendingMachine(fixedGenerateStrategy, lottoPrice);

        int purchasedAmount = 1000;

        // when
        List<Lotto> lottos = lottoVendingMachine.buyLottos(purchasedAmount);

        // then
        assertThat(lottos).hasSize(1)
                .extracting(Lotto::findNumbers)
                .containsExactly(
                        List.of(1, 2, 3, 4, 5, 6)
                );
    }

    @DisplayName("구입 금액이 로또 1개 가격으로 나누어 떨어지지 않을 경우, 예외가 발생한다.")
    @Test
    void buyLottos_lottoPrice_modular() {
        // given
        GenerateStrategy fixedGenerateStrategy = new FixedGenerateStrategy();
        int lottoPrice = 1000;

        LottoVendingMachine lottoVendingMachine = new LottoVendingMachine(fixedGenerateStrategy, lottoPrice);

        int purchasedAmount = 3100;

        // when then
        assertThatThrownBy(() -> lottoVendingMachine.buyLottos(purchasedAmount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 구입 금액은 " + lottoPrice + "원 단위여야 합니다.");
    }

    @DisplayName("구입 금액이 음수일 경우, 예외가 발생한다.")
    @Test
    void buyLottos_lottoPrice_negative() {
        // given
        GenerateStrategy fixedGenerateStrategy = new FixedGenerateStrategy();
        int lottoPrice = 1000;

        LottoVendingMachine lottoVendingMachine = new LottoVendingMachine(fixedGenerateStrategy, lottoPrice);

        int purchasedAmount = -1000;

        // when then
        assertThatThrownBy(() -> lottoVendingMachine.buyLottos(purchasedAmount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 구입 금액은 음수일 수 없습니다.");
    }
}