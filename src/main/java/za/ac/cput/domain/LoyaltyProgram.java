package za.ac.cput.domain;

import za.ac.cput.util.IdGenerator;

import java.time.LocalDateTime;
import java.util.*;

public class LoyaltyProgram {
    private Long programId;
    private int points;
    private String tier;
    private LocalDateTime joinDate;
    private List<String> rewards;

    private LoyaltyProgram(Builder builder) {
        this.programId = builder.programId;
        this.points = builder.points;
        this.tier = builder.tier;
        this.joinDate = builder.joinDate;
        this.rewards = builder.rewards != null ? builder.rewards : new ArrayList<>();
    }

    // Getters
    public Long getProgramId() { return programId; }
    public int getPoints() { return points; }
    public String getTier() { return tier; }
    public LocalDateTime getJoinDate() { return joinDate; }
    public List<String> getRewards() { return rewards; }

    // Business methods
    public void earnPoints(int amount) {
        this.points += amount;
        checkTier();
    }

    public boolean redeemPoints(int pointsNeeded) {
        if (this.points >= pointsNeeded) {
            this.points -= pointsNeeded;
            return true;
        }
        return false;
    }

    public String checkTier() {
        if (points >= 10000) {
            this.tier = "PLATINUM";
        } else if (points >= 5000) {
            this.tier = "GOLD";
        } else if (points >= 1000) {
            this.tier = "SILVER";
        } else {
            this.tier = "BRONZE";
        }
        return this.tier;
    }

    @Override
    public String toString() {
        return "LoyaltyProgram{" +
                "programId=" + programId +
                ", points=" + points +
                ", tier='" + tier + '\'' +
                ", joinDate=" + joinDate +
                ", rewards=" + rewards +
                '}';
    }

    public static class Builder {
        private Long programId;
        private int points;
        private String tier;
        private LocalDateTime joinDate;
        private List<String> rewards;

        public Builder(int points, String tier, LocalDateTime joinDate, List<String> rewards) {
            this.programId = IdGenerator.getInstance().generateId();
            this.points = 0;
            this.tier = "BRONZE";
            this.joinDate = LocalDateTime.now();
            this.rewards = new ArrayList<>();
        }

        public Builder() {

        }

        public Builder setPoints(int points) {
            this.points = points;
            return this;
        }

        public Builder setTier(String tier) {
            this.tier = tier;
            return this;
        }

        public Builder setJoinDate(LocalDateTime joinDate) {
            this.joinDate = joinDate;
            return this;
        }

        public Builder setRewards(List<String> rewards) {
            this.rewards = rewards;
            return this;
        }

        public Builder addReward(String reward) {
            if (this.rewards == null) {
                this.rewards = new ArrayList<>();
            }
            this.rewards.add(reward);
            return this;
        }

        public Builder copy(LoyaltyProgram loyaltyProgram) {
            this.programId = loyaltyProgram.programId;
            this.points = loyaltyProgram.points;
            this.tier = loyaltyProgram.tier;
            this.joinDate = loyaltyProgram.joinDate;
            this.rewards = loyaltyProgram.rewards;
            return this;
        }

        public LoyaltyProgram build() {
            return new LoyaltyProgram(this);
        }
    }
}
