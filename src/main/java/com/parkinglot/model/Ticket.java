
package com.parkinglot.model;

/**
 * Represents parking transaction record.
 */
public class Ticket {

    private final String ticketId;
    private final String spotId;
    private final String floorId;
    private final String vehicleNumber;
    private final long entryTime;
    private Long exitTime;

    public Ticket(String ticketId, String spotId, String floorId,
                  String vehicleNumber, long entryTime) {
        this.ticketId = ticketId;
        this.spotId = spotId;
        this.floorId = floorId;
        this.vehicleNumber = vehicleNumber;
        this.entryTime = entryTime;
    }

    public void closeTicket(long exitTime) {
        this.exitTime = exitTime;
    }

    public long getEntryTime() {
        return entryTime;
    }

    public long getExitTime() {
        return exitTime;
    }
}
