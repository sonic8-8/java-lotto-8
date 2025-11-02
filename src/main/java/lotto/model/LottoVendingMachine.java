package lotto.model;

import lotto.strategy.GenerateStrategy;

import java.util.List;
import java.util.stream.Stream;

public class LottoVendingMachine {
    private final GenerateStrategy generateStrategy;
    private final int lottoPrice;

    public LottoVendingMachine(GenerateStrategy generateStrategy, int lottoPrice) {
        this.generateStrategy = generateStrategy;
        this.lottoPrice = lottoPrice;
    }

    public List<Lotto> buyLottos(int purchasedPrice) {
        if (purchasedPrice % lottoPrice != 0) {
            throw new IllegalArgumentException("[ERROR] 구입 금액은 " + lottoPrice + "원 단위여야 합니다.");
        }

        int count = purchasedPrice / lottoPrice;

        return Stream.generate(() -> Lotto.of(generateStrategy.generateNumbers()))
                .limit(count)
                .toList();
    }
}
