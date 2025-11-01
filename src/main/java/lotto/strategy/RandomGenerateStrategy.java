package lotto.strategy;

import camp.nextstep.edu.missionutils.Randoms;

import java.util.List;

public class RandomGenerateStrategy implements GenerateStrategy {

    @Override
    public List<Integer> generateNumbers() {
        return Randoms.pickUniqueNumbersInRange(1, 45, 6);
    }
}
