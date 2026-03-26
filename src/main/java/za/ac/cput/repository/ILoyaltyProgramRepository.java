package za.ac.cput.repository;

import za.ac.cput.domain.LoyaltyProgram;

import java.util.List;

public interface ILoyaltyProgramRepository {
    LoyaltyProgram create(LoyaltyProgram loyaltyProgram);

    LoyaltyProgram read(Long programId);

    LoyaltyProgram update(LoyaltyProgram loyaltyProgram);

    boolean delete(Long programId);

    List<LoyaltyProgram> getAll();
}
