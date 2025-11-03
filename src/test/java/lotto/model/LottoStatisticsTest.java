package lotto.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static lotto.model.LottoRank.*;
import static org.assertj.core.api.Assertions.*;

class LottoStatisticsTest {
    @DisplayName("당첨 통계를 조회할 경우, 항상 복사본을 반환한다.")
    @Test
    void of() {
        // given
        Map<LottoRank, Integer> rankCounts = new HashMap<>();
        rankCounts.put(FIFTH, 1);
        rankCounts.put(FOURTH, 1);
        rankCounts.put(THIRD, 1);

        int purchasePrice = 3000;

        LottoStatistics lottoStatistics = LottoStatistics.of(rankCounts, purchasePrice);

        Map<LottoRank, Integer> winningResult = lottoStatistics.findWinningResult();
        winningResult.put(FIRST, 1);

        // when
        Map<LottoRank, Integer> newWinningResult = lottoStatistics.findWinningResult();

        // then
        assertThat(newWinningResult).hasSize(3)
                .containsOnly(
                        entry(FIFTH, 1),
                        entry(FOURTH, 1),
                        entry(THIRD, 1)
                );
    }

    @DisplayName("당첨 내역이 있을 경우, 수익률을 계산한다.")
    @Test
    void calculateProfitRate() {
        // given
        Map<LottoRank, Integer> rankCounts = new HashMap<>();
        rankCounts.put(FIFTH, 1);
        rankCounts.put(FOURTH, 1);
        rankCounts.put(THIRD, 1);

        int purchasedAmount = 3000;

        LottoStatistics lottoStatistics = LottoStatistics.of(rankCounts, purchasedAmount);

        // when
        double profitRate = lottoStatistics.calculateProfitRate();

        // then
        double expectedRate = 51833.333;
        assertThat(profitRate).isCloseTo(expectedRate, offset(0.001));
    }

    @DisplayName("당첨 내역이 없을 경우, 수익률은 0이다.")
    @Test
    void calculateProfitRate_zero() {
        // given
        Map<LottoRank, Integer> rankCounts = new HashMap<>();
        rankCounts.put(NONE, 3);

        int purchasedAmount = 3000;

        LottoStatistics lottoStatistics = LottoStatistics.of(rankCounts, purchasedAmount);

        // when
        double profitRate = lottoStatistics.calculateProfitRate();

        // then
        assertThat(profitRate).isZero();
    }
}