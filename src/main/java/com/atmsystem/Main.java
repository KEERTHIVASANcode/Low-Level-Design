package com.atmsystem;

import com.atmsystem.atm.ATM;
import com.atmsystem.dispenser.CashDispenser;
import com.atmsystem.model.Account;
import com.atmsystem.model.Card;

public class Main {

    public static void main(String[] args) {

        Account account = new Account("ACC123", 10000);
        Card card = new Card("CARD123", "1234", account);
        CashDispenser dispenser = new CashDispenser();

        ATM atm = new ATM(dispenser);

        atm.insertCard(card);
        atm.authenticate("1234");
        atm.withdraw(3700);
        atm.deposit(500);
        atm.ejectCard();
    }
}
