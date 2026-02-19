
package com.atmsystem.model;

/**
 * Represents a debit/credit card.
 * Handles PIN validation.
 */
public class Card {

    private final String cardNumber;
    private final String pin;
    private final Account linkedAccount;

    public Card(String cardNumber, String pin, Account account) {
        this.cardNumber = cardNumber;
        this.pin = pin;
        this.linkedAccount = account;
    }

    public boolean validatePin(String enteredPin) {
        return this.pin.equals(enteredPin);
    }

    public Account getLinkedAccount() {
        return linkedAccount;
    }
}
