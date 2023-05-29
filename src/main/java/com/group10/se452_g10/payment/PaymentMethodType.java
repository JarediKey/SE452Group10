package com.group10.se452_g10.payment;

public enum PaymentMethodType {
    CREDIT_CARD("Credit Card"),
    DEBIT_CARD("Debit Card"),
    NET_BANKING("Net Banking");

    private final String method;

    PaymentMethodType(String method) {
        this.method = method;
    }

    public final String getMethod() {
        return method;
    }
}