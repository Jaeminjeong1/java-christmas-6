package christmas.domain;

import java.util.List;

public class EventResult {

    private final int totalPrice;
    private final int discountAmount;
    private final int giftAmount;
    private final List<Benefit> benefits;
    private final Badge badge;

    public EventResult(int totalPrice, int discountAmount, int giftAmount, List<Benefit> benefits, Badge badge) {
        this.totalPrice = totalPrice;
        this.discountAmount = discountAmount;
        this.giftAmount = giftAmount;
        this.benefits = benefits;
        this.badge = badge;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public int getDiscountAmount() {
        return discountAmount;
    }

    public int getGiftAmount() {
        return giftAmount;
    }

    public List<Benefit> getBenefits() {
        return benefits;
    }

    public Badge getBadge() {
        return badge;
    }

    public int getTotalBenefit() {
        return discountAmount + giftAmount;
    }

    public int getExpectedPayment() {
        return totalPrice - discountAmount;
    }
}
