
package com.splitwise;

import com.splitwise.concurrency.UserLockManager;
import com.splitwise.model.User;
import com.splitwise.service.*;
import com.splitwise.strategy.*;

import java.util.Arrays;
import java.util.List;

/**
 * Demo runner.
 */
public class SplitwiseApplication {

    public static void main(String[] args) {

        UserLockManager lockManager = new UserLockManager();
        BalanceSheet balanceSheet = new BalanceSheet(lockManager);
        ExpenseService expenseService = new ExpenseService(balanceSheet);
        SimplifyDebtService simplifyDebtService = new SimplifyDebtService();

        User u1 = new User("1", "Alice", "a@mail.com");
        User u2 = new User("2", "Bob", "b@mail.com");
        User u3 = new User("3", "Charlie", "c@mail.com");

        expenseService.addExpense(
                u1,
                1000,
                Arrays.asList(u1, u2, u3),
                new EqualSplitStrategy(),
                null
        );

        List<String> simplified =
                simplifyDebtService.simplify(balanceSheet.getAllBalances());

        System.out.println("Simplified Transactions:");
        simplified.forEach(System.out::println);
    }
}
