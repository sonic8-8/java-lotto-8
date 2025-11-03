package lotto.model;

import java.util.Arrays;

public enum LottoRank {
    FIRST(6, 2_000_000_000, false, "6개 일치"),
    SECOND(5, 30_000_000, true, "5개 일치, 보너스 볼 일치"),
    THIRD(5, 1_500_000, false, "5개 일치"),
    FOURTH(4, 50_000, false, "4개 일치"),
    FIFTH(3, 5_000, false, "3개 일치"),
    NONE(0, 0, false, "꽝");

    private final int matchCount;
    private final int prizeMoney;
    private final boolean hasBonus;
    private final String description;

    LottoRank(int matchCount, int prizeMoney, boolean hasBonus, String description) {
        this.matchCount = matchCount;
        this.prizeMoney = prizeMoney;
        this.hasBonus = hasBonus;
        this.description = description;
    }

    public static LottoRank from(int matchCount, boolean hasBonus) {
        if (matchCount == 5 && hasBonus) {
            return SECOND;
        }
        if (matchCount == 5) {
            return THIRD;
        }

        return Arrays.stream(LottoRank.values())
                .filter(rank -> rank.matchCount == matchCount)
                .findFirst()
                .orElse(NONE);
    }

    public int getPrizeMoney() {
        return this.prizeMoney;
    }

    public int getMatchCount() {
        return this.matchCount;
    }

    public String getDescription() {
        return description;
    }
}
