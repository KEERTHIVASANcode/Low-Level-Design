
package com.splitwise.service;

import com.splitwise.concurrency.UserLockManager;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Maintains pairwise balances.
 * Invariant:
 *  balances[A][B] = -balances[B][A]
 */
public class BalanceSheet {

    private final Map<String, Map<String, Long>> balances = new ConcurrentHashMap<>();
    private final UserLockManager lockManager;

    public BalanceSheet(UserLockManager lockManager) {
        this.lockManager = lockManager;
    }

    public void updateBalance(String from, String to, long amount) {

        if (from.equals(to)) return;

        lockManager.lockUsers(from, to);
        try {
            balances.putIfAbsent(from, new ConcurrentHashMap<>());
            balances.putIfAbsent(to, new ConcurrentHashMap<>());

            balances.get(from).merge(to, amount, Long::sum);
            balances.get(to).merge(from, -amount, Long::sum);

        } finally {
            lockManager.unlockUsers(from, to);
        }
    }

    public Map<String, Map<String, Long>> getAllBalances() {
        return balances;
    }
}
