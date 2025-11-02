package lotto.strategy;

import java.util.List;

public class FixedGenerateStrategy implements GenerateStrategy {

    @Override
    public List<Integer> generateNumbers() {
        return List.of(1, 2, 3, 4, 5, 6);
    }
}
