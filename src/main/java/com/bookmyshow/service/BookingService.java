
package com.bookmyshow.service;

import com.bookmyshow.enums.PaymentMode;
import com.bookmyshow.model.*;
import com.bookmyshow.strategy.pricing.PricingStrategy;
import com.bookmyshow.strategy.payment.PaymentStrategy;
import com.bookmyshow.factory.PaymentFactory;

import java.util.List;
import java.util.UUID;

/**
 * Main orchestrator service.
 * Coordinates locking, pricing and payment.
 */
public class BookingService {

    private final SeatAvailabilityService seatService;
    private final PricingStrategy pricingStrategy;

    public BookingService(SeatAvailabilityService seatService,
                          PricingStrategy pricingStrategy) {
        this.seatService = seatService;
        this.pricingStrategy = pricingStrategy;
    }

    public Booking book(Show show,
                        List<String> seatIds,
                        PaymentMode paymentMode) {

        boolean locked = seatService.lockSeats(show, seatIds);
        if (!locked) {
            throw new RuntimeException("Seats not available");
        }

        List<Seat> seats = seatIds.stream()
                .map(show::getSeatById)
                .toList();

        double amount = pricingStrategy.calculatePrice(seats);

        PaymentStrategy payment = PaymentFactory.getPaymentStrategy(paymentMode);

        boolean success = payment.pay(amount);

        Booking booking = new Booking(
                UUID.randomUUID().toString(),
                show,
                seatIds
        );

        if (success) {
            seatService.confirmSeats(show, seatIds);
            booking.markConfirmed();
        } else {
            seatService.unlockSeats(show, seatIds);
            booking.markFailed();
        }

        return booking;
    }
}
