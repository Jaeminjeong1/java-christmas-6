package christmas.controller;

import christmas.service.ChristmasService;
import christmas.dto.PlannerResultDto;
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

    public void start() {
        outputView.startString();
        int day = readVisitDay();
        PlannerResultDto result = readOrderAndCalculate(day);
        printResult(result);
    }

    private int readVisitDay() {
        while (true) {
            try {
                int day = inputView.inputDay();
                christmasService.validateVisitDay(day);
                return day;
            } catch (IllegalArgumentException e) {
                outputView.printError(e.getMessage());
            }
        }
    }

    private PlannerResultDto readOrderAndCalculate(int day) {
        while (true) {
            try {
                String orderInput = inputView.inputMenu();
                return christmasService.calculate(day, orderInput);
            } catch (IllegalArgumentException e) {
                outputView.printError(e.getMessage());
            }
        }
    }

    private void printResult(PlannerResultDto result) {
        outputView.printPreviewHeader(result.getDay());
        outputView.printOrder(result.getOrderItems());
        outputView.printTotalPrice(result.getTotalPrice());
        outputView.printGift(result.getGiftAmount());
        outputView.printBenefits(result.getBenefits());
        outputView.printTotalBenefit(result.getTotalBenefit());
        outputView.printExpectedPayment(result.getExpectedPayment());
        outputView.printBadge(result.getBadgeName());
    }
}
