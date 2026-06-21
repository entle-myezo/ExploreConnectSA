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
        // objects just for testing
        customer = new Customer();
        booking = new Booking() {

            public Booking modifyBooking() {
                return null;
            }

            public String getBookingDetails() {
                return "";
            }

            public Invoice generateInvoice() {
                return null;
            }
        };
    }

    // Test basic review creation
    @Test
    void createReview() {

        Review review = ReviewFactory.createReview(
                5,
                "Excellent service",
                customer
        );

        assertNotNull(review);
        assertEquals(5, review.getRating());

        System.out.println(review);
    }

    // Test invalid rating
    @Test
    void createReviewInvalidRating() {

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            ReviewFactory.createReview(
                    6,
                    "Bad rating",
                    customer
            );
        });

        assertEquals("Rating must be between 1 and 5", exception.getMessage());
    }

    // Test null reviewer
    @Test
    void createReviewNullReviewer() {

        assertThrows(NullPointerException.class, () -> {
            ReviewFactory.createReview(
                    4,
                    "Good",
                    null
            );
        });
    }

    // Test service review creation
    @Test
    void createServiceReview() {

        Review review = ReviewFactory.createServiceReview(
                4,
                "Nice booking experience",
                customer,
                "Flight",
                "FL123",
                booking
        );

        assertNotNull(review);
        assertEquals(4, review.getRating());

        System.out.println(review);
    }

    // Test invalid service type
    @Test
    void createServiceReviewInvalidServiceType() {

        assertThrows(IllegalArgumentException.class, () -> {
            ReviewFactory.createServiceReview(
                    4,
                    "Test",
                    customer,
                    "",
                    "ID123",
                    booking
            );
        });
    }
}