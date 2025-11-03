package lotto;

import lotto.io.InputHandler;
import lotto.io.OutputHandler;
import lotto.model.*;
import lotto.processor.PurchasePriceValidator;
import lotto.processor.UserInputParser;

import java.util.EnumMap;
import java.util.List;
import java.util.stream.Collectors;

public class LottoGame {
    private final InputHandler inputHandler;
    private final OutputHandler outputHandler;
    private final UserInputParser userInputParser;
    private final LottoVendingMachine lottoVendingMachine;
    private final PurchasePriceValidator purchasePriceValidator;

    public LottoGame(InputHandler inputHandler, OutputHandler outputHandler, UserInputParser userInputParser, LottoVendingMachine lottoVendingMachine, PurchasePriceValidator purchasePriceValidator) {
        this.inputHandler = inputHandler;
        this.outputHandler = outputHandler;
        this.userInputParser = userInputParser;
        this.lottoVendingMachine = lottoVendingMachine;
        this.purchasePriceValidator = purchasePriceValidator;
    }

    public void run() {
        int purchasePrice = promptForPurchasePrice();

        List<Lotto> lottos = purchaseLottos(purchasePrice);

        WinningNumbers winningNumbers = getWinningNumbers();

        showLottoResult(purchasePrice, lottos, winningNumbers);
    }

    private int promptForPurchasePrice() {
        Integer purchasePrice = null;
        while (purchasePrice == null) {
            purchasePrice = attemptToGetPurchasePrice();
        }
        return purchasePrice;
    }

    private Integer attemptToGetPurchasePrice() {
        try {
            outputHandler.askPurchasePrice();
            String userInput = inputHandler.getUserInput();

            int purchasePrice = userInputParser.parsePurchasePrice(userInput);
            purchasePriceValidator.validate(purchasePrice);

            return purchasePrice;
        } catch (IllegalArgumentException e) {
            outputHandler.showErrorMessage(e.getMessage());
        }
        return null;
    }

    private List<Lotto> purchaseLottos(int purchasePrice) {
        List<Lotto> lottos = lottoVendingMachine.buyLottos(purchasePrice);
        outputHandler.showLottos(lottos);
        return lottos;
    }

    private WinningNumbers getWinningNumbers() {
        List<Integer> mainNumbers = promptForMainNumbers();
        return promptForWinningNumbers(mainNumbers);
    }

    private List<Integer> promptForMainNumbers() {
        List<Integer> mainNumbers = null;
        while (mainNumbers == null) {
            mainNumbers = attemptToGetMainNumbers();
        }
        return mainNumbers;
    }

    private List<Integer> attemptToGetMainNumbers() {
        try {
            outputHandler.askMainNumbers();
            String userInput = inputHandler.getUserInput();

            List<Integer> parsedMainNumbers = userInputParser.parseMainNumbers(userInput);
            Lotto.of(parsedMainNumbers);

            return parsedMainNumbers;
        } catch (IllegalArgumentException e) {
            outputHandler.showErrorMessage(e.getMessage());
        }
        return null;
    }

    private WinningNumbers promptForWinningNumbers(List<Integer> mainNumbers) {
        WinningNumbers winningNumbers = null;
        while (winningNumbers == null) {
            winningNumbers = attemptToCreateWinningNumbers(mainNumbers);
        }
        return winningNumbers;
    }

    private WinningNumbers attemptToCreateWinningNumbers(List<Integer> mainNumbers) {
        try {
            Integer bonusNumber = promptForBonusNumber();

            return WinningNumbers.of(mainNumbers, bonusNumber);
        } catch (IllegalArgumentException e) {
            outputHandler.showErrorMessage(e.getMessage());
        }
        return null;
    }

    private Integer promptForBonusNumber() {
        Integer bonusNumber = null;
        while (bonusNumber == null) {
            bonusNumber = attemptToGetBonusNumber();
        }
        return bonusNumber;
    }

    private Integer attemptToGetBonusNumber() {
        try {
            outputHandler.askBonusNumber();
            String userInput = inputHandler.getUserInput();
            return userInputParser.parseBonusNumber(userInput);
        } catch (IllegalArgumentException e) {
            outputHandler.showErrorMessage(e.getMessage());
        }
        return null;
    }

    private void showLottoResult(int purchasePrice, List<Lotto> lottos, WinningNumbers winningNumbers) {
        EnumMap<LottoRank, Integer> rankCounts = lottos.stream()
                .map(lotto -> lotto.calculateRank(winningNumbers))
                .collect(Collectors.groupingBy(
                        lottoRank -> lottoRank,
                        () -> new EnumMap<>(LottoRank.class),
                        Collectors.summingInt(lottoRank -> 1)
                ));
        LottoStatistics lottoStatistics = LottoStatistics.of(rankCounts, purchasePrice);
        outputHandler.showLottoStatistics(lottoStatistics);
    }
}