package model;

import java.math.BigDecimal;
import java.util.Objects;


public class Account {
    private final Long id;
    private final Long userId;

    private BigDecimal amount;

    public Account(Long id, Long userId, BigDecimal amount) {
        this.id = id;
        this.userId = userId;
        this.amount = amount;
    }

    public Long getId() {
        return id;
    }
    public Long getUserId() {
        return userId;
    }
    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Objects.equals(id, account.getId()) && Objects.equals(userId, account.getUserId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId);
    }

    @Override
    public String toString() {
        return "Account{id=" + id + ", userId=" + userId + ", moneyAmount=" + amount + "}";
    }
}
