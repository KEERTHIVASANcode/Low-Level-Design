
package com.atmsystem.state;

import com.atmsystem.atm.ATM;
import com.atmsystem.model.Card;

/**
 * State interface for ATM (State Pattern).
 */
public interface ATMState {

    void insertCard(ATM atm, Card card);
    void authenticate(ATM atm, String pin);
    void withdraw(ATM atm, double amount);
    void deposit(ATM atm, double amount);
    void ejectCard(ATM atm);
}
