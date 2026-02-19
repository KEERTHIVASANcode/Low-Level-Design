package com.atmsystem.atm;

import com.atmsystem.dispenser.CashDispenser;
import com.atmsystem.model.Card;
import com.atmsystem.state.*;

public class ATM {

    private ATMState currentState;
    private Card currentCard;
    private final CashDispenser dispenser;

    public ATM(CashDispenser dispenser) {
        this.dispenser = dispenser;
        this.currentState = new IdleState();
    }

    public void setState(ATMState state) { this.currentState = state; }
    public void setCurrentCard(Card card) { this.currentCard = card; }
    public Card getCurrentCard() { return currentCard; }
    public CashDispenser getDispenser() { return dispenser; }

    public void insertCard(Card card) { currentState.insertCard(this, card); }
    public void authenticate(String pin) { currentState.authenticate(this, pin); }
    public void withdraw(double amount) { currentState.withdraw(this, amount); }
    public void deposit(double amount) { currentState.deposit(this, amount); }
    public void ejectCard() { currentState.ejectCard(this); }
}
