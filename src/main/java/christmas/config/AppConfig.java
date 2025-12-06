package christmas.config;

import christmas.controller.ChristmasController;
import christmas.service.ChristmasService;
import christmas.view.InputView;
import christmas.view.OutputView;

public class AppConfig {

    public static ChristmasService createService() {
        return new ChristmasService();
    }

    public static ChristmasController createController() {
        return new ChristmasController(InputView.create(), OutputView.create(), createService());
    }

    public void start() {

    }
}
