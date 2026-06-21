package za.ac.cput.repository;

import za.ac.cput.domain.BoardingPass;

public interface IBoardingPassRepository extends IRepository<BoardingPass, String> {
    BoardingPass findById(String id);

    BoardingPass findByBookingReference(String bookingReference);
    BoardingPass findByFlightNumber(String flightNumber);
}