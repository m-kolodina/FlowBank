package org.example.product;

import lombok.Getter;
import org.example.service.ProductValidator;

import java.math.BigDecimal;

@Getter
public class DebitCard extends Account {
    public DebitCard(String name, Currency currency, BigDecimal balance) {
        super(name, currency, balance);
    }

    @Override
    public void deposit(BigDecimal amount) {
        ProductValidator.requirePositive(amount);
        balance = balance.add(amount);
    }

    @Override
    public void withdraw(BigDecimal amount) {
        ProductValidator.requirePositive(amount);
        if (balance.compareTo(amount) < 0) {
            throw new IllegalStateException("Недостаточно средств");
        }
        balance = balance.subtract(amount);
    }
}