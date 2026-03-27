package za.ac.cput.factory;

import za.ac.cput.domain.CreditCardDetails;
import za.ac.cput.util.Helper;

public class CreditCardDetailsFactory {

    public static CreditCardDetails createCreditCardDetails(
            String cardNumber,
            String cardHolderName,
            String expiryDate,
            boolean isDefault
    ) {
        // Validation
        Helper.requireNotEmptyOrNull(cardNumber, "Card Number");
        Helper.requireNotEmptyOrNull(cardHolderName, "Card Holder Name");
        Helper.requireNotEmptyOrNull(expiryDate, "Expiry Date");


        if (cardNumber.length() < 15 || cardNumber.length() > 16) {
            throw new IllegalArgumentException("Card number must be 15 or 16 digits");
        }

        if (!expiryDate.matches("(0[1-9]|1[0-2])/[0-9]{2}")) {
            throw new IllegalArgumentException("Expiry date must be in MM/YY format");
        }


        CreditCardDetails card = new CreditCardDetails.Builder(
                cardNumber,
                cardHolderName,
                expiryDate
        )
                .setDefault(isDefault)
                .build();


        if (!card.validateCard()) {
            throw new IllegalArgumentException("Invalid credit card details");
        }

        return card;
    }
}
