package za.ac.cput.repository;

import za.ac.cput.domain.CancellationPolicy;
import za.ac.cput.util.Helper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class CancellationPolicyRepository implements ICancellationPolicyRepository {
        private static CancellationPolicyRepository repo = null;
        private Map<String, CancellationPolicy> policyMap;

        private CancellationPolicyRepository() {
            policyMap = new HashMap<>();
        }

        public static CancellationPolicyRepository getRepository() {
            if (repo == null) {
                repo = new CancellationPolicyRepository();
            }
            return repo;
        }

        @Override
        public CancellationPolicy create(CancellationPolicy policy) {
            Helper.requireNonNull(policy, "Cancellation Policy");
            if (policy.getPolicyId() == null) {
                throw new IllegalArgumentException("Policy ID cannot be null");
            }
            if (policyMap.containsKey(policy.getPolicyId())) {
                throw new IllegalArgumentException("Policy with ID " + policy.getPolicyId() + " already exists");
            }
            policyMap.put(policy.getPolicyId(), policy);
            return policy;
        }

        @Override
        public CancellationPolicy read(String id) {
            Helper.requireNonNull(id, "Policy ID");
            return policyMap.get(id);
        }

        @Override
        public CancellationPolicy update(CancellationPolicy policy) {
            Helper.requireNonNull(policy, "Cancellation Policy");
            if (policy.getPolicyId() == null) {
                throw new IllegalArgumentException("Policy ID cannot be null");
            }
            if (!policyMap.containsKey(policy.getPolicyId())) {
                throw new IllegalArgumentException("Policy with ID " + policy.getPolicyId() + " does not exist");
            }
            policyMap.put(policy.getPolicyId(), policy);
            return policy;
        }

        @Override
        public CancellationPolicy delete(String id) {
            Helper.requireNonNull(id, "Policy ID");
            return policyMap.remove(id);
        }

        @Override
        public List<CancellationPolicy> getAll() {
            return new ArrayList<>(policyMap.values());
        }

        @Override
        public CancellationPolicy findById(String id) {
            return read(id);
        }

        @Override
        public CancellationPolicy findByPolicyName(String policyName) {
            Helper.requireNotEmptyOrNull(policyName, "Policy Name");
            return policyMap.values().stream()
                    .filter(policy -> policy.getPolicyName() != null &&
                            policy.getPolicyName().equalsIgnoreCase(policyName))
                    .findFirst()
                    .orElse(null);
        }

        @Override
        public List<CancellationPolicy> findByRefundPercentageGreaterThan(double percentage) {
            return policyMap.values().stream()
                    .filter(policy -> policy.getRefundPercentage() > percentage)
                    .collect(Collectors.toList());
        }
    }


