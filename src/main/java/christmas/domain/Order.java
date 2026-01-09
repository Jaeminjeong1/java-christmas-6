package christmas.domain;

import static christmas.utils.ErrorMessage.MENU_INPUT_ERROR;

import christmas.utils.Parser;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class Order {

    private static final Pattern NUMBER_PATTERN = Pattern.compile("\\d+");
    private static final int MIN_COUNT = 1;
    private static final int MAX_COUNT = 20;

    private final Map<Menu, Integer> items;

    private Order(Map<Menu, Integer> items) {
        this.items = items;
    }

    public static Order from(String input) {
        Map<Menu, Integer> parsed = new LinkedHashMap<>();
        String[] splitInputs = Parser.orderParser(input);
        for (String splitInput : splitInputs) {
            String[] splitOrder = Parser.menuParser(splitInput.trim());
            Menu menu = parseMenu(splitOrder);
            int count = parseCount(splitOrder);
            validateDuplicate(parsed, menu);
            parsed.put(menu, count);
        }
        validateTotalCount(parsed);
        validateDrinkOnly(parsed);
        return new Order(parsed);
    }

    private static Menu parseMenu(String[] splitOrder) {
        validateOrderFormat(splitOrder);
        return Menu.fromName(splitOrder[0].trim());
    }

    private static int parseCount(String[] splitOrder) {
        validateOrderFormat(splitOrder);
        String countString = splitOrder[1].trim();
        validateNumberFormat(countString);
        validatePositive(countString);
        return Integer.parseInt(countString);
    }

    private static void validateOrderFormat(String[] splitOrder) {
        if (splitOrder.length != 2) {
            throw new IllegalArgumentException(MENU_INPUT_ERROR.getMessage());
        }
    }

    private static void validateNumberFormat(String userInput) {
        if (!NUMBER_PATTERN.matcher(userInput).matches()) {
            throw new IllegalArgumentException(MENU_INPUT_ERROR.getMessage());
        }
    }

    private static void validatePositive(String userInput) {
        int number = Integer.parseInt(userInput);
        if (number < MIN_COUNT) {
            throw new IllegalArgumentException(MENU_INPUT_ERROR.getMessage());
        }
    }

    private static void validateDuplicate(Map<Menu, Integer> parsed, Menu menu) {
        if (parsed.containsKey(menu)) {
            throw new IllegalArgumentException(MENU_INPUT_ERROR.getMessage());
        }
    }

    private static void validateTotalCount(Map<Menu, Integer> parsed) {
        int total = 0;
        for (Integer count : parsed.values()) {
            total += count;
        }
        if (total > MAX_COUNT) {
            throw new IllegalArgumentException(MENU_INPUT_ERROR.getMessage());
        }
    }

    private static void validateDrinkOnly(Map<Menu, Integer> parsed) {
        int drinkCount = 0;

        for (Menu menu : parsed.keySet()) {
            if (menu.getMenuCategory() == MenuCategory.DRINK) {
                drinkCount++;
            }
        }

        if (drinkCount == parsed.size()) {
            throw new IllegalArgumentException(MENU_INPUT_ERROR.getMessage());
        }
    }

    public int getTotalPrice() {
        int totalPrice = 0;

        for (Map.Entry<Menu, Integer> entry : items.entrySet()) {
            Menu menu = entry.getKey();
            int quantity = entry.getValue();
            totalPrice += menu.getPrice() * quantity;
        }

        return totalPrice;
    }

    public int getMainCount() {
        int mainCount = 0;

        for (Map.Entry<Menu, Integer> entry : items.entrySet()) {
            Menu menu = entry.getKey();
            int quantity = entry.getValue();

            if (menu.getMenuCategory() == MenuCategory.MAIN) {
                mainCount += quantity;
            }
        }

        return mainCount;
    }

    public int getDessertCount() {
        int dessertCount = 0;

        for (Map.Entry<Menu, Integer> entry : items.entrySet()) {
            Menu menu = entry.getKey();
            int quantity = entry.getValue();

            if (menu.getMenuCategory() == MenuCategory.DESSERT) {
                dessertCount += quantity;
            }
        }

        return dessertCount;
    }

    public Map<Menu, Integer> getItems() {
        return items;
    }
}
