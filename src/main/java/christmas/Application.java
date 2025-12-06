package christmas;

import christmas.config.AppConfig;
import christmas.controller.ChristmasController;

public class Application {
    public static void main(String[] args) {
        // TODO: 프로그램 구현
        ChristmasController christmasController = AppConfig.createController();
        christmasController.start();
    }
}
