
package com.splitwise.strategy;

import com.splitwise.model.User;
import java.util.ArrayList;
import java.util.List;

/**
 * Equal split implementation.
 * Distributes remainder deterministically.
 */
public class EqualSplitStrategy implements SplitStrategy {

    @Override
    public List<Long> calculateSplit(
            long totalAmount,
            List<User> participants,
            List<Long> ignored
    ) {
        int n = participants.size();
        long base = totalAmount / n;
        long remainder = totalAmount % n;

        List<Long> splits = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            long amount = base;
            if (i < remainder) amount += 1;
            splits.add(amount);
        }
        return splits;
    }
}
