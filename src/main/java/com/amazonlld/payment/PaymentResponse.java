
package com.amazonlld.payment;

public class PaymentResponse {

    private final PaymentStatus status;
    private final String transactionId;

    public PaymentResponse(PaymentStatus status, String transactionId) {
        this.status = status;
        this.transactionId = transactionId;
    }

    public boolean isSuccess() {
        return status == PaymentStatus.SUCCESS;
    }

    public PaymentStatus getStatus() { return status; }
    public String getTransactionId() { return transactionId; }
}
