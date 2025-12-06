package christmas.dto;

import java.util.List;
import java.util.Map;

public class PlannerResultDto {

    private final int day;
    private final Map<String, Integer> orderItems;
    private final int totalPrice;
    private final int giftAmount;
    private final List<BenefitDto> benefits;
    private final int totalBenefit;
    private final int expectedPayment;
    private final String badgeName;

    public PlannerResultDto(
        int day,
        Map<String, Integer> orderItems,
        int totalPrice,
        int giftAmount,
        List<BenefitDto> benefits,
        int totalBenefit,
        int expectedPayment,
        String badgeName
    ) {
        this.day = day;
        this.orderItems = orderItems;
        this.totalPrice = totalPrice;
        this.giftAmount = giftAmount;
        this.benefits = benefits;
        this.totalBenefit = totalBenefit;
        this.expectedPayment = expectedPayment;
        this.badgeName = badgeName;
    }

    public int getDay() {
        return day;
    }

    public Map<String, Integer> getOrderItems() {
        return orderItems;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public int getGiftAmount() {
        return giftAmount;
    }

    public List<BenefitDto> getBenefits() {
        return benefits;
    }

    public int getTotalBenefit() {
        return totalBenefit;
    }

    public int getExpectedPayment() {
        return expectedPayment;
    }

    public String getBadgeName() {
        return badgeName;
    }
}
