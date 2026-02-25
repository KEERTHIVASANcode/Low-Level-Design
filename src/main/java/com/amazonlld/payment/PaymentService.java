
package com.amazonlld.payment;

public interface PaymentService {
    PaymentResponse processPayment(PaymentRequest request);
}
