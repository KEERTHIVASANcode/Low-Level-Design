package com.vendingmachine.state;

import com.vendingmachine.VendingMachine;
import com.vendingmachine.model.CoffeeType;
import com.vendingmachine.payment.PaymentResult;
import com.vendingmachine.payment.PaymentStrategy;

public class PaymentPendingState implements VendingMachineState {

    @Override
    public void selectCoffee(VendingMachine machine,
                             CoffeeType type,
                             PaymentStrategy payment) {

        PaymentResult result = payment.pay(type.getPrice());

        if (!result.isSuccess()) {
            machine.setState(new IdleState());
            throw new IllegalStateException(result.getMessage());
        }

        machine.getInventory().deduct(type.getRecipe());
        machine.setState(new DispensingState());

        machine.dispense(type, result);
    }
}