
package com.bookmyshow.strategy.payment;

/**
 * UPI payment implementation.
 */
public class UpiPaymentStrategy implements PaymentStrategy {

    @Override
    public boolean pay(double amount) {
        System.out.println("Paid " + amount + " using UPI");
        return true;
    }
}
