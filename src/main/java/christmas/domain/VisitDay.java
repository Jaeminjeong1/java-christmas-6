package christmas.domain;


public class VisitDay {

    private DayOfMonth dayOfMonth;
    private DayOfWeek dayOfWeek;

    private VisitDay (DayOfMonth dayOfMonth, DayOfWeek dayOfWeek) {
        this.dayOfMonth = dayOfMonth;
        this.dayOfWeek = dayOfWeek;
    }

    public static VisitDay from(int day) {
        DayOfMonth dayOfMonth = DayOfMonth.from(day);

        //금요일이 1일
        int startIndex = DayOfWeek.FRI.ordinal();

        int index = (startIndex + (day - 1)) % 7;
        DayOfWeek dayOfWeek = DayOfWeek.fromIndex(index);

        return new VisitDay(dayOfMonth, dayOfWeek);
    }
}
