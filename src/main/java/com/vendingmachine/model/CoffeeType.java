package com.vendingmachine.model;

import java.util.Map;

public enum CoffeeType {

    ESPRESSO(50, Map.of(
            Ingredient.COFFEE_BEANS, 10,
            Ingredient.WATER, 50
    )),

    LATTE(70, Map.of(
            Ingredient.COFFEE_BEANS, 10,
            Ingredient.WATER, 50,
            Ingredient.MILK, 100,
            Ingredient.SUGAR, 5
    )),

    CAPPUCCINO(80, Map.of(
            Ingredient.COFFEE_BEANS, 12,
            Ingredient.WATER, 50,
            Ingredient.MILK, 80,
            Ingredient.SUGAR, 5
    ));

    private final double price;
    private final Map<Ingredient, Integer> recipe;

    CoffeeType(double price, Map<Ingredient, Integer> recipe) {
        this.price = price;
        this.recipe = recipe;
    }

    public double getPrice() {
        return price;
    }

    public Map<Ingredient, Integer> getRecipe() {
        return recipe;
    }
}