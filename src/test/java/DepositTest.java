package org.example.product;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class DepositTest {
    private Deposit deposit;

    @BeforeEach
    void setUp() {
        deposit = new Deposit("Saving", Currency.RUB, BigDecimal.valueOf(10000));
    }

    @Test
    @DisplayName("Пополнение увеличивает баланс")
    public void depositIncreasesBalance() {
        deposit.deposit(BigDecimal.valueOf(5000));
        assertEquals(BigDecimal.valueOf(15000), deposit.getBalance());
    }

    @Test
    @DisplayName("Закрытие возвращает сумму")
    public void closeReturnsCorrectAmount() {
        deposit.deposit(BigDecimal.valueOf(3000));
        BigDecimal payout = deposit.close();
        assertEquals(BigDecimal.valueOf(13000), payout);
    }

    @Test
    @DisplayName("После закрытия баланс обнуляется")
    public void balanceIsZeroAfterClose() {
        deposit.deposit(BigDecimal.valueOf(3000));
        deposit.close();
        assertEquals(BigDecimal.ZERO, deposit.getBalance());
    }

    @Test
    @DisplayName("После закрытия вклад помечается как закрытый")
    public void isClosedReturnsTrueAfterClose() {
        deposit.close();
        assertTrue(deposit.isClosed());
    }

    @Test
    @DisplayName("Пополнение после закрытия вызывает исключение")
    public void depositAfterCloseThrowsException() {
        deposit.close();
        assertThrows(IllegalStateException.class, () -> {
            deposit.deposit(BigDecimal.valueOf(1000));
        });
    }

    @Test
    @DisplayName("Отрицательное пополнение вызывает исключение")
    public void negativeDepositThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            deposit.deposit(BigDecimal.valueOf(-1000));
        });
    }
}