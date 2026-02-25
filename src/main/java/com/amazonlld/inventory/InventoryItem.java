
package com.amazonlld.inventory;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Thread-safe inventory per product.
 * Uses fine-grained locking (ReentrantLock per product).
 */
public class InventoryItem {

    private int availableQuantity;
    private int reservedQuantity;
    private final ReentrantLock lock = new ReentrantLock();

    public InventoryItem(int quantity) {
        this.availableQuantity = quantity;
        this.reservedQuantity = 0;
    }

    public boolean reserve(int quantity) {
        lock.lock();
        try {
            if (availableQuantity < quantity) return false;
            availableQuantity -= quantity;
            reservedQuantity += quantity;
            return true;
        } finally {
            lock.unlock();
        }
    }

    public void release(int quantity) {
        lock.lock();
        try {
            reservedQuantity -= quantity;
            availableQuantity += quantity;
        } finally {
            lock.unlock();
        }
    }

    public void confirm(int quantity) {
        lock.lock();
        try {
            reservedQuantity -= quantity;
        } finally {
            lock.unlock();
        }
    }
}
