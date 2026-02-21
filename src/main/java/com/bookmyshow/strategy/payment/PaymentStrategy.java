
package com.bookmyshow.strategy.payment;

/**
 * Strategy interface for payment processing.
 */
public interface PaymentStrategy {
    boolean pay(double amount);
}
