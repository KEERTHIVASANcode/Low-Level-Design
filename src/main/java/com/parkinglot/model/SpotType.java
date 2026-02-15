
package com.parkinglot.model;

/**
 * Represents size category of a parking spot.
 * Explicit rank is used instead of ordinal() to avoid enum-order bugs.
 */
public enum SpotType {

    SMALL(1),
    MEDIUM(2),
    LARGE(3);

    private final int sizeRank;

    SpotType(int sizeRank) {
        this.sizeRank = sizeRank;
    }

    public int getSizeRank() {
        return sizeRank;
    }
}
