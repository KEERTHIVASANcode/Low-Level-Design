
package com.atmsystem.state;

import com.atmsystem.atm.ATM;
import com.atmsystem.model.Account;
import com.atmsystem.model.Card;
import com.atmsystem.transaction.*;

public class AuthenticatedState implements ATMState {

    public void insertCard(ATM atm, Card card) { throw new RuntimeException("Card already inserted"); }
    public void authenticate(ATM atm, String pin) { throw new RuntimeException("Already authenticated"); }

    @Override
    public void withdraw(ATM atm, double amount) {
        Account account = atm.getCurrentCard().getLinkedAccount();
        Transaction tx = new WithdrawTransaction(account, amount, atm.getDispenser());
        tx.execute();
    }

    @Override
    public void deposit(ATM atm, double amount) {
        Account account = atm.getCurrentCard().getLinkedAccount();
        Transaction tx = new DepositTransaction(account, amount);
        tx.execute();
    }

    @Override
    public void ejectCard(ATM atm) {
        atm.setCurrentCard(null);
        atm.setState(new IdleState());
        System.out.println("Card ejected");
    }
}
