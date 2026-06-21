package za.ac.cput.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import za.ac.cput.domain.BoardingPass;
import za.ac.cput.domain.Customer;
import za.ac.cput.domain.FlightBooking;
import za.ac.cput.domain.Traveler;
import za.ac.cput.factory.BoardingPassFactory;
import za.ac.cput.factory.CustomerFactory;
import za.ac.cput.factory.FlightBookingFactory;
import za.ac.cput.factory.TravelerFactory;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;


class BoardingPassRepositoryTest {

    private BoardingPassRepository repo;
    private BoardingPass dummyBooking;

    @BeforeEach
    void setUp() {
        repo = BoardingPassRepository.getRepository();
        Customer customer = CustomerFactory.createCustomer("John", "Doe", "john@email.com", "password123");
        Traveler traveler = TravelerFactory.createTravelerWithCounts(1, 0, 0);
        FlightBooking flight = FlightBookingFactory.createFlightBooking("BR123", "British Aways", "Durban","CapeTown", LocalDateTime.now().plusDays(30), customer, traveler);
        dummyBooking = BoardingPassFactory.createBoardingPass(flight);
    }

    @Test
    void testCreateBoardingPass() {

        assertNotNull(dummyBooking);
        assertEquals("BR123", dummyBooking.getBookingReference());
    }

    @Test
    void testRepositoryCreateAndFindByFlightNumber() {
        repo.create(dummyBooking);
        BoardingPass fetched = repo.findByFlightNumber("FN456");
        assertEquals("BR123", fetched.getBookingReference());
    }

    @Test
    void testBoardingPassWithSeat() {

        assertEquals("12A", dummyBooking.getSeatNumber());
    }
}