package za.ac.cput.repository;

import za.ac.cput.domain.Location;
import za.ac.cput.util.Helper;
import java.util.List;

public interface ILocationRepository extends IRepository<Location, Long> {
    Location findById(Long id);

    List<Location> findByAddressContaining(String address);
    List<Location> findByLatitudeBetween(double minLat, double maxLat);
    List<Location> findByLongitudeBetween(double minLng, double maxLng);
}