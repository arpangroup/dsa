package design.pagination;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.function.Predicate;

public class FilterByTimeRange implements FilterStrategy {
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public FilterByTimeRange(LocalDateTime startTime, LocalDateTime endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public Predicate<Transaction> getCriteria() {
        //return t -> t.getId().equals(userId);
        return null;
    }
}
