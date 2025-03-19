package design.pagination;

import java.util.List;

public class TransactionPagination {
    private TransactionRepository transactionRepository;

    public TransactionPagination(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public List<Transaction> getTransactionsWithCursorPagination(String lastTransactionId, int pageSize) {
        // if transactionId is null, fetch from the beginning
        if (lastTransactionId == null || lastTransactionId.isEmpty()) {
            return transactionRepository.findFirstTransactions(pageSize, null, false);
        }

        // Fetch transactions starting after the last transactionId
        return transactionRepository.findTransactionAfterId(lastTransactionId, pageSize, null, false);
    }

    public List<Transaction> getTransactionsWithCursorPagination(String lastTransactionId, int pageSize, String sortField, boolean ascending) {
        // if transactionId is null, fetch from the beginning
        if (lastTransactionId == null || lastTransactionId.isEmpty()) {
            return transactionRepository.findFirstTransactions(pageSize, sortField, ascending);
        }

        // Fetch transactions starting after the last transactionId
        return transactionRepository.findTransactionAfterId(lastTransactionId, pageSize, sortField, ascending);
    }

}
