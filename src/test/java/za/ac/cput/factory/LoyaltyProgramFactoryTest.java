package za.ac.cput.factory;
/* LoyaltyProgramFactoryTest.java

   LoyaltyProgram Factory Testing class

   Author: Alakhe Mxakato (230485316)

   Date: 28 June 2026
*/
import org.junit.jupiter.api.*;
import za.ac.cput.domain.*;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class LoyaltyProgramFactoryTest {

    @Test
    @Order(1)
    @DisplayName("Should create default loyalty program")
    void createLoyaltyProgram() {
        LoyaltyProgram program = LoyaltyProgramFactory.createLoyaltyProgram();

        assertNotNull(program);
        assertEquals(0, program.getPoints());
        assertEquals("BRONZE", program.getTier());
        assertNotNull(program.getJoinDate());
        assertTrue(program.getRewards().isEmpty());

        System.out.println("=== Default Loyalty Program ===");
        System.out.println("Points: " + program.getPoints());
        System.out.println("Tier: " + program.getTier());
    }

    @Test
    @Order(2)
    @DisplayName("Should create loyalty program with points")
    void createLoyaltyProgramWithPoints() {
        LoyaltyProgram program = LoyaltyProgramFactory.createLoyaltyProgramWithPoints(500);

        assertEquals(500, program.getPoints());
        assertEquals("BRONZE", program.getTier());

        System.out.println("=== Loyalty Program with Points ===");
        System.out.println("Points: " + program.getPoints());
        System.out.println("Tier: " + program.getTier());
    }

    @Test
    @Order(3)
    @DisplayName("Should create loyalty program with rewards")
    void createLoyaltyProgramWithRewards() {
        List<String> rewards = Arrays.asList("Free Flight Upgrade", "Airport Lounge Access");
        LoyaltyProgram program = LoyaltyProgramFactory.createLoyaltyProgramWithRewards(5000, rewards);

        assertEquals(5000, program.getPoints());
        assertEquals("GOLD", program.getTier());
        assertFalse(program.getRewards().isEmpty());
        assertEquals(2, program.getRewards().size());

        System.out.println("=== Loyalty Program with Rewards ===");
        System.out.println("Points: " + program.getPoints());
        System.out.println("Tier: " + program.getTier());
        System.out.println("Rewards: " + program.getRewards());
    }

    @Test
    @Order(4)
    @DisplayName("Should test tier progression - BRONZE")
    void testTierBronze() {
        LoyaltyProgram program = LoyaltyProgramFactory.createLoyaltyProgramWithPoints(500);
        assertEquals("BRONZE", program.getTier());
        System.out.println("✓ 500 points → BRONZE");
    }

    @Test
    @Order(5)
    @DisplayName("Should test tier progression - SILVER")
    void testTierSilver() {
        LoyaltyProgram program = LoyaltyProgramFactory.createLoyaltyProgramWithPoints(1500);
        assertEquals("SILVER", program.getTier());
        System.out.println("✓ 1500 points → SILVER");
    }

    @Test
    @Order(6)
    @DisplayName("Should test tier progression - GOLD")
    void testTierGold() {
        LoyaltyProgram program = LoyaltyProgramFactory.createLoyaltyProgramWithPoints(5000);
        assertEquals("GOLD", program.getTier());
        System.out.println("✓ 5000 points → GOLD");
    }

    @Test
    @Order(7)
    @DisplayName("Should test tier progression - PLATINUM")
    void testTierPlatinum() {
        LoyaltyProgram program = LoyaltyProgramFactory.createLoyaltyProgramWithPoints(10000);
        assertEquals("PLATINUM", program.getTier());
        System.out.println("✓ 10000 points → PLATINUM");
    }

    @Test
    @Order(8)
    @DisplayName("Should earn points and update tier")
    void testEarnPoints() {
        LoyaltyProgram program = LoyaltyProgramFactory.createLoyaltyProgram();
        program.earnPoints(1500);
        assertEquals(1500, program.getPoints());
        assertEquals("SILVER", program.getTier());
        System.out.println("✓ Points earned and tier updated: " + program.getTier());
    }

    @Test
    @Order(9)
    @DisplayName("Should redeem points successfully")
    void testRedeemPoints() {
        LoyaltyProgram program = LoyaltyProgramFactory.createLoyaltyProgramWithPoints(2000);
        assertTrue(program.redeemPoints(1000));
        assertEquals(1000, program.getPoints());
        System.out.println("✓ Points redeemed successfully");
    }

    @Test
    @Order(10)
    @DisplayName("Should not redeem when insufficient points")
    void testRedeemPointsInsufficient() {
        LoyaltyProgram program = LoyaltyProgramFactory.createLoyaltyProgramWithPoints(500);
        assertFalse(program.redeemPoints(1000));
        assertEquals(500, program.getPoints());
        System.out.println("✓ Insufficient points - redemption failed as expected");
    }

    @Test
    @Order(11)
    @DisplayName("Should throw exception when points are negative")
    void showExceptionWhenPointsNegative() {
        assertThrows(IllegalArgumentException.class, () ->
                LoyaltyProgramFactory.createLoyaltyProgramWithPoints(-100)
        );
        System.out.println("✓ Negative points correctly rejected");
    }
}
