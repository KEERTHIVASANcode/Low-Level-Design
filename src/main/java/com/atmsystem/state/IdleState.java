
package com.atmsystem.state;

import com.atmsystem.atm.ATM;
import com.atmsystem.model.Card;

public class IdleState implements ATMState {

    @Override
    public void insertCard(ATM atm, Card card) {
        atm.setCurrentCard(card);
        atm.setState(new CardInsertedState());
        System.out.println("Card inserted");
    }

    public void authenticate(ATM atm, String pin) { throw new RuntimeException("Insert card first"); }
    public void withdraw(ATM atm, double amount) { throw new RuntimeException("Insert card first"); }
    public void deposit(ATM atm, double amount) { throw new RuntimeException("Insert card first"); }
    public void ejectCard(ATM atm) { throw new RuntimeException("No card inserted"); }
}
