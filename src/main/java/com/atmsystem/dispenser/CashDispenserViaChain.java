
package com.atmsystem.dispenser;

import com.atmsystem.dispenser.chain.*;

/**
 * CashDispenser using Chain of Responsibility.
 * Uses two-phase logic: calculate first, then commit.
 */
public class CashDispenserViaChain {

    private final CashHandler chain;

    public CashDispenserViaChain() {

        CashHandler twoThousand = new TwoThousandHandler(5);
        CashHandler fiveHundred = new FiveHundredHandler(10);
        CashHandler hundred = new HundredHandler(20);

        twoThousand.setNextHandler(fiveHundred);
        fiveHundred.setNextHandler(hundred);

        this.chain = twoThousand;
    }

    public synchronized boolean canDispense(int amount) {
        int remaining = chain.calculate(amount);
        return remaining == 0;
    }

    public synchronized void dispenseCash(int amount) {

        if (!canDispense(amount)) {
            throw new RuntimeException("ATM cannot dispense requested amount");
        }

        chain.dispense(amount);
    }
}
