package lotto.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class LottoRankTest {
    @DisplayName("일치하는 번호가 6개일 경우 1등을 반환한다.")
    @Test
    void from_FIRST() {
        // given when
        LottoRank lottoRank = LottoRank.from(6, false);

        // then
        assertThat(lottoRank).isEqualTo(LottoRank.FIRST);
    }

    @DisplayName("일치하는 번호가 6개이고 보너스 번호를 포함할 경우에도 1등을 반환한다.")
    @Test
    void from_FIRST_hasBonus() {
        // given when
        LottoRank lottoRank = LottoRank.from(6, true);

        // then
        assertThat(lottoRank).isEqualTo(LottoRank.FIRST);
    }

    @DisplayName("일치하는 번호가 5개이고 보너스 번호가 일치할 경우 2등을 반환한다.")
    @Test
    void from_SECOND() {
        // given when
        LottoRank lottoRank = LottoRank.from(5, true);

        // then
        assertThat(lottoRank).isEqualTo(LottoRank.SECOND);
    }

    @DisplayName("일치하는 번호가 5개이고 보너스 번호를 포함하지 않을 경우 3등을 반환한다.")
    @Test
    void from_THIRD() {
        // given when
        LottoRank lottoRank = LottoRank.from(5, false);

        // then
        assertThat(lottoRank).isEqualTo(LottoRank.THIRD);
    }

    @DisplayName("일치하는 번호가 4개일 경우 4등을 반환한다.")
    @Test
    void from_FOURTH() {
        // given when
        LottoRank lottoRank = LottoRank.from(4, false);

        // then
        assertThat(lottoRank).isEqualTo(LottoRank.FOURTH);
    }

    @DisplayName("일치하는 번호가 3개일 경우 5등을 반환한다.")
    @Test
    void from_FIFTH() {
        // given when
        LottoRank lottoRank = LottoRank.from(3, false);

        // then
        assertThat(lottoRank).isEqualTo(LottoRank.FIFTH);
    }

    @DisplayName("일치하는 번호가 0개일 경우 꽝(NONE)을 반환한다.")
    @Test
    void from_NONE() {
        // given when
        LottoRank lottoRank = LottoRank.from(0, false);

        // then
        assertThat(lottoRank).isEqualTo(LottoRank.NONE);
    }
}