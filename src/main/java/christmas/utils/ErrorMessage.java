package christmas.utils;

public enum ErrorMessage {

    DAY_INPUT_ERROR("유효하지 않은 날짜입니다. 다시 입력해 주세요."),
    MENU_INPUT_ERROR("유효하지 않은 주문입니다. 다시 입력해 주세요.");

    private final static String PREFIX = "[ERROR] ";
    private final String message;

    ErrorMessage(String message) {
        this.message = PREFIX + message;
    }

    public String getMessage() {
        return message;
    }
}
