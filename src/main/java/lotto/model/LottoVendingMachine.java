package lotto.model;

import lotto.strategy.GenerateStrategy;

import java.util.List;
import java.util.stream.Stream;

public class LottoVendingMachine {
    private final GenerateStrategy generateStrategy;

    public LottoVendingMachine(GenerateStrategy generateStrategy) {
        this.generateStrategy = generateStrategy;
    }

    public List<Lotto> buyLottos(int purchasePrice) {
        int count = purchasePrice / LottoRule.LOTTO_PRICE;

        return Stream.generate(() -> Lotto.of(generateStrategy.generateNumbers()))
                .limit(count)
                .toList();
    }
}
