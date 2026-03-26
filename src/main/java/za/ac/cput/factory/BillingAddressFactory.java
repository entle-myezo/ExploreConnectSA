package za.ac.cput.factory;

public class BillingAddressFactory {

        private String streetNumber;
        private String streetName;
        private String city;
        private String postalCode;

        private BillingAddressFactory(BillingAddressFactory billingAddressFactory) {}

        public BillingAddressFactory(Builder builder) {
            this.streetNumber = builder.streetNumber;
            this.streetName = builder.streetName;
            this.city = builder.city;
            this.postalCode = builder.postalCode;

        }

        // Getters

        public String getStreetNumber() { return streetNumber; }
        public String getStreetName() { return streetName; }
        public String getCity() { return city; }
        public String getPostalCode() { return postalCode; }


        public static class Builder {
            public String streetName;
            public String streetNumber;
            private String city;
            private String postalCode;



            public Builder setStreetNumber(String streetNumber) {
                this.streetNumber = streetNumber;
                return this;
            }

            public Builder setStreetName(String streetName) {
                this.streetName = streetName;
                return this;
            }

            public Builder setCity(String city) {
                this.city = city;
                return this;
            }
            public Builder setPostalCode(String postalCode) {
                this.postalCode = postalCode;
                return this;
            }

            }
            public BillingAddressFactory build() {
                return new BillingAddressFactory(this);
            }
        }

