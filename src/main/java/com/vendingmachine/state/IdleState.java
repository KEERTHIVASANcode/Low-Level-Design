package com.vendingmachine.state;

import com.vendingmachine.VendingMachine;
import com.vendingmachine.model.CoffeeType;
import com.vendingmachine.payment.PaymentStrategy;

public class IdleState implements VendingMachineState {

    @Override
    public void selectCoffee(VendingMachine machine,
                             CoffeeType type,
                             PaymentStrategy payment) {

        if (!machine.getInventory().hasSufficient(type.getRecipe())) {
            machine.setState(new OutOfStockState());
            throw new IllegalStateException("Out of stock");
        }

        machine.setState(new PaymentPendingState());
        machine.processPayment(type, payment);
    }
}