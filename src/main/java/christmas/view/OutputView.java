package christmas.view;

import christmas.dto.BenefitDto;
import java.util.List;
import java.util.Map;

public class OutputView {

    private static final String START_MESSAGE = "안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.";
    private static final String PREVIEW_FORMAT = "12월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!";
    private static final String ORDER_TITLE = "<주문 메뉴>";
    private static final String TOTAL_PRICE_TITLE = "<할인 전 총주문 금액>";
    private static final String GIFT_TITLE = "<증정 메뉴>";
    private static final String BENEFIT_TITLE = "<혜택 내역>";
    private static final String TOTAL_BENEFIT_TITLE = "<총혜택 금액>";
    private static final String EXPECTED_PAYMENT_TITLE = "<할인 후 예상 결제 금액>";
    private static final String BADGE_TITLE = "<12월 이벤트 배지>";
    private static final String GIFT_ITEM = "샴페인 1개";
    private static final String NONE_TEXT = "없음";
    private static final String MONEY_FORMAT = "%,d원";


    private OutputView() {
    }

    public static OutputView create() {
        return new OutputView();
    }

    public void startString() {
        System.out.println(START_MESSAGE);
    }

    public void printPreviewHeader(int day) {
        System.out.println(String.format(PREVIEW_FORMAT, day));
    }

    public void printOrder(Map<String, Integer> items) {
        System.out.println();
        System.out.println(ORDER_TITLE);
        items.forEach((name, count) ->
            System.out.println(name + " " + count + "개")
        );
    }

    public void printTotalPrice(int price) {
        System.out.println();
        System.out.println(TOTAL_PRICE_TITLE);
        System.out.println(formatMoney(price));
    }

    public void printGift(int giftAmount) {
        System.out.println();
        System.out.println(GIFT_TITLE);
        if (giftAmount > 0) {
            System.out.println(GIFT_ITEM);
            return;
        }
        System.out.println(NONE_TEXT);
    }

    public void printBenefits(List<BenefitDto> benefits) {
        System.out.println();
        System.out.println(BENEFIT_TITLE);
        if (benefits.isEmpty()) {
            System.out.println(NONE_TEXT);
            return;
        }
        benefits.forEach(benefit ->
            System.out.println(benefit.getName() + ": -" + formatMoney(benefit.getAmount()))
        );
    }

    public void printTotalBenefit(int totalBenefit) {
        System.out.println();
        System.out.println(TOTAL_BENEFIT_TITLE);
        if (totalBenefit == 0) {
            System.out.println(formatMoney(0));
            return;
        }
        System.out.println("-" + formatMoney(totalBenefit));
    }

    public void printExpectedPayment(int payment) {
        System.out.println();
        System.out.println(EXPECTED_PAYMENT_TITLE);
        System.out.println(formatMoney(payment));
    }

    public void printBadge(String badgeName) {
        System.out.println();
        System.out.println(BADGE_TITLE);
        System.out.println(badgeName);
    }

    public void printError(String message) {
        System.out.println(message);
    }

    private String formatMoney(int amount) {
        return String.format(MONEY_FORMAT, amount);
    }
}
