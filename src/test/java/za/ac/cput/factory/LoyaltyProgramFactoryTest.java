package za.ac.cput.factory;

import org.junit.jupiter.api.Test;
import za.ac.cput.domain.LoyaltyProgram;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LoyaltyProgramFactoryTest {

    // Test 1: Basic program is created successfully
    @Test
    void testCreateLoyaltyProgram() {
        LoyaltyProgram program = LoyaltyProgramFactory.createLoyaltyProgram();
        assertNotNull(program);
        assertEquals(0, program.getPoints());
        assertEquals("BRONZE", program.getTier());
        assertNotNull(program.getJoinDate());
        System.out.println(program);
    }

    // Test 2: Program is created with correct points
    @Test
    void testCreateLoyaltyProgramWithPoints() {
        int points = 500;

        LoyaltyProgram program = LoyaltyProgramFactory.createLoyaltyProgramWithPoints(points);

        assertNotNull(program);
        assertEquals(500, program.getPoints());
        System.out.println(program);
    }

    // Test 3: Negative points should throw an exception
    @Test
    void testCreateLoyaltyProgramWithNegativePoints() {
        // Arrange & Act & Assert
        assertThrows(IllegalArgumentException.class, () ->
                LoyaltyProgramFactory.createLoyaltyProgramWithPoints(-10));
    }

    // Test 4: Program is created with rewards
    @Test
    void testCreateLoyaltyProgramWithRewards() {
        int points = 200;
        List<String> rewards = Arrays.asList("Free Flight", "Hotel Discount", "VIP Lounge");

        LoyaltyProgram program = LoyaltyProgramFactory.createLoyaltyProgramWithRewards(points, rewards);

        assertNotNull(program);
        assertEquals(200, program.getPoints());
        assertEquals(3, program.getRewards().size());
        System.out.println(program);
    }

    // Test 5: Null rewards should throw an exception
    @Test
    void testCreateLoyaltyProgramWithNullRewards() {
        assertThrows(NullPointerException.class, () ->
                LoyaltyProgramFactory.createLoyaltyProgramWithRewards(100, null));
    }
}