package za.ac.cput.domain;

import jakarta.persistence.*;
import za.ac.cput.util.IdGenerator;
@Entity
public class Address {
    @Id
    private Long addressId;
    private String addressType;
    private String streetNumber;
    private String streetName;
    private String suburb;
    private String city;
    private String province;
    private String country;
    private String postalCode;
    private boolean isDefault;

    protected Address(){}

    public Address(Builder builder) {
        this.addressId = builder.addressId;
        this.addressType = builder.addressType;
        this.streetNumber = builder.streetNumber;
        this.streetName = builder.streetName;
        this.suburb = builder.suburb;
        this.city = builder.city;
        this.province = builder.province;
        this.country = builder.country;
        this.postalCode = builder.postalCode;
        this.isDefault = builder.isDefault;
    }

    // Getters
    public Long getAddressId() { return addressId; }
    public String getAddressType() { return addressType; }
    public String getStreetNumber() { return streetNumber; }
    public String getStreetName() { return streetName; }
    public String getSuburb() { return suburb; }
    public String getCity() { return city; }
    public String getProvince() { return province; }
    public String getCountry() { return country; }
    public String getPostalCode() { return postalCode; }
    public boolean isDefault() { return isDefault; }

    // Business methods
    public boolean validateAddress() {
        // Add validation logic
        return postalCode != null && !postalCode.isEmpty();
    }

    public String getFullAddress() {
        return String.format("%s %s, %s, %s, %s, %s",
                streetNumber, streetName, suburb, city, province, postalCode);
    }

    @Override
    public String toString() {
        return "Address{" +
                "addressId=" + addressId +
                ", addressType='" + addressType + '\'' +
                ", fullAddress='" + getFullAddress() + '\'' +
                ", country='" + country + '\'' +
                ", isDefault=" + isDefault +
                '}';
    }

    public static class Builder {
        private Long addressId;
        private String addressType;
        private String streetNumber;
        private String streetName;
        private String suburb;
        private String city;
        private String province;
        private String country;
        private String postalCode;
        private boolean isDefault;

        public Builder setAddressId(Long addressId){
            this.addressId = addressId;
            return this;
        }

        public Builder setAddressType(String addressType) {
            this.addressType = addressType;
            return this;
        }
        public Builder setStreetNumber(String streetNumber){
            this.streetNumber = streetNumber;
            return this;
        }
        public Builder setStreetName(String streetName){
            this.streetName = streetName;
            return this;
        }

        public Builder setSuburb(String suburb) {
            this.suburb = suburb;
            return this;
        }
        public Builder setCity(String city){
            this.city = city;
            return this;
        }

        public Builder setProvince(String province) {
            this.province = province;
            return this;
        }

        public Builder setCountry(String country) {
            this.country = country;
            return this;
        }
        public Builder setPostalCode(String postalCode){
            this.postalCode = postalCode;
            return this;
        }

        public Builder setDefault(boolean isDefault) {
            this.isDefault = isDefault;
            return this;
        }

        public Builder copy(Address address) {
            this.addressId = address.addressId;
            this.addressType = address.addressType;
            this.streetNumber = address.streetNumber;
            this.streetName = address.streetName;
            this.suburb = address.suburb;
            this.city = address.city;
            this.province = address.province;
            this.country = address.country;
            this.postalCode = address.postalCode;
            this.isDefault = address.isDefault;
            return this;
        }

        public Address build() {
            return new Address(this);
        }
    }
}
