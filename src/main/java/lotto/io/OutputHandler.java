package lotto.io;

import lotto.model.Lotto;
import lotto.model.LottoRank;
import lotto.model.LottoStatistics;

import java.util.List;
import java.util.Map;

import static lotto.model.LottoRank.*;

public class OutputHandler {
    public void askPurchasePrice() {
        System.out.println("구입금액을 입력해 주세요.");
    }

    public void showLottos(List<Lotto> lottos) {
        System.out.println();
        System.out.println(lottos.size() + "개를 구매했습니다.");
        lottos.forEach(lotto -> System.out.println(lotto.findNumbers().toString()));
    }

    public void askMainNumbers() {
        System.out.println();
        System.out.println("당첨 번호를 입력해 주세요.");
    }

    public void askBonusNumber() {
        System.out.println();
        System.out.println("보너스 번호를 입력해 주세요.");
    }

    public void showLottoStatistics(LottoStatistics lottoStatistics) {
        System.out.println();
        System.out.println("당첨 통계");
        System.out.println("---");

        showWinningDetails(lottoStatistics.findWinningResult());

        showProfitRate(lottoStatistics.calculateProfitRate());
    }

    private void showWinningDetails(Map<LottoRank, Integer> winningResult) {
        List<LottoRank> ranksToDisplay = List.of(
                FIFTH, FOURTH, THIRD, SECOND, FIRST
        );

        ranksToDisplay.forEach(lottoRank -> {
                    Integer count = winningResult.getOrDefault(lottoRank, 0);
                    String prizeMoney = String.format("%,d", lottoRank.getPrizeMoney());

                    System.out.println(lottoRank.getDescription() + " (" + prizeMoney + "원) - " + count + "개");
                });
    }

    private void showProfitRate(double profitRate) {
        String formattedRate = String.format("%.1f", profitRate);
        System.out.println("총 수익률은 " + formattedRate + "%입니다.");
        System.out.println();
    }

    public void showErrorMessage(String message) {
        System.out.println(message);
    }
}