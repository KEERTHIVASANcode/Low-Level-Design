
package com.atmsystem.transaction;

import com.atmsystem.model.Account;

/**
 * Abstract Transaction class.
 * Follows Open-Closed Principle.
 */
public abstract class Transaction {

    protected final Account account;
    protected final double amount;

    public Transaction(Account account, double amount) {
        this.account = account;
        this.amount = amount;
    }

    public abstract void execute();
}
