
package com.splitwise.service;

import java.util.*;

/**
 * Simplifies debts using Min Cash Flow greedy algorithm.
 * Time complexity: O(n log n)
 */
public class SimplifyDebtService {

    public List<String> simplify(Map<String, Map<String, Long>> balances) {

        Map<String, Long> netBalance = new HashMap<>();

        // Compute net balance per user
        for (String user : balances.keySet()) {
            long sum = balances.get(user)
                    .values()
                    .stream()
                    .mapToLong(Long::longValue)
                    .sum();
            netBalance.put(user, sum);
        }

        PriorityQueue<Map.Entry<String, Long>> creditors =
                new PriorityQueue<>((a, b) -> Long.compare(b.getValue(), a.getValue()));

        PriorityQueue<Map.Entry<String, Long>> debtors =
                new PriorityQueue<>(Comparator.comparingLong(Map.Entry::getValue));

        for (Map.Entry<String, Long> entry : netBalance.entrySet()) {
            if (entry.getValue() > 0) creditors.add(entry);
            else if (entry.getValue() < 0) debtors.add(entry);
        }

        List<String> transactions = new ArrayList<>();

        while (!creditors.isEmpty() && !debtors.isEmpty()) {

            var creditor = creditors.poll();
            var debtor = debtors.poll();

            long amount = Math.min(creditor.getValue(), -debtor.getValue());

            transactions.add(
                    debtor.getKey() + " pays " +
                    creditor.getKey() + " : " + amount
            );

            long remainingCred = creditor.getValue() - amount;
            long remainingDebt = debtor.getValue() + amount;

            if (remainingCred > 0)
                creditors.add(Map.entry(creditor.getKey(), remainingCred));

            if (remainingDebt < 0)
                debtors.add(Map.entry(debtor.getKey(), remainingDebt));
        }

        return transactions;
    }
}
