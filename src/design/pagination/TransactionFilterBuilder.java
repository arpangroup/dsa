package design.pagination;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TransactionFilterBuilder {
    private List<FilterStrategy> filterStrategies = new ArrayList<>();

    public TransactionFilterBuilder filterById(String id) {
        filterStrategies.add(new FilterById(id));
        return this;
    }

    public TransactionFilterBuilder filterByUserId(String userId) {
        filterStrategies.add(new FilterByUserId(userId));
        return this;
    }

    public TransactionFilterBuilder filterByCurrency(String currency) {
        filterStrategies.add(new FilterByCurrency(currency));
        return this;
    }

    public TransactionFilterBuilder filterByAmountRange(BigDecimal minAmount, BigDecimal maxAmount) {
        filterStrategies.add(new FilterByAmountRange(minAmount, maxAmount));
        return this;
    }

    public TransactionFilterBuilder filterByTimeRange(LocalDateTime startTime, LocalDateTime endTime) {
        filterStrategies.add(new FilterByTimeRange(startTime, endTime));
        return this;
    }

    public List<FilterStrategy> build() {
        return filterStrategies;
    }
}
