package org.example.service;

import org.example.product.BankProduct;
import org.example.product.Deposit;
import org.example.product.Transactional;

import java.math.BigDecimal;

@SuppressWarnings("unused")
public class ProductService {

    public BigDecimal getBalance(BankProduct product) {
        return product.getBalance();
    }

    public void deposit(Transactional product, BigDecimal amount) {
        product.deposit(amount);
    }

    public void withdraw(Transactional product, BigDecimal amount) {
        product.withdraw(amount);
    }

    public BigDecimal closeDeposit(Deposit deposit) {
        return deposit.close();
    }

    public boolean isClosed(Deposit deposit) {
        return deposit.isClosed();
    }
}