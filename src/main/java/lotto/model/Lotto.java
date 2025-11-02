package lotto.model;

import java.util.*;

public class Lotto {
    private final List<Integer> numbers;

    private Lotto(List<Integer> numbers) {
        validate(numbers);

        ArrayList<Integer> sortedNumbers = new ArrayList<>(numbers);
        Collections.sort(sortedNumbers);

        this.numbers = Collections.unmodifiableList(sortedNumbers);
    }

    public static Lotto of(List<Integer> numbers) {
        return new Lotto(numbers);
    }

    private void validate(List<Integer> numbers) {
        if (numbers.size() != 6) {
            throw new IllegalArgumentException("[ERROR] 로또 번호는 6개여야 합니다.");
        }

        if (new HashSet<>(numbers).size() < 6) {
            throw new IllegalArgumentException("[ERROR] 로또 번호는 중복될 수 없습니다.");
        }

        boolean isAllInRange = numbers.stream()
                .allMatch(number -> 1 <= number && number <= 45);
        if (!isAllInRange) {
            throw new IllegalArgumentException("[ERROR] 로또 번호는 1부터 45 사이여야 합니다.");
        }
    }

    public List<Integer> findNumbers() {
        return new ArrayList<>(numbers);
    }

    public LottoRank calculateRank(WinningNumbers winningNumbers) {
        int matchCount = winningNumbers.countMainMatches(this.numbers);
        boolean hasBonus = winningNumbers.containsBonus(this.numbers);

        return LottoRank.from(matchCount, hasBonus);
    }

}
