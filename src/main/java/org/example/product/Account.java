package org.example.product;

import org.example.service.ProductValidator;

import java.math.BigDecimal;

public abstract class Account implements BankProduct, Transactional {
    protected final String name;
    protected final Currency currency;
    protected BigDecimal balance;

    public Account(String name, Currency currency, BigDecimal balance) {
        ProductValidator.requirePositiveOrZero(balance, "Баланс не может быть отрицательным");
        this.name = name;
        this.currency = currency;
        this.balance = balance;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Currency getCurrency() {
        return currency;
    }

    @Override
    public BigDecimal getBalance() {
        return balance;
    }
}