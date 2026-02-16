
package com.splitwise.strategy;

import com.splitwise.model.User;
import java.util.List;

/**
 * Strategy interface for computing splits.
 * Uses smallest currency unit (long).
 */
public interface SplitStrategy {

    List<Long> calculateSplit(
            long totalAmount,
            List<User> participants,
            List<Long> inputValues // optional for exact/percentage
    );
}
