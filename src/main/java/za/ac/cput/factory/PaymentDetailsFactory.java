package za.ac.cput.factory;

import za.ac.cput.domain.*;
import za.ac.cput.util.Helper;

import java.time.LocalDateTime;

public class PaymentDetailsFactory {


    public static PaymentDetails createPaymentDetails(
            PaymentMethod method,
            double amount,
            String currency
    ) {

        Helper.requireNonNull(method, "Payment Method");

        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be greater than 0");
        }

        Helper.requireNotEmptyOrNull(currency, "Currency");

        return new PaymentDetails.Builder(method, amount, currency)
                .setPaymentDate(LocalDateTime.now())
                .build();
    }


    public static PaymentDetails createFullPaymentDetails(
            PaymentMethod method,
            double amount,
            String currency,
            BillingAddress billingAddress,
            CreditCardDetails creditCardDetails
    ) {

        PaymentDetails basePayment = createPaymentDetails(method, amount, currency);

        // Validate billing address
        Helper.requireNonNull(billingAddress, "Billing Address");


        if (method == PaymentMethod.CREDIT_CARD) {
            Helper.requireNonNull(creditCardDetails, "Credit Card Details");
        }

        return new PaymentDetails.Builder(method, amount, currency)
                .copy(basePayment)
                .setBillingAddress(billingAddress)
                .setCreditCardDetails(creditCardDetails)
                .setPaymentDate(LocalDateTime.now())
                .build();
    }
}