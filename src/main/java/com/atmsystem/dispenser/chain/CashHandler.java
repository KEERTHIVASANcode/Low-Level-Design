
package com.atmsystem.dispenser.chain;

/**
 * Abstract handler for Chain of Responsibility.
 * Each denomination handler processes part of the amount.
 */
public abstract class CashHandler {

    protected CashHandler nextHandler;
    protected int denomination;
    protected int noteCount;

    public CashHandler(int denomination, int noteCount) {
        this.denomination = denomination;
        this.noteCount = noteCount;
    }

    public void setNextHandler(CashHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    /**
     * Dry run calculation without mutating state.
     */
    public int calculate(int amount) {
        int remaining = amount;

        if (remaining >= denomination) {
            int required = remaining / denomination;
            int usable = Math.min(required, noteCount);
            remaining -= usable * denomination;
        }

        if (nextHandler != null) {
            return nextHandler.calculate(remaining);
        }

        return remaining;
    }

    /**
     * Commit deduction phase.
     */
    public void dispense(int amount) {

        if (amount >= denomination) {
            int required = amount / denomination;
            int usable = Math.min(required, noteCount);
            noteCount -= usable;
            amount -= usable * denomination;

            if (usable > 0) {
                System.out.println("Dispensing " + usable + " notes of " + denomination);
            }
        }

        if (amount > 0 && nextHandler != null) {
            nextHandler.dispense(amount);
        } else if (amount > 0) {
            throw new RuntimeException("Cannot dispense full amount");
        }
    }
}
