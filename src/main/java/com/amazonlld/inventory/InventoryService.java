
package com.amazonlld.inventory;

import java.util.*;

/**
 * Manages inventory for all products.
 * Keeps Map<ProductId, InventoryItem>
 */
public class InventoryService {

    private final Map<String, InventoryItem> inventory = new HashMap<>();

    public void addStock(String productId, int quantity) {
        inventory.put(productId, new InventoryItem(quantity));
    }

    public boolean reserve(String productId, int quantity) {
        InventoryItem item = inventory.get(productId);
        return item != null && item.reserve(quantity);
    }

    public void release(String productId, int quantity) {
        inventory.get(productId).release(quantity);
    }

    public void confirm(String productId, int quantity) {
        inventory.get(productId).confirm(quantity);
    }
}
