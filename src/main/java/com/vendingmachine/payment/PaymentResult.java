package com.vendingmachine.payment;

public abstract class PaymentResult {

    protected final boolean success;
    protected final String message;

    protected PaymentResult(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }
}