package christmas.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class EventCalculator {

    private static final int D_DAY_BASE = 1000;
    private static final int D_DAY_INCREMENT = 100;
    private static final int WEEK_DISCOUNT = 2023;
    private static final int SPECIAL_DISCOUNT = 1000;
    private static final int GIFT_THRESHOLD = 120_000;
    private static final int EVENT_MIN_PRICE = 10_000;
    private static final Set<Integer> SPECIAL_DAYS = Set.of(3, 10, 17, 24, 25, 31);

    public void validateDay(int day) {
        VisitDay.from(day);
    }

    public EventResult calculate(int day, Order order) {
        VisitDay visitDay = VisitDay.from(day);
        int totalPrice = order.getTotalPrice();

        List<Benefit> benefits = new ArrayList<>();
        int discountAmount = calculateDiscounts(visitDay, order, totalPrice, benefits);
        int giftAmount = addGiftBenefit(totalPrice, benefits);
        Badge badge = Badge.fromBenefit(discountAmount + giftAmount);
        return new EventResult(totalPrice, discountAmount, giftAmount, benefits, badge);
    }

    private int calculateDiscounts(VisitDay visitDay, Order order, int totalPrice, List<Benefit> benefits) {
        if (totalPrice < EVENT_MIN_PRICE) {
            return 0;
        }
        int discount = 0;
        discount += addDDayBenefit(visitDay.getDay(), benefits);
        discount += addWeekBenefit(visitDay, order, benefits);
        discount += addSpecialBenefit(visitDay.getDay(), benefits);
        return discount;
    }

    private int addDDayBenefit(int day, List<Benefit> benefits) {
        if (day > 25) {
            return 0;
        }
        int amount = D_DAY_BASE + ((day - 1) * D_DAY_INCREMENT);
        benefits.add(new Benefit("크리스마스 디데이 할인", amount));
        return amount;
    }

    private int addWeekBenefit(VisitDay visitDay, Order order, List<Benefit> benefits) {
        if (visitDay.isWeekend()) {
            return addMainBenefit(order, benefits);
        }
        return addDessertBenefit(order, benefits);
    }

    private int addMainBenefit(Order order, List<Benefit> benefits) {
        int count = order.getMainCount();
        if (count == 0) {
            return 0;
        }
        int amount = count * WEEK_DISCOUNT;
        benefits.add(new Benefit("주말 할인", amount));
        return amount;
    }

    private int addDessertBenefit(Order order, List<Benefit> benefits) {
        int count = order.getDessertCount();
        if (count == 0) {
            return 0;
        }
        int amount = count * WEEK_DISCOUNT;
        benefits.add(new Benefit("평일 할인", amount));
        return amount;
    }

    private int addSpecialBenefit(int day, List<Benefit> benefits) {
        if (!SPECIAL_DAYS.contains(day)) {
            return 0;
        }
        benefits.add(new Benefit("특별 할인", SPECIAL_DISCOUNT));
        return SPECIAL_DISCOUNT;
    }

    private int addGiftBenefit(int totalPrice, List<Benefit> benefits) {
        if (totalPrice < GIFT_THRESHOLD) {
            return 0;
        }
        benefits.add(new Benefit("증정 이벤트", Menu.CHAMPAGNE.getPrice()));
        return Menu.CHAMPAGNE.getPrice();
    }
}
