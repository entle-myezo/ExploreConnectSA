package za.ac.cput.factory;
/* ReviewFactoryTest.java

   Review Factory Testing class

   Author: Alakhe Mxakato (230485316)

   Date: 28 June 2026
*/
import org.junit.jupiter.api.*;
import za.ac.cput.domain.*;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ReviewFactoryTest {

    private Customer customer;
    private Booking booking;

    @BeforeEach
    void setUp() {
        customer = CustomerFactory.createCustomer("John", "Doe", "john@email.com", "password123");

        // ===== FIX: Create a booking for the service review test =====
        Traveler traveler = TravelerFactory.createTravelerWithCounts(1, 0, 0);
        booking = FlightBookingFactory.createFullFlightBooking(
                "SA234", "South African Airways", "JFK", "LAX",
                LocalDateTime.now().plusDays(30), LocalDateTime.now().plusDays(30).plusHours(6),
                FJourney.ONE_WAY, FBookingClass.ECONOMY, FlightType.SOUTH_AFRICA_AIRWAYS,
                customer, traveler, CancellationPolicyFactory.createFlexiblePolicy(),
                850.00, 127.50
        );
    }

    @Test
    @Order(1)
    @DisplayName("Should create basic review")
    void createReview() {
        Review review = ReviewFactory.createReview(5, "Excellent service!", customer);

        assertNotNull(review);
        assertEquals(5, review.getRating());
        assertEquals("Excellent service!", review.getComment());
        assertEquals(customer, review.getReviewer());
        assertTrue(review.validateReview());

        System.out.println("=== Basic Review ===");
        System.out.println("Rating: " + review.getRating());
        System.out.println("Comment: " + review.getComment());
        System.out.println("✓ Review validated");
    }

    @Test
    @Order(2)
    @DisplayName("Should create service review")
    void createServiceReview() {
        Review review = ReviewFactory.createServiceReview(
                4, "Great flight", customer, "FLIGHT", "SA234", booking
        );

        assertNotNull(review);
        assertEquals(4, review.getRating());
        assertEquals("FLIGHT", review.getServiceType());
        assertEquals("SA234", review.getServiceId());
        assertEquals(booking, review.getBooking());

        System.out.println("=== Service Review ===");
        System.out.println("Service Type: " + review.getServiceType());
        System.out.println("Service ID: " + review.getServiceId());
        System.out.println("Booking: " + review.getBooking().getBookingReference());
    }

    @Test
    @Order(3)
    @DisplayName("Should throw exception when rating is below 1")
    void showExceptionWhenRatingTooLow() {
        assertThrows(IllegalArgumentException.class, () ->
                ReviewFactory.createReview(0, "Bad", customer)
        );
        System.out.println("✓ Rating below 1 correctly rejected");
    }

    @Test
    @Order(4)
    @DisplayName("Should throw exception when rating is above 5")
    void showExceptionWhenRatingTooHigh() {
        assertThrows(IllegalArgumentException.class, () ->
                ReviewFactory.createReview(6, "Great", customer)
        );
        System.out.println("✓ Rating above 5 correctly rejected");
    }

    @Test
    @Order(5)
    @DisplayName("Should throw exception when comment is empty")
    void showExceptionWhenCommentEmpty() {
        assertThrows(IllegalArgumentException.class, () ->
                ReviewFactory.createReview(5, "", customer)
        );
        System.out.println("✓ Empty comment correctly rejected");
    }

    @Test
    @Order(6)
    @DisplayName("Should flag inappropriate review")
    void testFlagInappropriate() {
        Review review = ReviewFactory.createReview(3, "Okay service", customer);
        assertTrue(review.flagInappropriate());
        System.out.println("✓ Review flagged successfully");
    }
}