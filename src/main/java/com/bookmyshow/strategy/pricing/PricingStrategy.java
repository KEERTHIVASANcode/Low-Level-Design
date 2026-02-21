
package com.bookmyshow.strategy.pricing;

import com.bookmyshow.model.Seat;
import java.util.List;

/**
 * Strategy interface for seat pricing.
 */
public interface PricingStrategy {
    double calculatePrice(List<Seat> seats);
}
