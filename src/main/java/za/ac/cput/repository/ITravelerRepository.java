package za.ac.cput.repository;

import za.ac.cput.domain.Traveler;
import java.util.List;

public interface ITravelerRepository {

    Traveler create(Traveler traveler);

    Traveler read(Long travelerId);

    Traveler update(Traveler traveler);

    boolean delete(Long travelerId);

    List<Traveler> getAll();
}
