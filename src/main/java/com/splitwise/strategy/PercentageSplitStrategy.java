
package com.splitwise.strategy;

import com.splitwise.model.User;
import java.util.ArrayList;
import java.util.List;

/**
 * Percentage split implementation.
 * inputValues contains percentage per participant.
 */
public class PercentageSplitStrategy implements SplitStrategy {

    @Override
    public List<Long> calculateSplit(
            long totalAmount,
            List<User> participants,
            List<Long> percentages
    ) {

        long percentSum = percentages.stream().mapToLong(Long::longValue).sum();

        if (percentSum != 100) {
            throw new IllegalArgumentException("Total percentage must equal 100");
        }

        List<Long> splits = new ArrayList<>();
        long allocated = 0;

        for (int i = 0; i < participants.size(); i++) {
            long amount = (totalAmount * percentages.get(i)) / 100;
            splits.add(amount);
            allocated += amount;
        }

        // Handle rounding remainder
        long remainder = totalAmount - allocated;
        for (int i = 0; i < remainder; i++) {
            splits.set(i, splits.get(i) + 1);
        }

        return splits;
    }
}
