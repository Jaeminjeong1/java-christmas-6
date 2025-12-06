package christmas.controller;

import christmas.service.ChristmasService;
import christmas.view.InputView;
import christmas.view.OutputView;

public class ChristmasController {

    private final InputView inputView;
    private final OutputView outputView;
    private final ChristmasService christmasService;

    public ChristmasController(InputView inputView, OutputView outputView, ChristmasService christmasService) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.christmasService = christmasService;
    }

}
