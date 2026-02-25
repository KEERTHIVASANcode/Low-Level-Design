
package com.amazonlld.cart;

import com.amazonlld.model.Product;

/**
 * Represents item inside cart.
 * Uses latest product price (cart is temporary).
 */
public class CartItem {

    private final Product product;
    private int quantity;

    public CartItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() { return product; }
    public int getQuantity() { return quantity; }

    public void increaseQuantity(int qty) {
        this.quantity += qty;
    }

    public double getTotalPrice() {
        return product.getPrice() * quantity;
    }
}
