package lotto.model;

import java.util.List;

public class WinningNumbers {
    private final List<Integer> mainNumbers;
    private final int bonusNumber;

    private WinningNumbers(List<Integer> mainNumbers, int bonusNumber) {
        this.mainNumbers = mainNumbers;
        this.bonusNumber = bonusNumber;
    }

    public static WinningNumbers of(List<Integer> mainNumbers, int bonusNumber) {
        return new WinningNumbers(mainNumbers, bonusNumber);
    }

    public int countMainMatches(List<Integer> numbers) {
        int count = 0;
        for (int mainNumber : mainNumbers) {
            for (int number : numbers) {
                if (mainNumber == number) {
                    count++;
                }
            }
        }
        return count;
    }

    public boolean containsBonus(List<Integer> numbers) {
        return numbers.contains(bonusNumber);
    }
}
