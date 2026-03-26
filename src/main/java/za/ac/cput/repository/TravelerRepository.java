package za.ac.cput.repository;

import za.ac.cput.domain.Traveler;
import za.ac.cput.util.Helper;
import java.util.ArrayList;
import java.util.List;

public class TravelerRepository implements ITravelerRepository {

    // Singleton instance
    private static TravelerRepository repository = null;

    // In-memory list
    private List<Traveler> travelerList;

    // Private constructor
    private TravelerRepository() {
        travelerList = new ArrayList<>();
    }

    // Get single instance
    public static TravelerRepository getRepository() {
        if (repository == null) {
            repository = new TravelerRepository();
        }
        return repository;
    }

    @Override
    public Traveler create(Traveler traveler) {

        // Validate traveler not null
        Helper.requireNonNull(traveler, "traveler");

        travelerList.add(traveler);
        return traveler;
    }

    @Override
    public Traveler read(Long travelerId) {

        // Validate ID
        Helper.requireNonNull(travelerId, "travelerId");

        for (Traveler traveler : travelerList) {
            if (traveler.getTravelerId().equals(travelerId)) {
                return traveler;
            }
        }
        return null;
    }

    @Override
    public Traveler update(Traveler traveler) {

        // Validate traveler
        Helper.requireNonNull(traveler, "traveler");

        Traveler existing = read(traveler.getTravelerId());

        if (existing != null) {
            travelerList.remove(existing);
            travelerList.add(traveler);
            return traveler;
        }

        return null;
    }

    @Override
    public boolean delete(Long travelerId) {

        // Validate ID
        Helper.requireNonNull(travelerId, "travelerId");

        Traveler traveler = read(travelerId);

        if (traveler != null) {
            travelerList.remove(traveler);
            return true;
        }

        return false;
    }

    @Override
    public List<Traveler> getAll() {
        return travelerList;
    }
}
