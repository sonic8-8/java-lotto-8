package lotto.model;

import lotto.strategy.FixedGenerateStrategy;
import lotto.strategy.GenerateStrategy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class LottoVendingMachineTest {
    @DisplayName("구입 금액에 해당하는 개수만큼 로또를 발급한다.")
    @Test
    void buyLottos() {
        // given
        GenerateStrategy fixedGenerateStrategy = new FixedGenerateStrategy();
        LottoVendingMachine lottoVendingMachine = new LottoVendingMachine(fixedGenerateStrategy);

        int purchasePrice = 1000;

        // when
        List<Lotto> lottos = lottoVendingMachine.buyLottos(purchasePrice);

        // then
        assertThat(lottos).hasSize(1)
                .extracting(Lotto::findNumbers)
                .containsExactly(
                        List.of(1, 2, 3, 4, 5, 6)
                );
    }
}