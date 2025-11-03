package lotto.io;

import lotto.model.Lotto;
import lotto.model.LottoStatistics;

import java.util.List;

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

        lottoStatistics.findWinningResult()
                .forEach((lottoRank, count) -> {
                    String prizeMoney = String.format("%,d", lottoRank.getPrizeMoney());
                    System.out.println(lottoRank.getDescription() + "(" + prizeMoney + "원) - " + count + "개");
                });

        String profitRate = String.format("%.1f", lottoStatistics.calculateProfitRate());
        System.out.println("총 수익률은 " + profitRate + "%입니다.");
        System.out.println();
    }

    public void showErrorMessage(String message) {
        System.out.println(message);
    }
}