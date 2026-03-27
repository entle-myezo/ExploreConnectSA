package za.ac.cput.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BoardingPassTest {

    private BoardingPassRepository repo;
    private FlightBooking dummyBooking;

    @BeforeEach
    void setUp() {
        repo = BoardingPassRepository.getRepository();
        dummyBooking = new FlightBooking.Builder()
                .setBookingReference("BR123")
                .setFlightNumber("FN456")
                .build();
    }

    @Test
    void testCreateBoardingPass() {
        BoardingPass pass = BoardingPassFactory.createBoardingPass(dummyBooking);
        assertNotNull(pass);
        assertEquals("BR123", pass.getBookingReference());
    }

    @Test
    void testRepositoryCreateAndFindByFlightNumber() {
        BoardingPass pass = BoardingPassFactory.createBoardingPass(dummyBooking);
        repo.create(pass);
        BoardingPass fetched = repo.findByFlightNumber("FN456");
        assertEquals("BR123", fetched.getBookingReference());
    }

    @Test
    void testBoardingPassWithSeat() {
        BoardingPass pass = BoardingPassFactory.createBoardingPassWithSeat(dummyBooking, "12A");
        assertEquals("12A", pass.getSeatNumber());
    }
}