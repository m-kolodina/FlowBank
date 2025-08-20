package org.example.product;

import org.example.service.ProductValidator;

import java.math.BigDecimal;

public class CreditCard extends Account {
    private final BigDecimal creditLimit;
    private final BigDecimal interestRate;
    private BigDecimal debt;

    public CreditCard(String name, Currency currency, BigDecimal balance,
                      BigDecimal creditLimit, BigDecimal interestRate) {
        super(name, currency, balance);
        this.creditLimit = creditLimit;
        this.interestRate = interestRate;
        this.debt = BigDecimal.ZERO;
    }

    @Override
    public void deposit(BigDecimal amount) {
        ProductValidator.requirePositive(amount);

        if (debt.compareTo(BigDecimal.ZERO) > 0) {
            if (amount.compareTo(debt) <= 0) {
                debt = debt.subtract(amount);
            } else {
                BigDecimal remainder = amount.subtract(debt);
                debt = BigDecimal.ZERO;
                balance = balance.add(remainder);
            }
        } else {
            balance = balance.add(amount);
        }
    }

    @Override
    public void withdraw(BigDecimal amount) {
        ProductValidator.requirePositive(amount);

        BigDecimal availableFunds = balance.add(creditLimit).subtract(debt);
        if (amount.compareTo(availableFunds) > 0) {
            throw new IllegalStateException("Превышен кредитный лимит");
        }

        balance = balance.subtract(amount);
        debt = debt.add(amount);
    }

    public BigDecimal getDebt() {
        return debt;
    }

    public BigDecimal getInterestRate() {
        return interestRate;
    }
}