package lotto.model;

import lotto.model.Lotto;
import lotto.strategy.GenerateStrategy;
import org.assertj.core.internal.Integers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class LottoVendingMachine {
    private final GenerateStrategy generateStrategy;
    private final int lottoPrice;

    public LottoVendingMachine(GenerateStrategy generateStrategy, int lottoPrice) {
        this.generateStrategy = generateStrategy;
        this.lottoPrice = lottoPrice;
    }

    public List<Lotto> buyLottos(int purchasedPrice) {
        int count = purchasedPrice / lottoPrice;

        return Stream.generate(() -> Lotto.of(generateStrategy.generateNumbers()))
                .limit(count)
                .toList();
    }
}
