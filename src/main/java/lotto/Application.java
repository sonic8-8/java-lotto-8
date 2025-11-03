package lotto;

import lotto.io.InputHandler;
import lotto.io.OutputHandler;
import lotto.model.LottoVendingMachine;
import lotto.processor.PurchasePriceValidator;
import lotto.processor.UserInputParser;
import lotto.strategy.RandomGenerateStrategy;

public class Application {
    public static void main(String[] args) {
        InputHandler inputHandler = new InputHandler();
        OutputHandler outputHandler = new OutputHandler();
        UserInputParser userInputParser = new UserInputParser();
        PurchasePriceValidator purchasePriceValidator = new PurchasePriceValidator();

        RandomGenerateStrategy randomGenerateStrategy = new RandomGenerateStrategy();
        LottoVendingMachine lottoVendingMachine = new LottoVendingMachine(randomGenerateStrategy);

        LottoGame lottoGame = new LottoGame(inputHandler, outputHandler, userInputParser, lottoVendingMachine, purchasePriceValidator);
        lottoGame.run();
    }
}
