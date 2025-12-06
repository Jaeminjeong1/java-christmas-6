package christmas.service;

import christmas.domain.EventCalculator;
import christmas.domain.EventResult;
import christmas.domain.Order;
import christmas.dto.BenefitDto;
import christmas.dto.PlannerResultDto;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ChristmasService {

    private final EventCalculator eventCalculator;

    public ChristmasService(EventCalculator eventCalculator) {
        this.eventCalculator = eventCalculator;
    }

    public void validateVisitDay(int day) {
        eventCalculator.validateDay(day);
    }

    public PlannerResultDto calculate(int day, String orderInput) {
        Order order = Order.from(orderInput);
        EventResult eventResult = eventCalculator.calculate(day, order);
        return toPlannerResult(day, order, eventResult);
    }

    private PlannerResultDto toPlannerResult(int day, Order order, EventResult result) {
        return new PlannerResultDto(
            day,
            toOrderMap(order),
            result.getTotalPrice(),
            result.getGiftAmount(),
            toBenefitDtos(result.getBenefits()),
            result.getTotalBenefit(),
            result.getExpectedPayment(),
            result.getBadge().getName()
        );
    }

    private Map<String, Integer> toOrderMap(Order order) {
        return new LinkedHashMap<>(order.getItems().entrySet().stream()
            .collect(Collectors.toMap(entry -> entry.getKey().getName(), Map.Entry::getValue, (a, b) -> a, LinkedHashMap::new)));
    }

    private List<BenefitDto> toBenefitDtos(List<christmas.domain.Benefit> benefits) {
        return benefits.stream()
            .map(benefit -> new BenefitDto(benefit.getName(), benefit.getAmount()))
            .collect(Collectors.toList());
    }
}
