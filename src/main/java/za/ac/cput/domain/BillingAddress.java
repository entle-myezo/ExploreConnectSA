package za.ac.cput.domain;

public class BillingAddress {
    private String fullName;
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String state;
    private String postalCode;
    private String country;
    private String phone;

    private BillingAddress(Builder builder) {
        this.fullName = builder.fullName;
        this.addressLine1 = builder.addressLine1;
        this.addressLine2 = builder.addressLine2;
        this.city = builder.city;
        this.state = builder.state;
        this.postalCode = builder.postalCode;
        this.country = builder.country;
        this.phone = builder.phone;
    }

    // Getters
    public String getFullName() { return fullName; }
    public String getAddressLine1() { return addressLine1; }
    public String getAddressLine2() { return addressLine2; }
    public String getCity() { return city; }
    public String getState() { return state; }
    public String getPostalCode() { return postalCode; }
    public String getCountry() { return country; }
    public String getPhone() { return phone; }

    @Override
    public String toString() {
        return "BillingAddress{" +
                "fullName='" + fullName + '\'' +
                ", addressLine1='" + addressLine1 + '\'' +
                ", city='" + city + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", country='" + country + '\'' +
                '}';
    }

    public static class Builder {
        private String fullName;
        private String addressLine1;
        private String addressLine2;
        private String city;
        private String state;
        private String postalCode;
        private String country;
        private String phone;

        public Builder(String fullName, String addressLine1, String city, String postalCode) {
            this.fullName = fullName;
            this.addressLine1 = addressLine1;
            this.city = city;
            this.postalCode = postalCode;
            this.country = "South Africa";
        }

        public Builder setAddressLine2(String addressLine2) {
            this.addressLine2 = addressLine2;
            return this;
        }

        public Builder setState(String state) {
            this.state = state;
            return this;
        }

        public Builder setCountry(String country) {
            this.country = country;
            return this;
        }

        public Builder setPhone(String phone) {
            this.phone = phone;
            return this;
        }

        public Builder copy(BillingAddress address) {
            this.fullName = address.fullName;
            this.addressLine1 = address.addressLine1;
            this.addressLine2 = address.addressLine2;
            this.city = address.city;
            this.state = address.state;
            this.postalCode = address.postalCode;
            this.country = address.country;
            this.phone = address.phone;
            return this;
        }

        public BillingAddress build() {
            return new BillingAddress(this);
        }
    }
}
