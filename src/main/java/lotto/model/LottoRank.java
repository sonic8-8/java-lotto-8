package lotto.model;

public enum LottoRank {
    FIRST(6, 2_000_000_000, "1등, 6개 일치"),
    SECOND(5, 30_000_000, "2등, 5개 일치, 보너스 볼 일치"),
    THIRD(5, 1_500_000, "3등, 5개 일치"),
    FOURTH(4, 50_000, "4등, 4개 일치"),
    FIFTH(3, 5_000, "5등, 3개 일치"),
    NONE(0, 0, "꽝");

    private final int matchCount;
    private final int prizeMoney;
    private final String desciption;

    LottoRank(int matchCount, int prizeMoney, String desciption) {
        this.matchCount = matchCount;
        this.prizeMoney = prizeMoney;
        this.desciption = desciption;
    }

    public int getPrizeMoney() {
        return prizeMoney;
    }
}
