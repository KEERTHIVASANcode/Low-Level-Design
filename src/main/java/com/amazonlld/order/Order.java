
package com.amazonlld.order;

import com.amazonlld.payment.PaymentStatus;
import java.util.*;

/**
 * Immutable Order representing completed transaction.
 */
public class Order {

    private final String orderId;
    private final List<OrderItem> items;
    private final double totalAmount;
    private final String transactionId;
    private final PaymentStatus paymentStatus;

    public Order(String orderId,
                 List<OrderItem> items,
                 String transactionId,
                 PaymentStatus paymentStatus) {

        this.orderId = orderId;
        this.items = Collections.unmodifiableList(items);
        this.totalAmount = items.stream()
                .mapToDouble(OrderItem::getTotalPrice)
                .sum();
        this.transactionId = transactionId;
        this.paymentStatus = paymentStatus;
    }

    public String getOrderId() { return orderId; }
    public double getTotalAmount() { return totalAmount; }
}
