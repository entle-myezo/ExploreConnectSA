package za.ac.cput.repository;

import za.ac.cput.domain.Traveler;
import java.util.List;

public interface ITravelerRepository extends IRepository<Traveler, Long> {

    Traveler findById(Long id);

    List<Traveler> findByBookingId(Long bookingId);
    Traveler findByPassportNumber(String passportNumber);

}