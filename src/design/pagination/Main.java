package design.pagination;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

public class Main {
    /*public static void main(String[] args) {
        // Sample Transactions
        List<Transaction> transactions = List.of(
            new Transaction("T1", "U1", "USD", new BigDecimal("100.50"), LocalDateTime.now().minusDays(1)),
            new Transaction("T2", "U2", "EUR", new BigDecimal("200.75"), LocalDateTime.now().minusDays(2)),
            new Transaction("T3", "U3", "USD", new BigDecimal("50.50"), LocalDateTime.now().minusDays(5)),
            new Transaction("T4", "U4", "INR", new BigDecimal("5000.50"), LocalDateTime.now().minusHours(10))
        );

        // Build Filter using Builder Pattern
        TransactionFilterBuilder filterBuilder = new TransactionFilterBuilder()
                .filterById("U1")
                .filterByCurrency("USD")
                .filterByAmountRange(new BigDecimal("50"), new BigDecimal("150"));

        // Apply filter using Strategy Pattern
        TransactionFilterProcessor filterProcessor = new TransactionFilterProcessor();
        List<Transaction> filteredTransactions = filterProcessor.filterTransactions(transactions, filterBuilder.build());

        // Display filtered transactions
        filteredTransactions.forEach(System.out::println);
    }*/

    /*public static void main(String[] args) {
        TransactionRepository transactionRepository = new TransactionRepository();
        TransactionPagination pagination = new TransactionPagination(transactionRepository);

        List<Transaction> firstPage = pagination.getTransactionsWithCursorPagination(null, 10);
        System.out.println("FirstPage: " + firstPage);

        String lastTransactionId = firstPage.get(firstPage.size() - 1).getId();
        List<Transaction> nextPage = pagination.getTransactionsWithCursorPagination(lastTransactionId, 10);
        System.out.println("NextPage: " + nextPage);
    }*/
}
