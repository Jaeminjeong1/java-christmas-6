package christmas.domain;

public enum Badge {
    NONE("없음"),
    STAR("별"),
    TREE("트리"),
    SANTA("산타");

    private final String name;

    Badge(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static Badge fromBenefit(int benefitSum) {
        if (benefitSum >= 20_000) {
            return SANTA;
        }
        if (benefitSum >= 10_000) {
            return TREE;
        }
        if (benefitSum >= 5_000) {
            return STAR;
        }
        return NONE;
    }
}
