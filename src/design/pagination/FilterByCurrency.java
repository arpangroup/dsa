package design.pagination;

import java.util.function.Predicate;

public class FilterByCurrency implements FilterStrategy {
    private String currency;

    public FilterByCurrency(String currency) {
        this.currency = currency;
    }


    @Override
    public Predicate<Transaction> getCriteria() {
        return t -> t.getId().equals(currency);
    }
}
