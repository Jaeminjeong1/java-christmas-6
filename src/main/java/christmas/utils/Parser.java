package christmas.utils;

public class Parser {

    private final static String ORDER_SEPARATOR = ",";
    private final static String MENU_SEPARATOR = "-";

    private Parser() {}

    public static String[] orderParser(String order) {
        return order.split(ORDER_SEPARATOR);
    }

    public static String[] menuParser(String menu) {
        return menu.split(MENU_SEPARATOR);
    }
}
