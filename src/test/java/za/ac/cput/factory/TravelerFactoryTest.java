package za.ac.cput.factory;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import za.ac.cput.domain.Traveler;
import java.time.LocalDate;
import java.util.Arrays;

class TravelerFactoryTest {

    // Test default traveler (should create 1 adult)
    @Test
    void createTraveler() {
        Traveler traveler = TravelerFactory.createTraveler();

        assertNotNull(traveler);
        assertEquals(1, traveler.getAdultCount());
        assertEquals(0, traveler.getChildCount());
        assertEquals(0, traveler.getInfantCount());

        System.out.println(traveler);
    }

    // Test creating traveler with valid counts
    @Test
    void createTravelerWithCounts() {
        Traveler traveler = TravelerFactory
                .createTravelerWithCounts(2, 1, 1);

        assertNotNull(traveler);
        assertEquals(2, traveler.getAdultCount());
        assertEquals(1, traveler.getChildCount());
        assertEquals(1, traveler.getInfantCount());

        System.out.println(traveler);
    }

    // Test counts validation (should throw exception)
    @Test
    void createTravelerWithInvalidCounts() {

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            TravelerFactory.createTravelerWithCounts(0, 0, 0);
        });

        assertEquals("At least one traveler required", exception.getMessage());
    }

    // Test infant greater than adults (should throw exception)
    @Test
    void createTravelerInvalidInfants() {

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            TravelerFactory.createTravelerWithCounts(1, 0, 2);
        });

        assertEquals("Infant count cannot exceed adult count", exception.getMessage());
    }

    // Test traveler with details
    @Test
    void createTravelerWithDetails() {

        Traveler traveler = TravelerFactory.createTravelerWithDetails(
                2,
                1,
                0,
                Arrays.asList("Alakhe", "Sbu", "Miya"),
                Arrays.asList(
                        LocalDate.of(2000, 1, 1),
                        LocalDate.of(2003, 5, 5),
                        LocalDate.of(2026, 3, 3)
                ),
                Arrays.asList("P123", "P456", "P789")
        );

        assertNotNull(traveler);
        assertEquals(3, traveler.getTravelerNames().size());

        System.out.println(traveler);
    }

    // Test mismatched traveler names (should throw exception)
    @Test
    void createTravelerWithInvalidNames() {

        assertThrows(IllegalArgumentException.class, () -> {
            TravelerFactory.createTravelerWithDetails(
                    2,
                    0,
                    0,
                    Arrays.asList("Alakhe"), // wrong size
                    null,
                    null
            );
        });
    }
}