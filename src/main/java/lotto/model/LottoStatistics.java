package lotto.model;

import java.util.EnumMap;
import java.util.Map;

public class LottoStatistics {
    private final Map<LottoRank, Integer> rankCounts;
    private final int purchasePrice;

    private LottoStatistics(Map<LottoRank, Integer> rankCounts, int purchasePrice) {
        this.rankCounts = rankCounts;
        this.purchasePrice = purchasePrice;
    }

    public static LottoStatistics of(Map<LottoRank, Integer> rankCounts, int purchasePrice) {
        return new LottoStatistics(rankCounts, purchasePrice);
    }

    public Map<LottoRank, Integer> findWinningResult() {
        return new EnumMap<>(rankCounts);
    }

    public double calculateProfitRate() {
        double totalPrizeMoney = rankCounts.entrySet().stream()
                .filter(entry -> entry.getValue() >= 1)
                .mapToDouble(entry -> entry.getKey().getPrizeMoney() * entry.getValue())
                .sum();

        return totalPrizeMoney / purchasePrice * 100;
    }
}