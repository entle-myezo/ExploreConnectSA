package za.ac.cput.factory;
/* CarRentalBookingFactoryTest.java

   CarRentalBooking Factory Testing class

   Author: Zamandlovu C Ndlovu (211204803)

   Date: 28 June 2026
*/
import org.junit.jupiter.api.*;
import za.ac.cput.domain.*;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CarRentalBookingFactoryTest {

    private Customer customer;
    private Traveler traveler;

    @BeforeEach
    void setUp() {
        customer = CustomerFactory.createCustomer("John", "Doe", "john@email.com", "password123");
        traveler = TravelerFactory.createTravelerWithCounts(1, 0, 0);
    }

    @Test
    @Order(1)
    @DisplayName("Should create basic car rental")
    void createCarRental() {
        CarRentalBooking rental = CarRentalBookingFactory.createCarRental(
                "Avis", "Toyota Corolla",
                LocalDateTime.now().plusDays(7), LocalDateTime.now().plusDays(14),
                customer, traveler
        );

        assertNotNull(rental);
        assertEquals("Avis", rental.getRentalCompany());
        assertEquals("Toyota Corolla", rental.getCarModel());
        assertEquals(customer, rental.getBookedBy());
        assertEquals(traveler, rental.getTravelers());
        assertNotNull(rental.getBookingReference());

        System.out.println("=== Basic Car Rental ===");
        System.out.println("Company: " + rental.getRentalCompany());
        System.out.println("Car: " + rental.getCarModel());
        System.out.println("Pickup: " + rental.getPickupDate());
        System.out.println("Return: " + rental.getReturnDate());
    }

    @Test
    @Order(2)
    @DisplayName("Should create car rental with extras")
    void createCarRentalWithExtras() {
        CarRentalBooking rental = CarRentalBookingFactory.createCarRentalWithExtras(
                "Hertz", "BMW X5",
                LocalDateTime.now().plusDays(10), LocalDateTime.now().plusDays(15),
                true, true, true, true,
                customer, traveler
        );

        assertNotNull(rental);
        assertTrue(rental.isInsurance());
        assertTrue(rental.isGps());
        assertTrue(rental.isChildSeat());
        assertTrue(rental.isAdditionalDriver());

        System.out.println("=== Car Rental with Extras ===");
        System.out.println("Insurance: " + rental.isInsurance());
        System.out.println("GPS: " + rental.isGps());
        System.out.println("Child Seat: " + rental.isChildSeat());
        System.out.println("Additional Driver: " + rental.isAdditionalDriver());
    }

    @Test
    @Order(3)
    @DisplayName("Should create one-way car rental")
    void createOneWayCarRental() {
        CarRentalBooking rental = CarRentalBookingFactory.createOneWayCarRental(
                "Europcar", "VW Polo",
                LocalDateTime.now().plusDays(20), LocalDateTime.now().plusDays(25),
                "Cape Town", "Johannesburg",
                customer, traveler
        );

        assertNotNull(rental);
        assertTrue(rental.isDifferentReturnLocation());
        assertEquals("Cape Town", rental.getPickupLocation());
        assertEquals("Johannesburg", rental.getReturnLocation());

        System.out.println("=== One-Way Car Rental ===");
        System.out.println("Pickup: " + rental.getPickupLocation());
        System.out.println("Return: " + rental.getReturnLocation());
        System.out.println("Different Location: " + rental.isDifferentReturnLocation());
    }

    @Test
    @Order(4)
    @DisplayName("Should create car rental with policy")
    void createCarRentalWithPolicy() {
        CarRentalBooking rental = CarRentalBookingFactory.createCarRentalWithPolicy(
                "Budget", "Ford Focus",
                LocalDateTime.now().plusDays(5), LocalDateTime.now().plusDays(8),
                "FULL_TO_FULL", 200, 5000.00,
                customer, traveler
        );

        assertNotNull(rental);
        assertEquals("FULL_TO_FULL", rental.getFuelPolicy());
        assertEquals(200, rental.getMileageLimit());
        assertEquals(5000.00, rental.getSecurityDeposit());

        System.out.println("=== Car Rental with Policy ===");
        System.out.println("Fuel Policy: " + rental.getFuelPolicy());
        System.out.println("Mileage Limit: " + rental.getMileageLimit());
        System.out.println("Security Deposit: " + rental.getSecurityDeposit());
    }

    @Test
    @Order(5)
    @DisplayName("Should calculate rental days")
    void testCalculateRentalDays() {
        CarRentalBooking rental = CarRentalBookingFactory.createCarRental(
                "Avis", "Toyota Corolla",
                LocalDateTime.now().plusDays(7), LocalDateTime.now().plusDays(14),
                customer, traveler
        );

        long days = rental.calculateRentalDays();
        assertEquals(7, days);

        System.out.println("=== Rental Days ===");
        System.out.println("Days: " + days);
    }

    @Test
    @Order(6)
    @DisplayName("Should extend rental")
    void testExtendRental() {
        CarRentalBooking rental = CarRentalBookingFactory.createCarRental(
                "Avis", "Toyota Corolla",
                LocalDateTime.now().plusDays(7), LocalDateTime.now().plusDays(14),
                customer, traveler
        );

        LocalDateTime newReturnDate = rental.getReturnDate().plusDays(3);
        assertTrue(rental.extendRental(newReturnDate));
        assertEquals(newReturnDate, rental.getReturnDate());

        System.out.println("=== Extended Rental ===");
        System.out.println("New Return Date: " + rental.getReturnDate());
    }

    @Test
    @Order(7)
    @DisplayName("Should report issue")
    void testReportIssue() {
        CarRentalBooking rental = CarRentalBookingFactory.createCarRental(
                "Avis", "Toyota Corolla",
                LocalDateTime.now().plusDays(7), LocalDateTime.now().plusDays(14),
                customer, traveler
        );

        assertDoesNotThrow(() -> rental.reportIssue("Flat tire"));
        System.out.println("✓ Issue reported");
    }

    @Test
    @Order(8)
    @DisplayName("Should throw exception when pickup date is after return date")
    void showExceptionWhenPickupAfterReturn() {
        assertThrows(IllegalArgumentException.class, () ->
                CarRentalBookingFactory.createCarRental(
                        "Avis", "Car",
                        LocalDateTime.now().plusDays(10), LocalDateTime.now().plusDays(5),
                        customer, traveler
                )
        );
        System.out.println("✓ Pickup after return correctly rejected");
    }

    @Test
    @Order(9)
    @DisplayName("Should throw exception when rental company is empty")
    void showExceptionWhenRentalCompanyEmpty() {
        assertThrows(IllegalArgumentException.class, () ->
                CarRentalBookingFactory.createCarRental(
                        "", "Car",
                        LocalDateTime.now().plusDays(7), LocalDateTime.now().plusDays(14),
                        customer, traveler
                )
        );
        System.out.println("✓ Empty rental company correctly rejected");
    }
}