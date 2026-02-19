
package com.atmsystem.dispenser;

import java.util.*;

/**
 * Transaction-safe CashDispenser.
 * Uses two-phase logic:
 * 1. Calculate distribution (no mutation)
 * 2. Commit deduction if valid
 */
public class CashDispenser {

    private final Map<Integer, Integer> notes =
            new TreeMap<>(Comparator.reverseOrder());

    public CashDispenser() {
        notes.put(2000, 5);
        notes.put(500, 10);
        notes.put(100, 20);
    }

    // Compute Distribution Without Mutating
    public Map<Integer, Integer> calculateDistribution(int amount) {
        int remaining = amount;
        Map<Integer, Integer> distribution = new HashMap<>();

        for (Map.Entry<Integer, Integer> entry : notes.entrySet()) {
            int denomination = entry.getKey();
            int available = entry.getValue();

            int required = remaining / denomination;
            int toUse = Math.min(required, available);

            if (toUse > 0) {
                distribution.put(denomination, toUse);
                remaining -= toUse * denomination;
            }
        }

        if (remaining != 0) {
            throw new RuntimeException("Cannot dispense exact amount");
        }

        return distribution;
    }

    public synchronized boolean canDispense(int amount) {
        try {
            // Phase 1: Validation
            calculateDistribution(amount);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public synchronized void dispenseCash(int amount) {
        Map<Integer, Integer> distribution = calculateDistribution(amount);

        // Phase 2: Commit
        for (Map.Entry<Integer, Integer> entry : distribution.entrySet()) {
            int denomination = entry.getKey();
            int used = entry.getValue();
            notes.put(denomination, notes.get(denomination) - used);
            System.out.println("Dispensing " + used + " notes of " + denomination);
        }
    }
}
