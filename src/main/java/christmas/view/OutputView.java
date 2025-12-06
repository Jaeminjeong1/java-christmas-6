package christmas.view;

public class OutputView {

    private final static String START = "안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.";


    private OutputView() {
    }

    public static OutputView create() {
        return new OutputView();
    }

}
