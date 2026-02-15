
package com.parkinglot.strategy;

import com.parkinglot.model.Ticket;

public class HourlyPricingStrategy implements PricingStrategy {

    private final double hourlyRate;

    public HourlyPricingStrategy(double hourlyRate) {
        this.hourlyRate = hourlyRate;
    }

    @Override
    public double calculatePrice(Ticket ticket) {
        long duration = ticket.getExitTime() - ticket.getEntryTime();
        long hours = (long) Math.ceil(duration / (1000.0 * 60 * 60));
        return hours * hourlyRate;
    }
}
