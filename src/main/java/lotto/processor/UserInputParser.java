package lotto.processor;

import java.util.Arrays;
import java.util.List;

public class UserInputParser {
    public int parsePurchasePrice(String userInput) {
        String trimmed = userInput.trim();

        try {
            return Integer.parseInt(trimmed);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 구입 금액이 유효한 숫자 형식이 아니거나 범위를 초과했습니다.");
        }
    }

    public List<Integer> parseMainNumbers(String userInput) {
        String[] split = userInput.split(",");
        List<String> candidates = Arrays.stream(split)
                .map(String::trim)
                .toList();

        try {
            return candidates.stream()
                    .map(Integer::parseInt)
                    .toList();
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 당첨 번호가 유효한 숫자 형식이 아니거나 범위를 초과했습니다.");
        }
    }

    public int parseBonusNumber(String userInput) {
        String trimmed = userInput.trim();

        try {
            return Integer.parseInt(trimmed);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 보너스 번호가 유효한 숫자 형식이 아니거나 범위를 초과했습니다.");
        }
    }
}
