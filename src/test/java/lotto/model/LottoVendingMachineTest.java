package lotto.model;

import lotto.model.Lotto;
import lotto.model.LottoVendingMachine;
import lotto.strategy.FixedGenerateStrategy;
import lotto.strategy.GenerateStrategy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

class LottoVendingMachineTest {
    @DisplayName("구매 금액에 해당하는 개수만큼 로또를 발급한다.")
    @Test
    void test() {
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

}