package design.pagination;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class TransactionFilterProcessor {
    public List<Transaction> filterTransactions(List<Transaction> transactions, List<FilterStrategy> strategies) {
        return transactions.stream()
                .filter(strategies.stream().map(FilterStrategy::getCriteria).reduce(x -> true, Predicate::and))
                .collect(Collectors.toList());
    }
}
