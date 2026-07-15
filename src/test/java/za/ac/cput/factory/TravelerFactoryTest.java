package za.ac.cput.factory;
/* TravelerFactoryTest.java

   Traveler Factory Testing class

   Author: Alakhe Mxakato (230485316)

   Date: 28 June 2026
*/
import org.junit.jupiter.api.*;
import za.ac.cput.domain.*;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TravelerFactoryTest {

    @Test
    @Order(1)
    @DisplayName("Should create default traveler with 1 adult")
    void createTraveler() {
        Traveler traveler = TravelerFactory.createTraveler();

        assertNotNull(traveler);
        assertEquals(1, traveler.getAdultCount());
        assertEquals(0, traveler.getChildCount());
        assertEquals(0, traveler.getInfantCount());

        System.out.println("=== Default Traveler ===");
        System.out.println("Adults: " + traveler.getAdultCount());
        System.out.println("Children: " + traveler.getChildCount());
        System.out.println("Infants: " + traveler.getInfantCount());
    }

    @Test
    @Order(2)
    @DisplayName("Should create traveler with specific counts")
    void createTravelerWithCounts() {
        Traveler traveler = TravelerFactory.createTravelerWithCounts(2, 1, 1);

        assertEquals(2, traveler.getAdultCount());
        assertEquals(1, traveler.getChildCount());
        assertEquals(1, traveler.getInfantCount());
        assertEquals(4, traveler.getTotalTravelers());

        System.out.println("=== Traveler with Counts ===");
        System.out.println("Total Travelers: " + traveler.getTotalTravelers());
    }

    @Test
    @Order(3)
    @DisplayName("Should create traveler with names and passports")
    void createTravelerWithDetails() {
        Traveler traveler = TravelerFactory.createTravelerWithDetails(
                2, 1, 0,
                Arrays.asList("John Doe", "Jane Doe", "Timmy Doe"),
                Arrays.asList("A12345678", "B87654321", "C11223344")
        );

        assertNotNull(traveler);
        assertEquals(3, traveler.getTravelerNames().size());
        assertEquals(3, traveler.getPassportNumbers().size());
        assertTrue(traveler.validateTravelers());

        System.out.println("=== Traveler with Details ===");
        System.out.println("Names: " + traveler.getTravelerNames());
        System.out.println("Passports: " + traveler.getPassportNumbers());
        System.out.println("✓ All travelers validated");
    }

    @Test
    @Order(4)
    @DisplayName("Should add adult successfully")
    void testAddAdult() {
        Traveler traveler = TravelerFactory.createTraveler();
        traveler.addAdult();
        assertEquals(2, traveler.getAdultCount());
        System.out.println("✓ Adult added successfully");
    }

    @Test
    @Order(5)
    @DisplayName("Should not remove adult below minimum")
    void testRemoveAdultBelowMinimum() {
        Traveler traveler = TravelerFactory.createTraveler();
        traveler.removeAdult();
        assertEquals(1, traveler.getAdultCount());
        System.out.println("✓ Adult removal prevented below minimum");
    }

    @Test
    @Order(6)
    @DisplayName("Should add infant only when adult available")
    void testAddInfantRule() {
        Traveler traveler = TravelerFactory.createTraveler();
        traveler.addInfant();
        assertEquals(1, traveler.getInfantCount());

        traveler.addInfant();
        assertEquals(1, traveler.getInfantCount());
        System.out.println("✓ Infant rule tested (infants <= adults)");
    }

    @Test
    @Order(7)
    @DisplayName("Should throw exception when no travelers")
    void showExceptionWhenNoTravelers() {
        assertThrows(IllegalArgumentException.class, () ->
                TravelerFactory.createTravelerWithCounts(0, 0, 0)
        );
        System.out.println("✓ Empty traveler correctly rejected");
    }

    @Test
    @Order(8)
    @DisplayName("Should throw exception when infants exceed adults")
    void showExceptionWhenInfantsExceedAdults() {
        assertThrows(IllegalArgumentException.class, () ->
                TravelerFactory.createTravelerWithCounts(1, 0, 2)
        );
        System.out.println("✓ Infants > adults correctly rejected");
    }
}

