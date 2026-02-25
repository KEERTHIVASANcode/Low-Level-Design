
package com.amazonlld.cart;

import com.amazonlld.model.Product;
import java.util.*;

/**
 * Cart stores items in Map for O(1) lookup.
 * Key = productId
 */
public class Cart {

    private final Map<String, CartItem> items = new HashMap<>();

    public void addProduct(Product product, int quantity) {
        CartItem existing = items.get(product.getId());
        if (existing != null) {
            existing.increaseQuantity(quantity);
        } else {
            items.put(product.getId(), new CartItem(product, quantity));
        }
    }

    public void removeProduct(String productId) {
        items.remove(productId);
    }

    public Collection<CartItem> getItems() {
        return items.values();
    }

    public double getTotalAmount() {
        return items.values().stream()
                .mapToDouble(CartItem::getTotalPrice)
                .sum();
    }

    public void clear() {
        items.clear();
    }
}
