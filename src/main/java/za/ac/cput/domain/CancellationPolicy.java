package za.ac.cput.domain;

import jakarta.persistence.*;
import za.ac.cput.util.IdGenerator;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
@Entity
public class CancellationPolicy {
    @Id
    private String policyId;
    private String policyName;
    private int hoursBeforeCancellation;
    private double refundPercentage;
    private boolean allowsModification;
    @Lob
    private String terms;

    protected CancellationPolicy(){}

    private CancellationPolicy(Builder builder) {
        this.policyId = builder.policyId;
        this.policyName = builder.policyName;
        this.hoursBeforeCancellation = builder.hoursBeforeCancellation;
        this.refundPercentage = builder.refundPercentage;
        this.allowsModification = builder.allowsModification;
        this.terms = builder.terms;
    }

    // Getters
    public String getPolicyId() { return policyId; }
    public String getPolicyName() { return policyName; }
    public int getHoursBeforeCancellation() { return hoursBeforeCancellation; }
    public double getRefundPercentage() { return refundPercentage; }
    public boolean isAllowsModification() { return allowsModification; }
    public String getTerms() { return terms; }

    // Business methods
    public double calculateRefund(double totalAmount) {
        return totalAmount * (refundPercentage / 100);
    }

    public boolean isEligible(LocalDateTime bookingTime, LocalDateTime cancellationTime) {
        long hoursUntilBooking = ChronoUnit.HOURS.between(cancellationTime, bookingTime);
        return hoursUntilBooking >= hoursBeforeCancellation;
    }

    @Override
    public String toString() {
        return "CancellationPolicy{" +
                "policyId='" + policyId + '\'' +
                ", policyName='" + policyName + '\'' +
                ", refundPercentage=" + refundPercentage +
                ", allowsModification=" + allowsModification +
                '}';
    }

    public static class Builder {
        private String policyId;
        private String policyName;
        private int hoursBeforeCancellation;
        private double refundPercentage;
        private boolean allowsModification;
        private String terms;

        public Builder setHoursBeforeCancellation(int hoursBeforeCancellation) {
            this.hoursBeforeCancellation = hoursBeforeCancellation;
            return this;
        }

        public Builder setAllowsModification(boolean allowsModification) {
            this.allowsModification = allowsModification;
            return this;
        }

        public Builder setTerms(String terms) {
            this.terms = terms;
            return this;
        }

        public Builder copy(CancellationPolicy policy) {
            this.policyId = policy.policyId;
            this.policyName = policy.policyName;
            this.hoursBeforeCancellation = policy.hoursBeforeCancellation;
            this.refundPercentage = policy.refundPercentage;
            this.allowsModification = policy.allowsModification;
            this.terms = policy.terms;
            return this;
        }

        public CancellationPolicy build() {
            return new CancellationPolicy(this);
        }
    }
}