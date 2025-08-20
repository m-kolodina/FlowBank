package org.example.product;

public enum Currency {
    RUB("Российский рубль", "₽"),
    USD("Доллар США", "$"),
    EUR("Евро", "€"),
    GBP("Фунт стерлингов", "£"),
    JPY("Японская иена", "¥"),
    CNY("Китайский юань", "¥");

    private final String displayName;
    private final String symbol;

    Currency(String displayName, String symbol) {
        this.displayName = displayName;
        this.symbol = symbol;
    }

    public String getDisplayName() { return displayName; }
    public String getSymbol() { return symbol; }
}