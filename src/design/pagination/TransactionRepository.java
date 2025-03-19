package design.pagination;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

    // First the first page of transactions
    public List<Transaction> findFirstTransactions(int pageSize, String sortField, boolean ascending) {
        List<Transaction> transactions = sortField != null ? sortedTransactions(this.transactions, sortField, ascending) : this.transactions;
        return transactions.subList(0, Math.min(pageSize, transactions.size()));
    }

    // Fetch transaction after a specific transaction ID (simulate DB call)
    public List<Transaction> findTransactionAfterId(String lastTransactionId, int pageSize, String sortField, boolean ascending) {

        int startIndex = 0;
        for (int i = 0; i<transactions.size(); i++) {
            if (transactions.get(i).getId().equals(lastTransactionId)) {
                startIndex = i+1;
                break;
            }
        }
        return transactions.subList(startIndex, Math.min(startIndex + pageSize, transactions.size()));
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


    private List<Transaction> sortedTransactions(List<Transaction> transactions, String sortField, boolean ascending) {
        return transactions;
    }
}
