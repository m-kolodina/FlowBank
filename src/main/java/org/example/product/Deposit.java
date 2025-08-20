package org.example.product;

import lombok.Getter;
import org.example.service.ProductValidator;

import java.math.BigDecimal;

@Getter
public class Deposit extends Account {
    private boolean closed = false;

    public Deposit(String name, Currency currency, BigDecimal balance) {
        super(name, currency, balance);
    }

    @Override
    public void deposit(BigDecimal amount) {
        ProductValidator.checkClosed(closed, "Вклад закрыт");
        ProductValidator.requirePositive(amount);
        balance = balance.add(amount);
    }

    @Override
    public void withdraw(BigDecimal amount) {
        throw new UnsupportedOperationException("Снятие с вклада запрещено");
    }

    public BigDecimal close() {
        ProductValidator.checkClosed(closed, "Вклад уже закрыт");
        closed = true;
        BigDecimal payout = balance;
        balance = BigDecimal.ZERO;
        return payout;
    }

    public boolean isClosed() {
        return closed;
    }
}