
package com.atmsystem.state;

import com.atmsystem.atm.ATM;
import com.atmsystem.model.Card;

public class CardInsertedState implements ATMState {

    private static final int MAX_ATTEMPTS = 3;
    private int attempts = 0;

    @Override
    public void insertCard(ATM atm, Card card) {
        throw new RuntimeException("Card already inserted");
    }

    @Override
    public void authenticate(ATM atm, String pin) {

        Card card = atm.getCurrentCard();

        if (card.validatePin(pin)) {
            atm.setState(new AuthenticatedState());
            System.out.println("Authentication successful");
        } else {
            attempts++;
            System.out.println("Invalid PIN");

            if (attempts >= MAX_ATTEMPTS) {
                atm.setCurrentCard(null);
                atm.setState(new IdleState());
                System.out.println("Card blocked");
            }
        }
    }

    public void withdraw(ATM atm, double amount) { throw new RuntimeException("Authenticate first"); }
    public void deposit(ATM atm, double amount) { throw new RuntimeException("Authenticate first"); }

    @Override
    public void ejectCard(ATM atm) {
        atm.setCurrentCard(null);
        atm.setState(new IdleState());
        System.out.println("Card ejected");
    }
}
