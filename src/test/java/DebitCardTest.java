package org.example.product;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class DebitCardTest {
    private DebitCard card;

    @BeforeEach
    void setUp() {
        card = new DebitCard("Standart", Currency.EUR, BigDecimal.valueOf(500));
    }

    @Test
    @DisplayName("Пополнение увеличивает баланс")
    public void depositIncreasesBalance() {
        card.deposit(BigDecimal.valueOf(200));
        assertEquals(BigDecimal.valueOf(700), card.getBalance());
    }

    @Test
    @DisplayName("Списание уменьшает баланс")
    public void withdrawalReducesBalance() {
        card.withdraw(BigDecimal.valueOf(300));  // ← debit → withdraw
        assertEquals(BigDecimal.valueOf(200), card.getBalance());
    }

    @Test
    @DisplayName("Списание превышающее баланс вызывает исключение")
    public void withdrawalExceedingBalanceThrowsException() {
        assertThrows(IllegalStateException.class, () -> {
            card.withdraw(BigDecimal.valueOf(5000));  // ← debit → withdraw
        });
    }

    @Test
    @DisplayName("Отрицательное пополнение вызывает исключение")
    public void negativeDepositThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            card.deposit(BigDecimal.valueOf(-100));
        });
    }
}
