package lotto.processor;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class UserInputParserTest {
    @DisplayName("사용자 입력을 구입 가격으로 변환한다.")
    @Test
    void parsePurchasePrice() {
        // given
        UserInputParser userInputParser = new UserInputParser();
        String userInput = "1000";

        // when
        int purchasePrice = userInputParser.parsePurchasePrice(userInput);

        // then
        assertThat(purchasePrice).isEqualTo(1000);
    }

    @DisplayName("사용자 입력 앞뒤에 공백이 있을 경우, 공백을 제거하고 구입 가격으로 변환한다.")
    @Test
    void parsePurchasePrice_trim() {
        // given
        UserInputParser userInputParser = new UserInputParser();
        String userInput = " 1000 ";

        // when
        int purchasePrice = userInputParser.parsePurchasePrice(userInput);

        // then
        assertThat(purchasePrice).isEqualTo(1000);
    }

    @DisplayName("사용자 입력이 int 범위를 초과할 경우 예외가 발생한다.")
    @Test
    void parsePurchasePrice_range() {
        // given
        UserInputParser userInputParser = new UserInputParser();
        String userInput = "3_000_000_000";

        // when then
        assertThatThrownBy(() -> userInputParser.parsePurchasePrice(userInput))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 구입 금액이 유효한 숫자 형식이 아니거나 범위를 초과했습니다.");
    }

    @DisplayName("사용자 입력이 빈 문자열일 경우 예외가 발생한다.")
    @Test
    void parsePurchasePrice_emptyString() {
        // given
        UserInputParser userInputParser = new UserInputParser();
        String userInput = "";

        // when then
        assertThatThrownBy(() -> userInputParser.parsePurchasePrice(userInput))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 구입 금액이 유효한 숫자 형식이 아니거나 범위를 초과했습니다.");
    }

    @DisplayName("사용자 입력이 숫자 형식이 아닐 경우 예외가 발생한다.")
    @Test
    void parsePurchasePrice_notDigit() {
        // given
        UserInputParser userInputParser = new UserInputParser();
        String userInput = "abc";

        // when then
        assertThatThrownBy(() -> userInputParser.parsePurchasePrice(userInput))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 구입 금액이 유효한 숫자 형식이 아니거나 범위를 초과했습니다.");
    }

    @DisplayName("숫자 사이에 공백이 포함될 경우 예외가 발생한다.")
    @Test
    void parsePurchasePrice_internalSpace() {
        // given
        UserInputParser userInputParser = new UserInputParser();
        String userInput = "1 000";

        // when then
        assertThatThrownBy(() -> userInputParser.parsePurchasePrice(userInput))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 구입 금액이 유효한 숫자 형식이 아니거나 범위를 초과했습니다.");
    }

    @DisplayName("사용자 입력을 당첨 메인 번호로 변환한다.")
    @Test
    void parseMainNumbers() {
        // given
        UserInputParser userInputParser = new UserInputParser();
        String userInput = "1,2,3,4,5,6";

        // when
        List<Integer> mainNumbers = userInputParser.parseMainNumbers(userInput);

        // then
        assertThat(mainNumbers).containsExactly(
                1, 2, 3, 4, 5, 6
        );
    }

    @DisplayName("파싱한 문자열 앞뒤에 공백이 있을 경우, 공백을 제거하고 당첨 메인 번호로 변환한다.")
    @Test
    void parseMainNumbers_trim() {
        // given
        UserInputParser userInputParser = new UserInputParser();
        String userInput = "1, 2, 3, 4, 5, 6";

        // when
        List<Integer> mainNumbers = userInputParser.parseMainNumbers(userInput);

        // then
        assertThat(mainNumbers).containsExactly(
                1, 2, 3, 4, 5, 6
        );
    }

    @DisplayName("파싱한 문자열이 숫자 형식이 아닐 경우 예외가 발생한다.")
    @Test
    void parseMainNumbers_notDigit() {
        // given
        UserInputParser userInputParser = new UserInputParser();
        String userInput = "a,b,c,d,e,f";

        // when then
        assertThatThrownBy(() -> userInputParser.parseMainNumbers(userInput))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 당첨 번호가 유효한 숫자 형식이 아니거나 범위를 초과했습니다.");
    }

    @DisplayName("파싱한 문자열이 빈 문자열일 경우 예외가 발생한다.")
    @Test
    void parseMainNumbers_emptySegment() {
        // given
        UserInputParser userInputParser = new UserInputParser();
        String userInput = "1,2,,4,5,6";

        // when then
        assertThatThrownBy(() -> userInputParser.parseMainNumbers(userInput))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 당첨 번호가 유효한 숫자 형식이 아니거나 범위를 초과했습니다.");
    }

    @DisplayName("숫자 사이에 공백이 포함될 경우 예외가 발생한다.")
    @Test
    void parseMainNumbers_internalSpace() {
        // given
        UserInputParser userInputParser = new UserInputParser();
        String userInput = "1, 2, 3, 4, 12 4";

        // when then
        assertThatThrownBy(() -> userInputParser.parseMainNumbers(userInput))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 당첨 번호가 유효한 숫자 형식이 아니거나 범위를 초과했습니다.");
    }

    @DisplayName("사용자 입력을 보너스 번호로 변환한다.")
    @Test
    void parseBonusNumber() {
        // given
        UserInputParser userInputParser = new UserInputParser();
        String userInput = "7";

        // when
        int bonusNumber = userInputParser.parseBonusNumber(userInput);

        // then
        assertThat(bonusNumber).isEqualTo(7);
    }

    @DisplayName("사용자 입력 앞뒤에 공백이 있을 경우, 공백을 제거하고 보너스 번호로 변환한다.")
    @Test
    void parseBonusNumber_trim() {
        // given
        UserInputParser userInputParser = new UserInputParser();
        String userInput = " 7 ";

        // when
        int bonusNumber = userInputParser.parseBonusNumber(userInput);

        // then
        assertThat(bonusNumber).isEqualTo(7);
    }

    @DisplayName("사용자 입력이 숫자 형식이 아닐 경우 예외가 발생한다.")
    @Test
    void parseBonusNumber_notDigit() {
        // given
        UserInputParser userInputParser = new UserInputParser();
        String userInput = "abc";

        // when then
        assertThatThrownBy(() -> userInputParser.parseBonusNumber(userInput))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 보너스 번호가 유효한 숫자 형식이 아니거나 범위를 초과했습니다.");
    }

    @DisplayName("숫자 사이에 공백이 포함될 경우 예외가 발생한다.")
    @Test
    void parseBonusNumber_internalSpace() {
        // given
        UserInputParser userInputParser = new UserInputParser();
        String userInput = "4 5";

        // when then
        assertThatThrownBy(() -> userInputParser.parseBonusNumber(userInput))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 보너스 번호가 유효한 숫자 형식이 아니거나 범위를 초과했습니다.");
    }

    @DisplayName("사용자 입력이 int 범위를 초과할 경우 예외가 발생한다.")
    @Test
    void parseBonusNumber_range() {
        // given
        UserInputParser userInputParser = new UserInputParser();
        String userInput = "3_000_000_000";

        // when then
        assertThatThrownBy(() -> userInputParser.parseBonusNumber(userInput))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 보너스 번호가 유효한 숫자 형식이 아니거나 범위를 초과했습니다.");
    }

    @DisplayName("사용자 입력이 빈 문자열일 경우 예외가 발생한다.")
    @Test
    void parseBonusNumber_emptyString() {
        // given
        UserInputParser userInputParser = new UserInputParser();
        String userInput = "";

        // when then
        assertThatThrownBy(() -> userInputParser.parseBonusNumber(userInput))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 보너스 번호가 유효한 숫자 형식이 아니거나 범위를 초과했습니다.");
    }
}