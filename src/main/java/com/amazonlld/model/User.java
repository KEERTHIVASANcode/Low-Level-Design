
package com.amazonlld.model;

import com.amazonlld.cart.Cart;

/**
 * Represents a user in the system.
 * Each user owns one cart.
 */
public class User {

    private final String id;
    private final String name;
    private final Cart cart;

    public User(String id, String name) {
        this.id = id;
        this.name = name;
        this.cart = new Cart();
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public Cart getCart() { return cart; }
}
