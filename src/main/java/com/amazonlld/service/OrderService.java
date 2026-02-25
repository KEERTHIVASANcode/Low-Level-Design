
package com.amazonlld.service;

import com.amazonlld.cart.*;
import com.amazonlld.factory.PaymentServiceFactory;
import com.amazonlld.inventory.InventoryService;
import com.amazonlld.model.User;
import com.amazonlld.order.*;
import com.amazonlld.payment.*;

import java.util.*;

/**
 * Orchestrates full checkout flow.
 */
public class OrderService {

    private final InventoryService inventoryService;

    public OrderService(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    public Order placeOrder(User user, String paymentMethod) {

        Cart cart = user.getCart();
        List<CartItem> items = new ArrayList<>(cart.getItems());
        List<CartItem> reserved = new ArrayList<>();

        // 1. Reserve inventory
        for (CartItem item : items) {
            boolean success = inventoryService.reserve(
                    item.getProduct().getId(),
                    item.getQuantity()
            );

            if (!success) {
                releaseReserved(reserved);
                throw new IllegalStateException("Out of stock");
            }
            reserved.add(item);
        }

        // 2. Process payment
        PaymentService paymentService =
                PaymentServiceFactory.getPaymentService(paymentMethod);

        PaymentRequest request = new PaymentRequest(
                user.getId(),
                cart.getTotalAmount(),
                paymentMethod
        );

        PaymentResponse response = paymentService.processPayment(request);

        if (!response.isSuccess()) {
            releaseReserved(reserved);
            throw new IllegalStateException("Payment failed");
        }

        // 3. Confirm inventory
        for (CartItem item : reserved) {
            inventoryService.confirm(
                    item.getProduct().getId(),
                    item.getQuantity()
            );
        }

        // 4. Create order
        List<OrderItem> orderItems = new ArrayList<>();
        for (CartItem item : items) {
            orderItems.add(new OrderItem(
                    item.getProduct().getId(),
                    item.getQuantity(),
                    item.getProduct().getPrice()
            ));
        }

        Order order = new Order(
                UUID.randomUUID().toString(),
                orderItems,
                response.getTransactionId(),
                response.getStatus()
        );

        cart.clear();
        return order;
    }

    private void releaseReserved(List<CartItem> reserved) {
        for (CartItem item : reserved) {
            inventoryService.release(
                    item.getProduct().getId(),
                    item.getQuantity()
            );
        }
    }
}
