package za.ac.cput.repository;

import za.ac.cput.domain.BookingConfirmation;
import za.ac.cput.util.Helper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BookingConfirmationRepository implements IBookingConfirmationRepository {
    private static BookingConfirmationRepository repo = null;
    private Map<String, BookingConfirmation> confirmationMap;

    private BookingConfirmationRepository() {
        confirmationMap = new HashMap<>();
    }

    public static BookingConfirmationRepository getRepository() {
        if (repo == null) {
            repo = new BookingConfirmationRepository();
        }
        return repo;
    }

    @Override
    public BookingConfirmation create(BookingConfirmation confirmation) {
        Helper.requireNonNull(confirmation, "Booking Confirmation");
        if (confirmation.getConfirmationNumber() == null) {
            throw new IllegalArgumentException("Confirmation Number cannot be null");
        }
        if (confirmationMap.containsKey(confirmation.getConfirmationNumber())) {
            throw new IllegalArgumentException("Confirmation with number " +
                    confirmation.getConfirmationNumber() + " already exists");
        }
        confirmationMap.put(confirmation.getConfirmationNumber(), confirmation);
        return confirmation;
    }

    @Override
    public BookingConfirmation read(String id) {
        Helper.requireNonNull(id, "Confirmation Number");
        return confirmationMap.get(id);
    }

    @Override
    public BookingConfirmation update(BookingConfirmation confirmation) {
        Helper.requireNonNull(confirmation, "Booking Confirmation");
        if (confirmation.getConfirmationNumber() == null) {
            throw new IllegalArgumentException("Confirmation Number cannot be null");
        }
        if (!confirmationMap.containsKey(confirmation.getConfirmationNumber())) {
            throw new IllegalArgumentException("Confirmation with number " +
                    confirmation.getConfirmationNumber() + " does not exist");
        }
        confirmationMap.put(confirmation.getConfirmationNumber(), confirmation);
        return confirmation;
    }

    @Override
    public BookingConfirmation delete(String id) {
        Helper.requireNonNull(id, "Confirmation Number");
        return confirmationMap.remove(id);
    }

    @Override
    public List<BookingConfirmation> getAll() {
        return new ArrayList<>(confirmationMap.values());
    }

    @Override
    public BookingConfirmation findById(String id) {
        return read(id);
    }

    @Override
    public BookingConfirmation findByBookingReference(String bookingReference) {
        Helper.requireNotEmptyOrNull(bookingReference, "Booking Reference");
        return confirmationMap.values().stream()
                .filter(confirmation -> confirmation.getBooking() != null &&
                        confirmation.getBooking().getBookingReference() != null &&
                        confirmation.getBooking().getBookingReference().equals(bookingReference))
                .findFirst()
                .orElse(null);
    }

    @Override
    public BookingConfirmation findByConfirmationNumber(String confirmationNumber) {
        Helper.requireNotEmptyOrNull(confirmationNumber, "Confirmation Number");
        return confirmationMap.get(confirmationNumber);
    }
}