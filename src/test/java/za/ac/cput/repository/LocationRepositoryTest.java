package za.ac.cput.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import za.ac.cput.domain.Location;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class LocationTest {

    private LocationRepository repo;

    @BeforeEach
    void setUp() {
        repo = LocationRepository.getRepository();
    }

    @Test
    void testCreateLocation() {
        Location loc = LocationFactory.createLocation(-33.9, 18.6);
        assertNotNull(loc);
        assertEquals(-33.9, loc.getLatitude());
        assertEquals(18.6, loc.getLongitude());
    }

    @Test
    void testLocationWithAddress() {
        Location loc = LocationFactory.createLocationWithAddress(-33.9, 18.6, "Cape Town Airport");
        assertEquals("Cape Town Airport", loc.getAddress());
    }

    @Test
    void testRepositoryFindByLatitude() {
        Location loc = LocationFactory.createLocation(-26.1, 28.2);
        repo.create(loc);
        assertFalse(repo.findByLatitudeBetween(-27, -25).isEmpty());
    }
}