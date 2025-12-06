package christmas.domain;


import static christmas.utils.ErrorMessage.DAY_INPUT_ERROR;

public class DayOfMonth {

    private static final int START_DAY = 1;
    private static final int END_DAY = 31;

    private final int value;

    private DayOfMonth (int value) {
        validateValue(value);
        this.value = value;
    }

    public static DayOfMonth from (int value) {
        return new DayOfMonth(value);
    }

    private void validateValue (int value) {
        if (value < START_DAY || value > END_DAY) {
            throw new IllegalArgumentException (DAY_INPUT_ERROR.getMessage());
        }
    }
}
