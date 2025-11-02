package lotto.model;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;

public class WinningNumbers {
    private final List<Integer> mainNumbers;
    private final int bonusNumber;

    private WinningNumbers(List<Integer> mainNumbers, int bonusNumber) {
        validateMainNumber(mainNumbers);
        validateBonusNumber(mainNumbers, bonusNumber);

        this.mainNumbers = Collections.unmodifiableList(mainNumbers);
        this.bonusNumber = bonusNumber;
    }

    public static WinningNumbers of(List<Integer> mainNumbers, int bonusNumber) {
        return new WinningNumbers(mainNumbers, bonusNumber);
    }

    public int countMainMatches(List<Integer> numbers) {
        return (int) numbers.stream()
                .filter(mainNumbers::contains)
                .count();
    }

    public boolean containsBonus(List<Integer> numbers) {
        return numbers.contains(bonusNumber);
    }

    private void validateMainNumber(List<Integer> mainNumbers) {
        if (mainNumbers.size() != 6) {
            throw new IllegalArgumentException("[ERROR] 당첨 번호는 6개여야 합니다.");
        }

        if (new HashSet<>(mainNumbers).size() < 6) {
            throw new IllegalArgumentException("[ERROR] 당첨 번호는 중복될 수 없습니다.");
        }

        boolean isAllInRange = mainNumbers.stream()
                .allMatch(number -> 1 <= number && number <= 45);
        if (!isAllInRange) {
            throw new IllegalArgumentException("[ERROR] 당첨 번호는 1부터 45 사이여야 합니다.");
        }
    }

    private void validateBonusNumber(List<Integer> mainNumbers, int bonusNumber) {
        if (bonusNumber < 1 || 45 < bonusNumber) {
            throw new IllegalArgumentException("[ERROR] 보너스 번호는 1부터 45 사이여야 합니다.");
        }
        if (mainNumbers.contains(bonusNumber)) {
            throw new IllegalArgumentException("[ERROR] 보너스 번호는 당첨 번호와 중복될 수 없습니다.");
        }
    }
}
