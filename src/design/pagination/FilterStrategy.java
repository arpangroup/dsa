package design.pagination;

import java.util.function.Predicate;

public interface FilterStrategy {
    Predicate<Transaction> getCriteria();
}
