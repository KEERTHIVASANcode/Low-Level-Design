
package com.amazonlld.model;

/**
 * Represents a product in the catalog.
 * Only contains product-related data (SRP).
 */
public class Product {

    private final String id;
    private String name;
    private double price;

    public Product(String id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public double getPrice() { return price; }

    public void updatePrice(double newPrice) {
        this.price = newPrice;
    }
}
