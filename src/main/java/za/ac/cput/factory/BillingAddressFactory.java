package za.ac.cput.factory;

import za.ac.cput.domain.BillingAddress;
import za.ac.cput.util.Helper;

public final class BillingAddressFactory {

    // Basic Billing Address
    public static BillingAddress createBillingAddress(String fullName, String addressLine1,
                                                      String city, String postalCode) {
        Helper.requireNotEmptyOrNull(fullName, "Full Name");
        Helper.requireNotEmptyOrNull(addressLine1, "Address Line 1");
        Helper.requireNotEmptyOrNull(city, "City");
        Helper.requireNotEmptyOrNull(postalCode, "Postal Code");

        return new BillingAddress.Builder(fullName, addressLine1, city, postalCode)
                .build();
    }

    // Full Billing Address
    public static BillingAddress createFullBillingAddress(String fullName, String addressLine1,
                                                          String addressLine2, String city,
                                                          String state, String postalCode,
                                                          String country, String phone) {
        return new BillingAddress.Builder(fullName, addressLine1, city, postalCode)
                .setAddressLine2(addressLine2)
                .setState(state)
                .setCountry(country)
                .setPhone(phone)
                .build();
    }

    // South African Billing Address
    public static BillingAddress createSouthAfricanBillingAddress(String fullName,
                                                                  String addressLine1,
                                                                  String city,
                                                                  String postalCode,
                                                                  String province,
                                                                  String phone) {
        return new BillingAddress.Builder(fullName, addressLine1, city, postalCode)
                .setState(province)               // province is the state
                .setCountry("South Africa")
                .setPhone(phone)
                .build();
    }
}