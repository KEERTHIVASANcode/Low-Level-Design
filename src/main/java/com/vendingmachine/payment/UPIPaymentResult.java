package com.vendingmachine.payment;

public class UPIPaymentResult extends PaymentResult {

    private final String transactionId;

    public UPIPaymentResult(boolean success, String message, String transactionId) {
        super(success, message);
        this.transactionId = transactionId;
    }

    public String getTransactionId() {
        return transactionId;
    }
}