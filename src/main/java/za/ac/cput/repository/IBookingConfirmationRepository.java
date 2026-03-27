package za.ac.cput.repository;

import za.ac.cput.domain.BookingConfirmation;

public interface IBookingConfirmationRepository extends IRepository<BookingConfirmation, String> {
    BookingConfirmation findByBookingReference(String bookingReference);
    BookingConfirmation findByConfirmationNumber(String confirmationNumber);
}