package lotto.processor;

import lotto.model.LottoRule;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PurchasePriceValidatorTest {
    @DisplayName("구입 금액이 로또 1개 가격으로 나누어 떨어지지 않을 경우, 예외가 발생한다.")
    @Test
    void validate_modular() {
        // given
        PurchasePriceValidator purchasePriceValidator = new PurchasePriceValidator();
        int purchasePrice = 3100;

        // when then
        assertThatThrownBy(() -> purchasePriceValidator.validate(purchasePrice))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 구입 금액은 " + LottoRule.LOTTO_PRICE + "원 단위여야 합니다.");
    }

    @DisplayName("구입 금액이 음수일 경우, 예외가 발생한다.")
    @Test
    void validate_negative() {
        // given
        PurchasePriceValidator purchasePriceValidator = new PurchasePriceValidator();
        int purchasePrice = -1000;

        // when then
        assertThatThrownBy(() -> purchasePriceValidator.validate(purchasePrice))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 구입 금액은 음수일 수 없습니다.");
    }
}