package design.pagination;

import java.util.function.Predicate;

public class FilterById implements FilterStrategy{
    private String id;

    public FilterById(String id) {
        this.id = id;
    }

    @Override
    public Predicate<Transaction> getCriteria() {
        return t -> t.getId().equals(id);
    }
}
