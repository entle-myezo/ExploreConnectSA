package za.ac.cput.factory;

import org.junit.jupiter.api.Test;
import za.ac.cput.domain.Traveler;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TravelerFactoryTest {

    // Test 1: Basic traveler is created with default values
    @Test
    void testCreateTraveler() {
        Traveler traveler = TravelerFactory.createTraveler();

        assertNotNull(traveler);
        assertEquals(0, traveler.getAdultCount());
        assertEquals(0, traveler.getChildCount());
        assertEquals(0, traveler.getInfantCount());
        System.out.println(traveler);
    }

    // Test 2: Traveler is created with correct counts
    @Test
    void testCreateTravelerWithCounts() {
        Traveler traveler = TravelerFactory.createTravelerWithCounts(2, 1, 1);

        assertNotNull(traveler);
        assertEquals(2, traveler.getAdultCount());
        assertEquals(1, traveler.getChildCount());
        assertEquals(1, traveler.getInfantCount());
        System.out.println(traveler);
    }

    // Test 3: All zero counts should throw an exception
    @Test
    void testCreateTravelerWithAllZeroCounts() {
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                TravelerFactory.createTravelerWithCounts(0, 0, 0));

        assertEquals("At least one traveler required", exception.getMessage());
    }

    // Test 4: Infants exceeding adults should throw an exception
    @Test
    void testCreateTravelerWithTooManyInfants() {
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                TravelerFactory.createTravelerWithCounts(1, 0, 2));

        assertEquals("Infant count cannot exceed adult count", exception.getMessage());
    }

    // Test 5: Traveler is created with full details
    @Test
    void testCreateTravelerWithDetails() {
        List<String> names = Arrays.asList("Alakhe", "Sbu", "Miya");
        List<LocalDate> ages = Arrays.asList(
                LocalDate.of(2000, 1, 1),
                LocalDate.of(2003, 5, 5),
                LocalDate.of(2026, 3, 3)
        );
        List<String> passports = Arrays.asList("P123", "P456", "P789");

        Traveler traveler = TravelerFactory.createTravelerWithDetails(2, 1, 0, names, ages, passports);
        assertNotNull(traveler);
        assertEquals(3, traveler.getTravelerNames().size());
        assertEquals(3, traveler.getPassportNumbers().size());
        System.out.println(traveler);
    }

    // Test 6: Mismatched names count should throw an exception
    @Test
    void testCreateTravelerWithMismatchedNames() {
        assertThrows(IllegalArgumentException.class, () ->
                TravelerFactory.createTravelerWithDetails(
                        2, 0, 0,
                        Arrays.asList("Alakhe"), // only 1 name for 2 adults
                        null,
                        null
                ));
    }
}