
package com.amazonlld.payment;

public class PaymentRequest {

    private final String userId;
    private final double amount;
    private final String paymentMethod;

    public PaymentRequest(String userId, double amount, String paymentMethod) {
        this.userId = userId;
        this.amount = amount;
        this.paymentMethod = paymentMethod;
    }

    public String getUserId() { return userId; }
    public double getAmount() { return amount; }
    public String getPaymentMethod() { return paymentMethod; }
}
