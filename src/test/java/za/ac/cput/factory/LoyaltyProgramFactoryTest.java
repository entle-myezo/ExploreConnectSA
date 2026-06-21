package za.ac.cput.factory;

import org.junit.jupiter.api.Test;
import za.ac.cput.domain.LoyaltyProgram;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LoyaltyProgramFactoryTest {

    // Test basic loyalty program
    @Test
    void createLoyaltyProgram() {

        LoyaltyProgram program = LoyaltyProgramFactory.createLoyaltyProgram();

        assertNotNull(program);
        System.out.println(program);
    }

    // Test loyalty program with points
    @Test
    void createLoyaltyProgramWithPoints() {

        LoyaltyProgram program =
                LoyaltyProgramFactory.createLoyaltyProgramWithPoints(100);

        assertNotNull(program);
        assertEquals(100, program.getPoints());

        System.out.println(program);
    }

    // Test negative points
    @Test
    void createLoyaltyProgramWithNegativePoints() {

        assertThrows(IllegalArgumentException.class, () -> {
            LoyaltyProgramFactory.createLoyaltyProgramWithPoints(-10);
        });
    }

    // Test loyalty program with rewards
    @Test
    void createLoyaltyProgramWithRewards() {

        List<String> rewards = Arrays.asList(
                "Free Flight",
                "Hotel Discount",
                "VIP Lounge"
        );

        LoyaltyProgram program =
                LoyaltyProgramFactory.createLoyaltyProgramWithRewards(200, rewards);

        assertNotNull(program);
        assertEquals(200, program.getPoints());
        assertEquals(3, program.getRewards().size());

        System.out.println(program);
    }

    // Test null rewards
    @Test
    void createLoyaltyProgramWithNullRewards() {

        assertThrows(NullPointerException.class, () -> {
            LoyaltyProgramFactory.createLoyaltyProgramWithRewards(100, null);
        });
    }
}