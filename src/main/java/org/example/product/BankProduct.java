package org.example.product;

import java.math.BigDecimal;

public interface BankProduct {
    String getName();
    Currency getCurrency();
    BigDecimal getBalance();
}