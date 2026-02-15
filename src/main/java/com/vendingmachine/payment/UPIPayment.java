package com.vendingmachine.payment;

import java.util.UUID;

public class UPIPayment implements PaymentStrategy {

    @Override
    public PaymentResult pay(double amount) {
        return new UPIPaymentResult(
                true,
                "UPI Payment successful",
                UUID.randomUUID().toString()
        );
    }
}