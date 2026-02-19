
package com.atmsystem.model;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Represents a bank account.
 * Thread-safe using explicit locking.
 */
public class Account {

    private final String accountNumber;
    private double balance;
    private final ReentrantLock lock = new ReentrantLock();

    public Account(String accountNumber, double initialBalance) {
        this.accountNumber = accountNumber;
        this.balance = initialBalance;
    }

    public void lock() { lock.lock(); }
    public void unlock() { lock.unlock(); }

    public double getBalance() { return balance; }

    public void debit(double amount) {
        if (balance < amount) {
            throw new RuntimeException("Insufficient funds");
        }
        balance -= amount;
    }

    public void credit(double amount) {
        balance += amount;
    }

    public String getAccountNumber() {
        return accountNumber;
    }
}
