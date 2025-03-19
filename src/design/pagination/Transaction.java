package design.pagination;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Transaction {
    private String id;
    private String userId;
    private String currency;
    private BigDecimal amount;
    private LocalDateTime time;

    public Transaction(String id, String userId, String currency, BigDecimal amount) {
        this.id = id;
        this.userId = userId;
        this.currency = currency;
        this.amount = amount;
    }

    public Transaction(String id, String userId, String currency, BigDecimal amount, LocalDateTime time) {
       this(id, userId, currency, amount);
        this.time = time;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "{" +
                "id='" + id + '\'' +
                ", userId='" + userId + '\'' +
                ", currency='" + currency + '\'' +
                ", amount=" + amount +
                ", time=" + time +
                '}';
    }
}
