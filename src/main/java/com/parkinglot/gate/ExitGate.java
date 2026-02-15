
package com.parkinglot.gate;

import com.parkinglot.ParkingFloor;
import com.parkinglot.ParkingSpot;
import com.parkinglot.model.*;
import com.parkinglot.strategy.PricingStrategy;

public class ExitGate {

    private final PricingStrategy pricingStrategy;

    public ExitGate(PricingStrategy pricingStrategy) {
        this.pricingStrategy = pricingStrategy;
    }

    public double exitVehicle(Ticket ticket,
                              ParkingFloor floor,
                              ParkingSpot spot) {

        ticket.closeTicket(System.currentTimeMillis());

        double amount = pricingStrategy.calculatePrice(ticket);

        spot.unpark();
        floor.freeSpot(spot);

        return amount;
    }
}
