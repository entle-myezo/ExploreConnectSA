package za.ac.cput.util;

import org.springframework.stereotype.*;

import java.util.concurrent.atomic.AtomicLong;

@Component
public class IdGenerator {

    // ==================== COUNTERS FOR DIFFERENT ENTITY TYPES ====================

    private final AtomicLong customerCounter = new AtomicLong(0);
    private final AtomicLong adminCounter = new AtomicLong(0);
    private final AtomicLong addressCounter = new AtomicLong(0);
    private final AtomicLong contactCounter = new AtomicLong(0);
    private final AtomicLong loyaltyProgramCounter = new AtomicLong(0);
    private final AtomicLong travelerCounter = new AtomicLong(0);
    private final AtomicLong reviewCounter = new AtomicLong(0);
    private final AtomicLong cancellationPolicyCounter = new AtomicLong(0);
    private final AtomicLong reportCounter = new AtomicLong(0);
    private final AtomicLong bookingConfirmationCounter = new AtomicLong(0);
    private final AtomicLong invoiceCounter = new AtomicLong(0);
    private final AtomicLong lineItemCounter = new AtomicLong(0);
    private final AtomicLong paymentCounter = new AtomicLong(0);
    private final AtomicLong flightBookingCounter = new AtomicLong(0);
    private final AtomicLong hotelBookingCounter = new AtomicLong(0);
    private final AtomicLong shuttleBookingCounter = new AtomicLong(0);
    private final AtomicLong carRentalBookingCounter = new AtomicLong(0);
    private final AtomicLong locationCounter = new AtomicLong(0);
    private final AtomicLong boardingPassCounter = new AtomicLong(0);

    // ==================== ID GENERATION METHODS ====================

    /**
     * Generates a formatted ID with prefix and sequential number
     * @param prefix The entity prefix (e.g., "CUS" for Customer)
     * @return Formatted ID (e.g., "CUS0001")
     */
    public synchronized String generateId(String prefix) {
        AtomicLong counter = getCounterForPrefix(prefix);
        long nextValue = counter.incrementAndGet();
        return String.format("%s%04d", prefix, nextValue);
    }

    /**
     * Generates a long ID without prefix
     * @return Sequential long ID
     */
    public synchronized Long generateLongId() {
        return customerCounter.incrementAndGet();
    }

    /**
     * Generates a booking reference with prefix and date
     * @param prefix Booking type (e.g., "FLT" for Flight, "HTL" for Hotel)
     * @return Booking reference (e.g., "FLT-2024-0001")
     */
    public synchronized String generateBookingReference(String prefix) {
        String year = String.valueOf(java.time.Year.now().getValue());
        return String.format("%s-%s-%04d", prefix, year, flightBookingCounter.incrementAndGet());
    }

    /**
     * Generates a UUID for entities that need globally unique identifiers
     * @return UUID string
     */
    public synchronized String generateUUID() {
        return java.util.UUID.randomUUID().toString();
    }

    // ==================== COUNTER RETRIEVAL METHODS ====================

    private AtomicLong getCounterForPrefix(String prefix) {
        switch (prefix) {
            case "CUS":
                return customerCounter;
            case "ADM":
                return adminCounter;
            case "ADR":
                return addressCounter;
            case "CON":
                return contactCounter;
            case "LOY":
                return loyaltyProgramCounter;
            case "TRA":
                return travelerCounter;
            case "REV":
                return reviewCounter;
            case "POL":
                return cancellationPolicyCounter;
            case "RPT":
                return reportCounter;
            case "CNF":
                return bookingConfirmationCounter;
            case "INV":
                return invoiceCounter;
            case "LIN":
                return lineItemCounter;
            case "PAY":
                return paymentCounter;
            case "FLT":
                return flightBookingCounter;
            case "HTL":
                return hotelBookingCounter;
            case "SHT":
                return shuttleBookingCounter;
            case "CAR":
                return carRentalBookingCounter;
            case "LOC":
                return locationCounter;
            case "BPS":
                return boardingPassCounter;
            default:
                throw new IllegalArgumentException("Invalid ID prefix: " + prefix +
                        ". Supported prefixes: CUS, ADM, ADR, CON, LOY, TRA, REV, POL, RPT, CNF, INV, LIN, PAY, FLT, HTL, SHT, CAR, LOC, BPS");
        }
    }

    // ==================== INITIALIZATION METHODS ====================

    /**
     * Initializes counters from database on application startup
     * These methods should be called after reading the last ID from the database
     */
    public void initializeCustomerCounter(long initialValue) {
        customerCounter.set(initialValue);
    }

    public void initializeAdminCounter(long initialValue) {
        adminCounter.set(initialValue);
    }

    public void initializeAddressCounter(long initialValue) {
        addressCounter.set(initialValue);
    }

    public void initializeContactCounter(long initialValue) {
        contactCounter.set(initialValue);
    }

    public void initializeLoyaltyProgramCounter(long initialValue) {
        loyaltyProgramCounter.set(initialValue);
    }

    public void initializeTravelerCounter(long initialValue) {
        travelerCounter.set(initialValue);
    }

    public void initializeReviewCounter(long initialValue) {
        reviewCounter.set(initialValue);
    }

    public void initializeCancellationPolicyCounter(long initialValue) {
        cancellationPolicyCounter.set(initialValue);
    }

    public void initializeReportCounter(long initialValue) {
        reportCounter.set(initialValue);
    }

    public void initializeBookingConfirmationCounter(long initialValue) {
        bookingConfirmationCounter.set(initialValue);
    }

    public void initializeInvoiceCounter(long initialValue) {
        invoiceCounter.set(initialValue);
    }

    public void initializeLineItemCounter(long initialValue) {
        lineItemCounter.set(initialValue);
    }

    public void initializePaymentCounter(long initialValue) {
        paymentCounter.set(initialValue);
    }

    public void initializeFlightBookingCounter(long initialValue) {
        flightBookingCounter.set(initialValue);
    }

    public void initializeHotelBookingCounter(long initialValue) {
        hotelBookingCounter.set(initialValue);
    }

    public void initializeShuttleBookingCounter(long initialValue) {
        shuttleBookingCounter.set(initialValue);
    }

    public void initializeCarRentalBookingCounter(long initialValue) {
        carRentalBookingCounter.set(initialValue);
    }

    public void initializeLocationCounter(long initialValue) {
        locationCounter.set(initialValue);
    }

    public void initializeBoardingPassCounter(long initialValue) {
        boardingPassCounter.set(initialValue);
    }

    // ==================== UTILITY METHODS ====================

    /**
     * Resets all counters (useful for testing)
     */
    public synchronized void resetAllCounters() {
        customerCounter.set(0);
        adminCounter.set(0);
        addressCounter.set(0);
        contactCounter.set(0);
        loyaltyProgramCounter.set(0);
        travelerCounter.set(0);
        reviewCounter.set(0);
        cancellationPolicyCounter.set(0);
        reportCounter.set(0);
        bookingConfirmationCounter.set(0);
        invoiceCounter.set(0);
        lineItemCounter.set(0);
        paymentCounter.set(0);
        flightBookingCounter.set(0);
        hotelBookingCounter.set(0);
        shuttleBookingCounter.set(0);
        carRentalBookingCounter.set(0);
        locationCounter.set(0);
        boardingPassCounter.set(0);
    }

    /**
     * Gets the current value of a specific counter (for debugging)
     */
    public synchronized long getCounterValue(String prefix) {
        return getCounterForPrefix(prefix).get();
    }

}