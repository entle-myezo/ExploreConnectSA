package za.ac.cput.repository;

import za.ac.cput.domain.Location;
import za.ac.cput.util.Helper;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicLong;

public class LocationRepository implements ILocationRepository {

    private static LocationRepository repo = null;
    private Map<Long, Location> locationMap;
    private AtomicLong idCounter;

    private LocationRepository() {
        locationMap = new HashMap<>();
        idCounter = new AtomicLong(1);
    }

    public static LocationRepository getRepository() {
        if (repo == null) repo = new LocationRepository();
        return repo;
    }

    @Override
    public Location create(Location location) {
        Helper.requireNonNull(location, "Location");
        Long id = idCounter.getAndIncrement();
        locationMap.put(id, location);
        return location;
    }

    @Override
    public Location read(Long id) {
        Helper.requireNonNull(id, "Location ID");
        return locationMap.get(id);
    }

    @Override
    public Location update(Location location) {
        Helper.requireNonNull(location, "Location");
        return location; // consider updating in map
    }

    @Override
    public Location delete(Long id) {
        Helper.requireNonNull(id, "Location ID");
        return locationMap.remove(id);
    }

    @Override
    public List<Location> getAll() {
        return new ArrayList<>(locationMap.values());
    }

    @Override
    public Location findById(Long id) {
        return read(id);
    }

    @Override
    public List<Location> findByAddressContaining(String address) {
        Helper.requireNotEmptyOrNull(address, "Address");
        return locationMap.values().stream()
                .filter(location -> location.getAddress() != null &&
                        location.getAddress().toLowerCase().contains(address.toLowerCase()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Location> findByLatitudeBetween(double minLat, double maxLat) {
        if (minLat < -90 || maxLat > 90 || minLat > maxLat) throw new IllegalArgumentException("Invalid latitude range");
        return locationMap.values().stream()
                .filter(location -> location.getLatitude() >= minLat && location.getLatitude() <= maxLat)
                .collect(Collectors.toList());
    }

    @Override
    public List<Location> findByLongitudeBetween(double minLng, double maxLng) {
        if (minLng < -180 || maxLng > 180 || minLng > maxLng) throw new IllegalArgumentException("Invalid longitude range");
        return locationMap.values().stream()
                .filter(location -> location.getLongitude() >= minLng && location.getLongitude() <= maxLng)
                .collect(Collectors.toList());
    }
}