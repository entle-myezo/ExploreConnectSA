package za.ac.cput.factory;

import za.ac.cput.domain.*;
import za.ac.cput.util.Helper;

import java.time.LocalDateTime;
import java.util.List;

public class CustomerFactory {
    // Basic Customer with minimal required fields
    public static Customer createCustomer(String firstName, String lastName,
                                          String email, String password) {
        Helper.requireNotEmptyOrNull(firstName, "First Name");
        Helper.requireNotEmptyOrNull(lastName, "Last Name");
        Helper.requireNotEmptyOrNull(email, "Email");
        Helper.requireNotEmptyOrNull(password, "Password");

        if (!Helper.isValidEmail(email)) {
            throw new IllegalArgumentException("Invalid email format: " + email);
        }

        return new Customer.Builder(firstName, lastName, email, password)
                .build();
    }

    // Customer with identity details
    public static Customer createCustomerWithIdentity(String firstName, String lastName,
                                                      String email, String password,
                                                      IdentityType identityType,
                                                      String identityNumber,
                                                      LocalDateTime dateOfBirth,
                                                      String nationality) {
        Helper.requireNotEmptyOrNull(firstName, "First Name");
        Helper.requireNotEmptyOrNull(lastName, "Last Name");
        Helper.requireNotEmptyOrNull(email, "Email");
        Helper.requireNotEmptyOrNull(password, "Password");
        Helper.requireNonNull(identityType, "Identity Type");
        Helper.requireNotEmptyOrNull(identityNumber, "Identity Number");
        Helper.requireNonNull(dateOfBirth, "Date of Birth");
        Helper.requireNotEmptyOrNull(nationality, "Nationality");

        if (!Helper.isValidEmail(email)) {
            throw new IllegalArgumentException("Invalid email format: " + email);
        }

        // Validate South African ID if applicable
        if (identityType == IdentityType.RSA_ID) {
            Helper.isValidSouthAfricanId(identityNumber);
        }

        return new Customer.Builder(firstName, lastName, email, password)
                .setIdentityType(identityType)
                .setIdentityNumber(identityNumber)
                .setDateOfBirth(dateOfBirth)
                .setNationality(nationality)
                .build();
    }

    // Customer with all details including preferences
    public static Customer createFullCustomer(String firstName, String lastName,
                                              String email, String password,
                                              IdentityType identityType,
                                              String identityNumber,
                                              LocalDateTime dateOfBirth,
                                              String nationality,
                                              LanguageType preferredLanguage,
                                              ContactDetails contacts,
                                              List<Address> addresses) {
        Customer customer = createCustomerWithIdentity(firstName, lastName, email, password,
                identityType, identityNumber, dateOfBirth, nationality);

        return new Customer.Builder(customer.getFirstName(), customer.getLastName(),
                customer.getEmail(), customer.getPassword())
                .setPreferredLanguage(preferredLanguage)
                .setContacts(contacts)
                .setAddresses(addresses)
                .build();
    }

    // Customer with loyalty program
    public static Customer createCustomerWithLoyalty(String firstName, String lastName,
                                                     String email, String password,
                                                     LoyaltyProgram loyaltyProgram) {
        Customer customer = createCustomer(firstName, lastName, email, password);

        return new Customer.Builder(firstName, lastName, email, password)
                .setLoyaltyProgram(loyaltyProgram)
                .copy(customer)
                .build();
    }
}
