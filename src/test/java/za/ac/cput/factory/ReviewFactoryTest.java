package za.ac.cput.factory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import za.ac.cput.domain.Booking;
import za.ac.cput.domain.Customer;
import za.ac.cput.domain.Invoice;
import za.ac.cput.domain.Review;

import static org.junit.jupiter.api.Assertions.*;

class ReviewFactoryTest {

    private Customer customer;
    private Booking booking;

    @BeforeEach
    void setUp() {
        // Arrange shared objects used across tests
        customer = new Customer();
        booking = new Booking() {
            @Override
            public Booking modifyBooking() {
                return null;
            }

            @Override
            public String getBookingDetails() {
                return "";
            }

            @Override
            public Invoice generateInvoice() {
                return null;
            }
        };
    }

    // Test 1: Basic review is created successfully
    @Test
    void testCreateReview() {
        // Arrange & Act
        Review review = ReviewFactory.createReview(5, "Excellent service", customer);

        // Assert
        assertNotNull(review);
        assertEquals(5, review.getRating());
        assertEquals("Excellent service", review.getComment());
        System.out.println(review);
    }

    // Test 2: Rating above 5 should throw an exception
    @Test
    void testCreateReviewWithInvalidRating() {
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                ReviewFactory.createReview(6, "Bad rating", customer));

        assertEquals("Rating must be between 1 and 5", exception.getMessage());
    }

    // Test 3: Null reviewer should throw an exception
    @Test
    void testCreateReviewWithNullReviewer() {
        assertThrows(NullPointerException.class, () ->
                ReviewFactory.createReview(4, "Good", null));
    }

    // Test 4: Empty comment should throw an exception
    @Test
    void testCreateReviewWithEmptyComment() {
        assertThrows(IllegalArgumentException.class, () ->
                ReviewFactory.createReview(3, "", customer));
    }

    // Test 5: Service review is created with full details
    @Test
    void testCreateServiceReview() {
        Review review = ReviewFactory.createServiceReview(
                4, "Nice booking experience", customer, "Flight", "FL123", booking);

        // Assert
        assertNotNull(review);
        assertEquals(4, review.getRating());
        assertEquals("Flight", review.getServiceType());
        assertEquals("FL123", review.getServiceId());
        System.out.println(review);
    }

    // Test 6: Empty service type should throw an exception
    @Test
    void testCreateServiceReviewWithEmptyServiceType() {
        assertThrows(IllegalArgumentException.class, () ->
                ReviewFactory.createServiceReview(4, "Test", customer, "", "ID123", booking));
    }

    // Test 7: Null booking should throw an exception
    @Test
    void testCreateServiceReviewWithNullBooking() {
        assertThrows(NullPointerException.class, () ->
                ReviewFactory.createServiceReview(4, "Test", customer, "Flight", "FL123", null));
    }
}