package christmas.domain;

public enum DayOfWeek {
    MON, TUE, WED, THU, FRI, SAT, SUN;

    public static DayOfWeek fromIndex(int index) {
        return DayOfWeek.values()[index];
    }
}
