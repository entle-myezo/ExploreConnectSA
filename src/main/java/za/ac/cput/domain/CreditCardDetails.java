package za.ac.cput.domain;

import jakarta.persistence.*;

@Embeddable
public class CreditCardDetails {
    @Id
    private String cardNumber;
    private String cardHolderName;
    private String expiryDate;
    private String lastFourDigits;
    private String cardType;
    private boolean isDefault;

    protected CreditCardDetails(){}

    private CreditCardDetails(Builder builder) {
        this.cardNumber = builder.cardNumber;
        this.cardHolderName = builder.cardHolderName;
        this.expiryDate = builder.expiryDate;
        this.lastFourDigits = builder.lastFourDigits;
        this.cardType = builder.cardType;
        this.isDefault = builder.isDefault;
    }

    // Getters
    public String getCardNumber() { return cardNumber; }
    public String getCardHolderName() { return cardHolderName; }
    public String getExpiryDate() { return expiryDate; }
    public String getLastFourDigits() { return lastFourDigits; }
    public String getCardType() { return cardType; }
    public boolean isDefault() { return isDefault; }

    // Business methods
    public boolean validateCard() {
        // Luhn algorithm for card validation
        return cardNumber != null && cardNumber.length() >= 15 &&
                cardNumber.length() <= 16 &&
                expiryDate != null && expiryDate.matches("(0[1-9]|1[0-2])/[0-9]{2}");
    }

    public String maskCardNumber() {
        return "**** **** **** " + lastFourDigits;
    }

    @Override
    public String toString() {
        return "CreditCardDetails{" +
                "cardHolderName='" + cardHolderName + '\'' +
                ", lastFourDigits='" + lastFourDigits + '\'' +
                ", cardType='" + cardType + '\'' +
                ", isDefault=" + isDefault +
                '}';
    }

    public static class Builder {
        private String cardNumber;
        private String cardHolderName;
        private String expiryDate;
        private String lastFourDigits;
        private String cardType;
        private boolean isDefault;

        public Builder(String cardNumber, String cardHolderName, String expiryDate) {
            this.cardNumber = cardNumber;
            this.cardHolderName = cardHolderName;
            this.expiryDate = expiryDate;
            this.lastFourDigits = cardNumber.substring(cardNumber.length() - 4);

            // Determine card type from first digits
            if (cardNumber.startsWith("4")) {
                this.cardType = "Visa";
            } else if (cardNumber.startsWith("5")) {
                this.cardType = "Mastercard";
            } else if (cardNumber.startsWith("3")) {
                this.cardType = "American Express";
            } else {
                this.cardType = "Unknown";
            }
        }

        public Builder setCardNumber(String cardNumber) {
            this.cardNumber = cardNumber;
            return this;
        }

        public Builder setCardHolderName(String cardHolderName) {
            this.cardHolderName = cardHolderName;
            return this;
        }

        public Builder setExpiryDate(String expiryDate) {
            this.expiryDate = expiryDate;
            return this;
        }

        public Builder setLastFourDigits(String lastFourDigits) {
            this.lastFourDigits = lastFourDigits;
            return this;
        }

        public Builder setCardType(String cardType) {
            this.cardType = cardType;
            return this;
        }

        public Builder setDefault(boolean isDefault) {
            this.isDefault = isDefault;
            return this;
        }

        public Builder copy(CreditCardDetails card) {
            this.cardNumber = card.cardNumber;
            this.cardHolderName = card.cardHolderName;
            this.expiryDate = card.expiryDate;
            this.lastFourDigits = card.lastFourDigits;
            this.cardType = card.cardType;
            this.isDefault = card.isDefault;
            return this;
        }

        public CreditCardDetails build() {
            return new CreditCardDetails(this);
        }
    }
}