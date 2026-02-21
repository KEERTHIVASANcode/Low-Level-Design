
package com.bookmyshow.strategy.pricing;

import com.bookmyshow.model.Seat;
import com.bookmyshow.enums.SeatType;
import java.util.List;

/**
 * Default implementation of pricing strategy.
 */
public class DefaultPricingStrategy implements PricingStrategy {

    @Override
    public double calculatePrice(List<Seat> seats) {
        double total = 0;

        for (Seat seat : seats) {
            if (seat.getSeatType() == SeatType.SILVER) total += 150;
            if (seat.getSeatType() == SeatType.GOLD) total += 250;
            if (seat.getSeatType() == SeatType.PLATINUM) total += 350;
        }

        return total;
    }
}
