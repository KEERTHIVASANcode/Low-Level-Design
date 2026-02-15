package com.vendingmachine.inventory;

import com.vendingmachine.model.Ingredient;

import java.util.EnumMap;
import java.util.Map;

public class Inventory {

    private final Map<Ingredient, Integer> stock;

    public Inventory(Map<Ingredient, Integer> initialStock) {
        this.stock = new EnumMap<>(Ingredient.class);
        this.stock.putAll(initialStock);
    }

    public boolean hasSufficient(Map<Ingredient, Integer> recipe) {
        for (Map.Entry<Ingredient, Integer> entry : recipe.entrySet()) {
            if (stock.getOrDefault(entry.getKey(), 0) < entry.getValue()) {
                return false;
            }
        }
        return true;
    }

    public void deduct(Map<Ingredient, Integer> recipe) {
        if (!hasSufficient(recipe)) {
            throw new IllegalStateException("Insufficient ingredients");
        }

        for (Map.Entry<Ingredient, Integer> entry : recipe.entrySet()) {
            stock.put(entry.getKey(),
                    stock.get(entry.getKey()) - entry.getValue());
        }
    }

    public void refill(Ingredient ingredient, int qty) {
        stock.put(ingredient,
                stock.getOrDefault(ingredient, 0) + qty);
    }
}