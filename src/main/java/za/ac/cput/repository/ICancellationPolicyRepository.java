package za.ac.cput.repository;

import za.ac.cput.domain.CancellationPolicy;

import java.util.List;

public interface ICancellationPolicyRepository extends IRepository<CancellationPolicy, String> {
    CancellationPolicy findById(String id);

    CancellationPolicy findByPolicyName(String policyName);
    List<CancellationPolicy> findByRefundPercentageGreaterThan(double percentage);
}
