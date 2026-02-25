
package com.amazonlld.payment;

import java.util.UUID;

/**
 * Concrete Strategy implementation.
 */
public class CreditCardPaymentService implements PaymentService {

    @Override
    public PaymentResponse processPayment(PaymentRequest request) {
        return new PaymentResponse(
                PaymentStatus.SUCCESS,
                UUID.randomUUID().toString()
        );
    }
}
