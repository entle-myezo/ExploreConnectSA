package za.ac.cput.repository;

public interface IBoardingPassRepository extends IRepository<BoardingPass, String> {
    BoardingPass findByBookingReference(String bookingReference);
    BoardingPass findByFlightNumber(String flightNumber);
}