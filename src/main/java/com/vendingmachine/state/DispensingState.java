package com.vendingmachine.state;

import com.vendingmachine.VendingMachine;
import com.vendingmachine.model.CoffeeType;
import com.vendingmachine.payment.PaymentStrategy;

public class DispensingState implements VendingMachineState {

    @Override
    public void selectCoffee(VendingMachine machine,
                             CoffeeType type,
                             PaymentStrategy payment) {

        throw new IllegalStateException("Machine busy dispensing");
    }
}