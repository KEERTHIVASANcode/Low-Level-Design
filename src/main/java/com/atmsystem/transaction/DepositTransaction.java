
package com.atmsystem.transaction;

import com.atmsystem.model.Account;

/**
 * Deposit transaction.
 */
public class DepositTransaction extends Transaction {

    public DepositTransaction(Account account, double amount) {
        super(account, amount);
    }

    @Override
    public void execute() {
        account.lock();
        try {
            account.credit(amount);
        } finally {
            account.unlock();
        }
    }
}
