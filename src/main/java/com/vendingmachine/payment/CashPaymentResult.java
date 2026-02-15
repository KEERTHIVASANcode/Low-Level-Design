package com.vendingmachine.payment;

public class CashPaymentResult extends PaymentResult {

    private final double change;

    public CashPaymentResult(boolean success, String message, double change) {
        super(success, message);
        this.change = change;
    }

    public double getChange() {
        return change;
    }
}