package org.example.product;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class CreditCardTest {
    private CreditCard card;

    @BeforeEach
    void setUp() {
        card = new CreditCard(
                "Gold",
                Currency.USD,
                BigDecimal.valueOf(1000),   // баланс
                BigDecimal.valueOf(500),    // кредитный лимит
                BigDecimal.valueOf(0.15)    // процентная ставка
        );
    }

    @Test
    @DisplayName("Списание средств уменьшает баланс")
    public void withdrawalReducesBalance() {
        card.withdraw(BigDecimal.valueOf(200));
        assertEquals(BigDecimal.valueOf(800), card.getBalance());
    }

    @Test
    @DisplayName("Списание средств увеличивает долг")
    public void withdrawalIncreasesDebt() {
        card.withdraw(BigDecimal.valueOf(200));
        assertEquals(BigDecimal.valueOf(200), card.getDebt());
    }

    @Test
    @DisplayName("Пополнение гасит долг полностью")
    public void depositClearsDebtCompletely() {
        card.withdraw(BigDecimal.valueOf(300));
        card.deposit(BigDecimal.valueOf(400));
        assertEquals(BigDecimal.ZERO, card.getDebt());
    }

    @Test
    @DisplayName("Пополнение частично гасит долг")
    public void depositPartiallyClearsDebt() {
        card.withdraw(BigDecimal.valueOf(300));
        card.deposit(BigDecimal.valueOf(100));
        assertEquals(BigDecimal.valueOf(200), card.getDebt());
    }

    @Test
    @DisplayName("Пополнение после погашения долга увеличивает баланс")
    public void depositAfterDebtClearanceIncreasesBalance() {
        card.withdraw(BigDecimal.valueOf(300));
        card.deposit(BigDecimal.valueOf(400));
        assertEquals(BigDecimal.valueOf(800), card.getBalance());
    }

    @Test
    @DisplayName("Отрицательное списание вызывает исключение")
    public void negativeWithdrawalThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            card.withdraw(BigDecimal.valueOf(-50));
        });
    }

    @Test
    @DisplayName("Списание выше доступного лимита вызывает исключение")
    public void withdrawalExceedingLimitThrowsException() {
        assertThrows(IllegalStateException.class, () -> {
            card.withdraw(BigDecimal.valueOf(1600)); // 1000 + 500 = 1500 максимум
        });
    }
}