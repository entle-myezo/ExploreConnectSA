package za.ac.cput.factory;
/* LoyaltyProgramFactory.java

   LoyaltyProgram Factory class

   Author: Alakhe Mxakato (230485316)

   Date: 28 June 2026
*/
import za.ac.cput.domain.LoyaltyProgram;
import za.ac.cput.util.Helper;
import za.ac.cput.util.IdGenerator;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class LoyaltyProgramFactory {

    private static final IdGenerator idGenerator = new IdGenerator();

    // Creates a basic loyalty program with 0 points

    public static LoyaltyProgram createLoyaltyProgram() {
        Long programId = idGenerator.generateLongId();

        return new LoyaltyProgram.Builder()
                .setProgramId(programId)
                .setPoints(0)
                .setTier("BRONZE")
                .setJoinDate(LocalDateTime.now())
                .setRewards(new ArrayList<>())
                .build();
    }

    //Creates a loyalty program with points

    public static LoyaltyProgram createLoyaltyProgramWithPoints(int points) {
        Helper.requireNotNegative(points, "Points");

        LoyaltyProgram program = createLoyaltyProgram();
        program.earnPoints(points);

        return new LoyaltyProgram.Builder()
                .copy(program)
                .setPoints(points)
                .setTier(program.getTier())
                .build();
    }

    // Creates a loyalty program with rewards

    public static LoyaltyProgram createLoyaltyProgramWithRewards(int points, List<String> rewards) {
        Helper.requireNotNegative(points, "Points");
        Helper.requireNonNull(rewards, "Rewards");

        LoyaltyProgram program = createLoyaltyProgramWithPoints(points);

        return new LoyaltyProgram.Builder()
                .copy(program)
                .setRewards(rewards)
                .build();
    }

    // Creates a loyalty program with custom tier

    public static LoyaltyProgram createLoyaltyProgramWithTier(int points, String tier) {
        Helper.requireNotNegative(points, "Points");
        Helper.requireNotEmptyOrNull(tier, "Tier");

        LoyaltyProgram program = createLoyaltyProgramWithPoints(points);

        return new LoyaltyProgram.Builder()
                .copy(program)
                .setTier(tier)
                .build();
    }
}
