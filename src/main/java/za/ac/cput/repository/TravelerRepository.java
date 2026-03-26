
package za.ac.cput.repository;

import za.ac.cput.domain.Traveler;
import za.ac.cput.util.Helper;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TravelerRepository implements ITravelerRepository {
    private static TravelerRepository repo = null;
    private Map<Long, Traveler> travelerMap;

    private TravelerRepository() {
        travelerMap = new HashMap<>();
    }

    public static TravelerRepository getRepository() {
        if (repo == null) {
            repo = new TravelerRepository();
        }
        return repo;
    }

    @Override
    public Traveler create(Traveler traveler) {
        Helper.requireNonNull(traveler, "Traveler");
        if (traveler.getTravelerId() == null) {
            throw new IllegalArgumentException("Traveler ID cannot be null");
        }
        if (travelerMap.containsKey(traveler.getTravelerId())) {
            throw new IllegalArgumentException("Traveler with ID " + traveler.getTravelerId() + " already exists");
        }
        travelerMap.put(traveler.getTravelerId(), traveler);
        return traveler;
    }

    @Override
    public Traveler read(Long id) {
        Helper.requireNonNull(id, "Traveler ID");
        return travelerMap.get(id);
    }

    @Override
    public Traveler update(Traveler traveler) {
        Helper.requireNonNull(traveler, "Traveler");
        if (traveler.getTravelerId() == null) {
            throw new IllegalArgumentException("Traveler ID cannot be null");
        }
        if (!travelerMap.containsKey(traveler.getTravelerId())) {
            throw new IllegalArgumentException("Traveler with ID " + traveler.getTravelerId() + " does not exist");
        }
        travelerMap.put(traveler.getTravelerId(), traveler);
        return traveler;
    }

    @Override
    public Traveler delete(Long id) {
        Helper.requireNonNull(id, "Traveler ID");
        return travelerMap.remove(id);
    }

    @Override
    public List<Traveler> getAll() {
        return new ArrayList<>(travelerMap.values());
    }

    @Override
    public Traveler findById(Long id) {
        return read(id);
    }

    @Override
    public List<Traveler> findByBookingId(Long bookingId) {
        Helper.requireNonNull(bookingId, "Booking ID");
        return travelerMap.values().stream()
                .filter(traveler -> false)
                .collect(Collectors.toList());
    }

    @Override
    public Traveler findByPassportNumber(String passportNumber) {
        Helper.requireNotEmptyOrNull(passportNumber, "Passport Number");
        return travelerMap.values().stream()
                .filter(traveler -> traveler.getPassportNumbers() != null &&
                        traveler.getPassportNumbers().contains(passportNumber))
                .findFirst()
                .orElse(null);
    }
}