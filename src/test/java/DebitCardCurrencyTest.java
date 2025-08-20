package org.example.product;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class DebitCardCurrencyTest {
    private DebitCard card;

    @BeforeEach
    void setUp() {
        card = new DebitCard("MultiCurrency", Currency.GBP, BigDecimal.valueOf(200));
    }

    @Test
    @DisplayName("Пополнение увеличивает баланс")
    public void depositIncreasesBalance() {
        card.deposit(BigDecimal.valueOf(100));
        assertEquals(BigDecimal.valueOf(300), card.getBalance());
    }

    @Test
    @DisplayName("Списание уменьшает баланс")
    public void withdrawalReducesBalance() {
        card.withdraw(BigDecimal.valueOf(50));
        assertEquals(BigDecimal.valueOf(150), card.getBalance());
    }
}