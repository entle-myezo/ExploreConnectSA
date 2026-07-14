package za.ac.cput.factory;
/* AddressFactory.java

   Address Factory class

   Author: Zamandlovu C Ndlovu (211204803)

   Date: 28 June 2026
*/
import za.ac.cput.domain.*;
import za.ac.cput.util.Helper;
import za.ac.cput.util.IdGenerator;

public class AddressFactory {

    private static final IdGenerator idGenerator = new IdGenerator();

    // Creates a basic address

    public static Address createAddress(String streetNumber, String streetName,
                                        String suburb, String city,
                                        String province, String postalCode,
                                        String country) {
        // Validate required fields
        Helper.requireNotEmptyOrNull(streetNumber, "Street Number");
        Helper.requireNotEmptyOrNull(streetName, "Street Name");
        Helper.requireNotEmptyOrNull(city, "City");
        Helper.requireNotEmptyOrNull(postalCode, "Postal Code");

        if (!Helper.isValidPostalCode(postalCode)) {
            throw new IllegalArgumentException("Invalid Postal Code. Must be 4 digits.");
        }

        Long addressId = idGenerator.generateLongId();

        return new Address.Builder()
                .setAddressId(addressId)
                .setStreetNumber(streetNumber)
                .setStreetName(streetName)
                .setSuburb(suburb)
                .setCity(city)
                .setProvince(province)
                .setPostalCode(postalCode)
                .setCountry(country != null ? country : "South Africa")
                .build();
    }

    //Creates a full address with address type and default flag

    public static Address createFullAddress(String streetNumber, String streetName,
                                            String suburb, String city,
                                            String province, String postalCode,
                                            String country, String addressType,
                                            boolean isDefault) {
        Address address = createAddress(streetNumber, streetName, suburb, city,
                province, postalCode, country);

        return new Address.Builder()
                .copy(address)
                .setAddressType(addressType)
                .setDefault(isDefault)
                .build();
    }
}