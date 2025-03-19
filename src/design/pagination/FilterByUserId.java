package design.pagination;

import java.util.function.Predicate;

public class FilterByUserId implements FilterStrategy {
    private String userId;

    public FilterByUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public Predicate<Transaction> getCriteria() {
        return t -> t.getId().equals(userId);
    }
}
