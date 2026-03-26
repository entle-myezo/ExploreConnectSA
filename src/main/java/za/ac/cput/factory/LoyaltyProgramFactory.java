package za.ac.cput.factory;

import za.ac.cput.domain.LoyaltyProgram;
import za.ac.cput.util.Helper;
import java.util.List;

public class LoyaltyProgramFactory {

    // Basic Loyalty Program
    public static LoyaltyProgram createLoyaltyProgram() {
        return new LoyaltyProgram.Builder()
                .build();
    }

    // Loyalty Program with initial points
    public static LoyaltyProgram createLoyaltyProgramWithPoints(int points) {
        Helper.requireNotNegative(points, "Points");

        LoyaltyProgram program = createLoyaltyProgram();

        return new LoyaltyProgram.Builder()
                .setPoints(points)
                .copy(program)
                .build();
    }

    // Loyalty Program with custom rewards
    public static LoyaltyProgram createLoyaltyProgramWithRewards(int points,
                                                                 List<String> rewards) {
        Helper.requireNonNull(rewards, "Rewards");

        LoyaltyProgram program = createLoyaltyProgramWithPoints(points);

        return new LoyaltyProgram.Builder()
                .setPoints(points)
                .setRewards(rewards)
                .copy(program)
                .build();
    }
}


