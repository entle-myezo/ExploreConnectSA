package za.ac.cput.factory;

import za.ac.cput.domain.Booking;
import za.ac.cput.domain.BookingConfirmation;
import za.ac.cput.util.Helper;

public class BookingConfirmationFactory {

    // Basic Confirmation from Booking
    public static BookingConfirmation createConfirmation(Booking booking) {
        Helper.requireNonNull(booking, "Booking");
        return new BookingConfirmation.Builder(booking)
                .build();
    }

    // Confirmation with terms and conditions
    public static BookingConfirmation createConfirmationWithTerms(Booking booking,
                                                                  String termsAndConditions) {
        Helper.requireNonNull(booking, "Booking");
        Helper.requireNotEmptyOrNull(termsAndConditions, "Terms and Conditions");
        return new BookingConfirmation.Builder(booking)
                .setTermsAndConditions(termsAndConditions)
                .build();
    }
}