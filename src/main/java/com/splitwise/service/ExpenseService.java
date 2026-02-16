
package com.splitwise.service;

import com.splitwise.model.User;
import com.splitwise.strategy.SplitStrategy;

import java.util.List;

/**
 * Handles expense flow:
 * 1. Validate
 * 2. Calculate split via Strategy
 * 3. Update balances
 */
public class ExpenseService {

    private final BalanceSheet balanceSheet;

    public ExpenseService(BalanceSheet balanceSheet) {
        this.balanceSheet = balanceSheet;
    }

    public void addExpense(
            User paidBy,
            long amount,
            List<User> participants,
            SplitStrategy strategy,
            List<Long> inputValues
    ) {

        if (amount <= 0) throw new IllegalArgumentException("Invalid amount");

        List<Long> splits = strategy.calculateSplit(amount, participants, inputValues);

        for (int i = 0; i < participants.size(); i++) {
            User user = participants.get(i);
            long share = splits.get(i);

            if (!user.getId().equals(paidBy.getId())) {
                balanceSheet.updateBalance(user.getId(), paidBy.getId(), share);
            }
        }
    }
}
