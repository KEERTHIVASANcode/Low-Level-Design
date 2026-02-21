
package com.bookmyshow.factory;

import com.bookmyshow.enums.PaymentMode;
import com.bookmyshow.strategy.payment.*;

public class PaymentFactory {

    public static PaymentStrategy getPaymentStrategy(PaymentMode mode) {
        return switch (mode) {
            case CARD -> new CardPaymentStrategy();
            case UPI -> new UpiPaymentStrategy();
        };
    }
}
