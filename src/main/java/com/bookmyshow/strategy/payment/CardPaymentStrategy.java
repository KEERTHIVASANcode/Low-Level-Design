
package com.bookmyshow.strategy.payment;

/**
 * Card payment implementation.
 */
public class CardPaymentStrategy implements PaymentStrategy {

    @Override
    public boolean pay(double amount) {
        System.out.println("Paid " + amount + " using CARD");
        return true;
    }
}
