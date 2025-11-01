package lotto.model;

import java.util.EnumMap;
import java.util.Map;

public class LottoStatistics {
    private final Map<LottoRank, Integer> rankCounts;
    private final int purchasedAmount;

    private LottoStatistics(Map<LottoRank, Integer> rankCounts, int purchasedAmount) {
        this.rankCounts = rankCounts;
        this.purchasedAmount = purchasedAmount;
    }

    public static LottoStatistics of(Map<LottoRank, Integer> rankCounts, int purchasedAmount) {
        return new LottoStatistics(rankCounts, purchasedAmount);
    }

    public Map<LottoRank, Integer> findWinningResult() {
        return new EnumMap<>(rankCounts);
    }

    public double calculateProfitRate() {
        double totalPrizeMoney = rankCounts.entrySet().stream()
                .filter(entry -> entry.getValue() >= 1)
                .mapToDouble(entry -> entry.getKey().getPrizeMoney() * entry.getValue())
                .sum();

        return totalPrizeMoney / purchasedAmount;
    }
}