package design.pagination;

import java.util.List;
import java.util.Map;

public class TransactionPagination {
    private TransactionRepository transactionRepository;

    public TransactionPagination(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    // Cursor based pagination with sorting
    public List<Transaction> getTransactionsWithCursorPagination(String lastTransactionId, int pageSize, String sortField, boolean ascending) {
        // if transactionId is null, fetch from the beginning
        if (lastTransactionId == null || lastTransactionId.isEmpty()) {
            return transactionRepository.findFirstTransactions(pageSize, sortField, ascending);
        }

        // Fetch transactions starting after the last transactionId
        return transactionRepository.findTransactionAfterId(lastTransactionId, pageSize, sortField, ascending);
    }

    // Combine filter, Sorting and pagination
    public List<Transaction> getFilteredSortedPaginatedTransactions(Map<String, String> filters, String lastTransactionId, String sortField, boolean ascending, int pageSize) {
        // Filter transactions
        List<Transaction> filteredTransactions = transactionRepository.filterTransactions(filters);

        // Sort the filtered Transactions
        List<Transaction> sortedTransactions = transactionRepository.sortTransactions(filteredTransactions, sortField, ascending);

        // Paginate the sorted transactions
        return paginate(sortedTransactions, lastTransactionId, pageSize);
    }

    private List<Transaction> paginate(List<Transaction> transactions, String lastTransactionId, int pageSize) {
        if (lastTransactionId == null || lastTransactionId.isEmpty()) {
            return transactions.subList(0, Math.min(pageSize, transactions.size()));
        }
        int startIndex = findTransactionIndexById(transactions, lastTransactionId);
        return transactions.subList(startIndex + 1 + pageSize, Math.min(startIndex + 1 + pageSize, transactions.size()));
    }

    private int findTransactionIndexById(List<Transaction> transactions, String transactionId) {
        for (int i = 0; i < transactions.size(); i++) {
            if (transactions.get(i).getId().equals(transactionId)) {
                return i;
            }
        }
        return -1;
    }


}
