package org.example.service;

import java.math.BigDecimal;

public final class ProductValidator {

    private ProductValidator() {
        throw new AssertionError("Утилитарный класс не должен инстанцироваться");
    }

    private static final String ERR_POSITIVE = "Сумма должна быть положительной";
    private static final String ERR_NON_NULL = "Значение не может быть null";

    public static void requirePositive(BigDecimal amount) {
        if (amount == null) {
            throw new IllegalArgumentException(ERR_NON_NULL);
        }
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException(ERR_POSITIVE);
        }
    }

    public static void requirePositiveOrZero(BigDecimal amount, String message) {
        if (amount == null) {
            throw new IllegalArgumentException(ERR_NON_NULL);
        }
        if (amount.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException(message);
        }
    }

    public static void checkClosed(boolean isClosed, String message) {
        if (isClosed) {
            throw new IllegalStateException(message);
        }
    }
}