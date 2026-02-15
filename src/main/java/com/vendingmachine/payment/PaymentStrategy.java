package com.vendingmachine.payment;

public interface PaymentStrategy {
    PaymentResult pay(double amount);
}