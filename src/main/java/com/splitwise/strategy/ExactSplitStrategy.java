
package com.splitwise.strategy;

import com.splitwise.model.User;
import java.util.List;

/**
 * Exact split implementation.
 * inputValues contains exact share per participant.
 */
public class ExactSplitStrategy implements SplitStrategy {

    @Override
    public List<Long> calculateSplit(
            long totalAmount,
            List<User> participants,
            List<Long> exactValues
    ) {
        long sum = exactValues.stream().mapToLong(Long::longValue).sum();

        if (sum != totalAmount) {
            throw new IllegalArgumentException("Exact split does not sum to total amount");
        }

        return exactValues;
    }
}
