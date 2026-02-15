package com.vendingmachine.state;

import com.vendingmachine.VendingMachine;
import com.vendingmachine.model.CoffeeType;
import com.vendingmachine.payment.PaymentStrategy;

public interface VendingMachineState {

    void selectCoffee(VendingMachine machine,
                      CoffeeType type,
                      PaymentStrategy payment);
}