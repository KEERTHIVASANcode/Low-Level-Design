
package com.atmsystem.transaction;

import com.atmsystem.dispenser.CashDispenser;
import com.atmsystem.model.Account;

/**
 * Withdraw transaction using atomic execution.
 */
public class WithdrawTransaction extends Transaction {

    private final CashDispenser dispenser;

    public WithdrawTransaction(Account account,
                               double amount,
                               CashDispenser dispenser) {
        super(account, amount);
        this.dispenser = dispenser;
    }

    @Override
    public void execute() {

        account.lock();
        try {
            synchronized (dispenser) {

                if (account.getBalance() < amount) {
                    throw new RuntimeException("Insufficient funds");
                }

                if (!dispenser.canDispense((int) amount)) {
                    throw new RuntimeException("ATM cannot dispense requested amount");
                }

                account.debit(amount);
                dispenser.dispenseCash((int) amount);
            }
        } finally {
            account.unlock();
        }
    }
}
