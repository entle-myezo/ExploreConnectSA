package za.ac.cput.factory;

import za.ac.cput.domain.LoyaltyProgram;
import za.ac.cput.util.Helper;

import java.time.LocalDateTime;
import java.util.List;

public class LoyaltyProgramFactory {
    public static LoyaltyProgram createLoyaltyProgram(  int points,
                                                        String tier,
                                                        LocalDateTime joinDate,
                                                        List<String> rewards) {
        Helper.requireNotEmptyOrNull(tier, "tier");
        return new LoyaltyProgram.Builder(points, tier, joinDate, rewards).setPoints(points)
                .setTier(tier)
                .setJoinDate(joinDate)
                .setRewards(rewards)
                .build();
      }
}
