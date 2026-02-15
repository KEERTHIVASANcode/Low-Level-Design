package com.vendingmachine.payment;

public class CashPayment implements PaymentStrategy {

    private double insertedAmount;

    public void insertCash(double amount) {
        insertedAmount += amount;
    }

    @Override
    public PaymentResult pay(double amount) {
        if (insertedAmount < amount) {
            return new CashPaymentResult(false,
                    "Insufficient cash",
                    insertedAmount);
        }

        double change = insertedAmount - amount;
        insertedAmount = 0;

        return new CashPaymentResult(true,
                "Payment successful",
                change);
    }
}