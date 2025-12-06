package christmas.view;

import camp.nextstep.edu.missionutils.Console;

import java.util.regex.Pattern;

import static christmas.utils.ErrorMessage.DAY_INPUT_ERROR;

public class InputView {

    private final static String DAY_INPUT = "12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)";
    private final static String MENU_INPUT = "주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)";
    private static final Pattern NUMBER_PATTERN = Pattern.compile("\\d+");

    private InputView() {
    }

    public static InputView create() {
        return new InputView();
    }

    public int inputDay() {
        System.out.println(DAY_INPUT);
        String input = Console.readLine();

        validateEmptyInput(input);
        validateNumberFormat(input);

        return Integer.parseInt(input);
    }

    public String inputMenu() {
        System.out.println(MENU_INPUT);
        String input = Console.readLine();

        validateEmptyInput(input);

        return input;
    }

    private void validateEmptyInput(String userInput) {
        if (userInput == null || userInput.isBlank()) {
            throw new IllegalArgumentException(DAY_INPUT_ERROR.getMessage());
        }
    }

    private void validateNumberFormat(String userInput) {
        if (!NUMBER_PATTERN.matcher(userInput).matches()) {
            throw new IllegalArgumentException(DAY_INPUT_ERROR.getMessage());
        }
    }
}
