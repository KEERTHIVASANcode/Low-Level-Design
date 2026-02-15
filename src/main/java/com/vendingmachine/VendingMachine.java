package com.vendingmachine;

import com.vendingmachine.inventory.Inventory;
import com.vendingmachine.model.CoffeeType;
import com.vendingmachine.payment.CashPaymentResult;
import com.vendingmachine.payment.PaymentResult;
import com.vendingmachine.payment.PaymentStrategy;
import com.vendingmachine.state.IdleState;
import com.vendingmachine.state.VendingMachineState;

import java.util.concurrent.locks.ReentrantLock;

public class VendingMachine {

    private final Inventory inventory;
    private VendingMachineState currentState;
    private final ReentrantLock lock = new ReentrantLock();

    public VendingMachine(Inventory inventory) {
        this.inventory = inventory;
        this.currentState = new IdleState();
    }

    public void selectCoffee(CoffeeType type,
                             PaymentStrategy payment) {

        lock.lock();
        try {
            currentState.selectCoffee(this, type, payment);
        } finally {
            lock.unlock();
        }
    }

    public void processPayment(CoffeeType type,
                               PaymentStrategy payment) {
        currentState.selectCoffee(this, type, payment);
    }

    public void dispense(CoffeeType type, PaymentResult result) {

        System.out.println("Dispensing: " + type);

        if (result instanceof CashPaymentResult cashResult
                && cashResult.getChange() > 0) {
            returnChange(cashResult.getChange());
        }

        setState(new IdleState());
    }

    private void returnChange(double change) {
        System.out.println("Returning change: " + change);
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setState(VendingMachineState state) {
        this.currentState = state;
    }
}