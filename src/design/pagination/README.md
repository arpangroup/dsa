https://www.reddit.com/r/leetcode/comments/1dn2o87/coinbase0624/?rdt=44841


## Filter, Pagination:

1. Given a bunch of transactions with fields like `id`, `userid`, `currency`, `amount`, `time` etc... implement filters on it.
2. In the second part I was expected to implement pagination with the best techniques and explain different strategies like `curson pagination`, limit/offset etc.
3. There will also be questions about **handling streaming data**
4. Follow-up questions include implementing features to cancel and pause transactions, as well as a feature to cancel all transactions for a particular user.

The task involves writing a function to display Bitcoin transaction history. The function must adhere to specific formatting requirements. 

### Limit/Offset Example (SQL Query)
````sql
SELECT * FROM transactions
ORDER BY timestamp
LIMIT 10
OFFSET 100000;
````

- offset (# of records) + limit
Limit/Offset
````java
transactions.stream()
    .skip(offset)
    .limit(limit)
    .collect(Collectors.toList());
````

### Cursor Pagination Example (SQL Query):
````sql
SELECT * FROM transactions
WHERE timestamp > '2024-09-30 12:00:00'
ORDER BY timestamp
LIMIT 10;
````
````java
Optional<Transaction> lastSeenTransaction = transactions.stream().filter(t -> t.getId.equals(lastSeenId)).findFirst();
if(lastSeenTransaction.isPresent()) {
    int startIndex = transactions.indexOf(lastSeenTransaction.get()) + 1;
    return transactions.stream()
        .skip(startIndex)
        .limit(limit)
        .collect(Collectors.toList());
}
````

### Filter +  Sort + Pagination
````sql
SELECT * FROM transactions
WHERE id > 'lastTransactionId'
ORDER BY id ASC
LIMIT 10
````