package design.pagination;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.TemporalField;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TransactionRepository {
    // Sample Transactions
    List<Transaction> transactions = List.of(
            new Transaction("T1", "U1", "USD", new BigDecimal("100.50")),
            new Transaction("T2", "U2", "EUR", new BigDecimal("200.75")),
            new Transaction("T3", "U3", "USD", new BigDecimal("50.50")),
            new Transaction("T4", "U4", "INR", new BigDecimal("5000.50")),
            new Transaction("T5", "U5", "GBP", new BigDecimal("5000.50")),
            new Transaction("T6", "U6", "USD", new BigDecimal("5000.50"))
    );

    // Apply filters based on a Map of filter criteria
    public List<Transaction> filterTransactions(Map<String, String> filters) {
        Stream<Transaction> transactionStream = transactions.stream();

        // Apply each filter condition
        if (filters.containsKey("userId")) {
            transactionStream = transactionStream.filter(t -> t.getUserId().equals(filters.get("userId")));
        }
        if (filters.containsKey("currency")) {
            transactionStream = transactionStream.filter(t -> t.getCurrency().equals(filters.get("currency")));
        }
        if (filters.containsKey("amountMin")) {
            transactionStream = transactionStream.filter(t -> t.getAmount().longValue() >= Long.parseLong(filters.get("amountMin")));
        }
        return transactionStream.collect(Collectors.toList());
    }

    // First the first page of sorted transactions
    public List<Transaction> findFirstTransactions(int pageSize, String sortField, boolean ascending) {
        List<Transaction> transactions = sortField != null ? sortTransactions(this.transactions, sortField, ascending) : this.transactions;
        return transactions.subList(0, Math.min(pageSize, transactions.size()));
    }

    // Fetch transaction after a specific transaction ID (simulate DB call)
    public List<Transaction> findTransactionAfterId(String lastTransactionId, int pageSize, String sortField, boolean ascending) {
        List<Transaction> sortedTransactions = sortTransactions(transactions, sortField, ascending);
       /* int startIndex = 0;
        for (int i = 0; i<transactions.size(); i++) {
            if (transactions.get(i).getId().equals(lastTransactionId)) {
                startIndex = i+1;
                break;
            }
        }*/
        int startIndex = findTransactionIndexById(sortedTransactions, lastTransactionId);
        return sortedTransactions.subList(startIndex, Math.min(startIndex + pageSize, transactions.size()));
    }

    // Fetch transaction after a specific transaction ID (simulate DB call)
    public List<Transaction> findTransactionAfterId(String lastTransactionId, int pageSize, String sortField) {
        int startIndex = 0;
        for (int i = 0; i<transactions.size(); i++) {
            if (transactions.get(i).getId().equals(lastTransactionId)) {
                startIndex = i+1;
                break;
            }
        }
        return transactions.subList(startIndex, Math.min(startIndex + pageSize, transactions.size()));
    }


    // Helper method to sort transactions by a field (ID, Amount, etc...)
    public List<Transaction> sortTransactions(List<Transaction> transactions, String sortField, boolean ascending) {
        Comparator<Transaction> comparator = getComparatorField(sortField);
        if (!ascending) {
            comparator = comparator.reversed();
        }
        return transactions.stream().sorted(comparator).collect(Collectors.toList());
    }

    // Get the comparator for a specific field
    private Comparator<Transaction> getComparatorField(String sortField) {
        switch (sortField) {
            case "amount":
                return Comparator.comparing(Transaction::getAmount);
            case "id":
                return Comparator.comparing(Transaction::getId);
            default:
                throw new IllegalArgumentException("unknown sort field: " + sortField);
        }
    }

    private int findTransactionIndexById(List<Transaction> transactions, String transactionId) {
        for (int i = 0; i < transactions.size(); i++) {
            if (transactions.get(i).getId().equals(transactionId)) {
                return i;
            }
        }
        return -1;
    }

    public List<Transaction> findTransactionsAfterTimestamp(long lastTimestamp, int pageSize) {
        return transactions.stream()
                .filter(t -> t.getTimestamp() > lastTimestamp)
                .sorted(Comparator.comparingLong(Transaction::getTimestamp))
                .limit(pageSize)
                .collect(Collectors.toList());
    }
}
