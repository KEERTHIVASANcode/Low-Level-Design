
package com.amazonlld.order;

/**
 * Immutable OrderItem.
 * Stores price at purchase time.
 */
public class OrderItem {

    private final String productId;
    private final int quantity;
    private final double priceAtPurchase;

    public OrderItem(String productId, int quantity, double priceAtPurchase) {
        this.productId = productId;
        this.quantity = quantity;
        this.priceAtPurchase = priceAtPurchase;
    }

    public double getTotalPrice() {
        return priceAtPurchase * quantity;
    }
}
