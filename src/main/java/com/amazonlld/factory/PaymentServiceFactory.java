
package com.amazonlld.factory;

import com.amazonlld.payment.*;

/**
 * Factory Pattern to create PaymentService dynamically.
 */
public class PaymentServiceFactory {

    public static PaymentService getPaymentService(String method) {
        if ("CREDIT_CARD".equalsIgnoreCase(method)) {
            return new CreditCardPaymentService();
        }
        throw new IllegalArgumentException("Unsupported payment method");
    }
}
