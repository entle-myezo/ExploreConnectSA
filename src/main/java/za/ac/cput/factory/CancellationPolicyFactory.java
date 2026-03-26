package za.ac.cput.factory;

import za.ac.cput.domain.CancellationPolicy;
import za.ac.cput.util.Helper;

public class CancellationPolicyFactory {

    // Basic Cancellation Policy
    public static CancellationPolicy createPolicy(String policyName, double refundPercentage) {
        Helper.requireNotEmptyOrNull(policyName, "Policy Name");
        Helper.requirePositive(refundPercentage, "Refund Percentage");

        if (refundPercentage > 100) {
            throw new IllegalArgumentException("Refund percentage cannot exceed 100");
        }

        return new CancellationPolicy.Builder(policyName, refundPercentage)
                .build();
    }

    // Flexible Policy (full refund up to 48 hours)
    public static CancellationPolicy createFlexiblePolicy() {
        return new CancellationPolicy.Builder("Flexible", 100.0)
                .setHoursBeforeCancellation(48)
                .setAllowsModification(true)
                .setTerms("Full refund if cancelled 48 hours before booking")
                .build();
    }

    // Standard Policy (50% refund up to 24 hours)
    public static CancellationPolicy createStandardPolicy() {
        return new CancellationPolicy.Builder("Standard", 50.0)
                .setHoursBeforeCancellation(24)
                .setAllowsModification(true)
                .setTerms("50% refund if cancelled 24 hours before booking")
                .build();
    }

    // Strict Policy (no refund)
    public static CancellationPolicy createStrictPolicy() {
        return new CancellationPolicy.Builder("Strict", 0.0)
                .setHoursBeforeCancellation(0)
                .setAllowsModification(false)
                .setTerms("No refunds for cancellations")
                .build();
    }
}
