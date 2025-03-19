package design.pagination;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.function.Predicate;

public class FilterByAmountRange implements FilterStrategy {
    private BigDecimal minAmount;
    private BigDecimal maxAmount;

    public FilterByAmountRange(BigDecimal minAmount, BigDecimal maxAmount) {
        this.minAmount = minAmount;
        this.minAmount = maxAmount;
    }

    @Override
    public Predicate<Transaction> getCriteria() {
        //return t -> t.getId().equals(userId);
        return null;
    }
}
