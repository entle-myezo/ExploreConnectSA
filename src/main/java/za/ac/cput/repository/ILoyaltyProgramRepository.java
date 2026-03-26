package za.ac.cput.repository;

import za.ac.cput.domain.LoyaltyProgram;

import java.util.List;

public interface ILoyaltyProgramRepository extends IRepository<LoyaltyProgram, Long> {
    LoyaltyProgram findById(Long id);

    LoyaltyProgram findByCustomerId(Long customerId);
    List<LoyaltyProgram> findByTier(String tier);
    List<LoyaltyProgram> findByPointsGreaterThan(int points);
}
