package lotto.processor;

import lotto.model.LottoRule;

public class PurchasePriceValidator {
    public void validate(int purchasePrice) {
        if (purchasePrice % LottoRule.LOTTO_PRICE != 0) {
            throw new IllegalArgumentException("[ERROR] 구입 금액은 " + LottoRule.LOTTO_PRICE + "원 단위여야 합니다.");
        }

        if (purchasePrice < 0) {
            throw new IllegalArgumentException("[ERROR] 구입 금액은 음수일 수 없습니다.");
        }
    }
}
