package za.ac.cput.repository;

import za.ac.cput.domain.LoyaltyProgram;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import za.ac.cput.util.Helper;

public class LoyaltyProgramRepository implements ILoyaltyProgramRepository {
    private static LoyaltyProgramRepository repo = null;
    private Map<Long, LoyaltyProgram> loyaltyMap;

    private LoyaltyProgramRepository() {
        loyaltyMap = new HashMap<>();
    }

    public static LoyaltyProgramRepository getRepository() {
        if (repo == null) {
            repo = new LoyaltyProgramRepository();
        }
        return repo;
    }

    @Override
    public LoyaltyProgram create(LoyaltyProgram program) {
        Helper.requireNonNull(program, "Loyalty Program");
        if (program.getProgramId() == null) {
            throw new IllegalArgumentException("Program ID cannot be null");
        }
        if (loyaltyMap.containsKey(program.getProgramId())) {
            throw new IllegalArgumentException("Program with ID " + program.getProgramId() + " already exists");
        }
        loyaltyMap.put(program.getProgramId(), program);
        return program;
    }

    @Override
    public LoyaltyProgram read(Long id) {
        Helper.requireNonNull(id, "Program ID");
        return loyaltyMap.get(id);
    }

    @Override
    public LoyaltyProgram update(LoyaltyProgram program) {
        Helper.requireNonNull(program, "Loyalty Program");
        if (program.getProgramId() == null) {
            throw new IllegalArgumentException("Program ID cannot be null");
        }
        if (!loyaltyMap.containsKey(program.getProgramId())) {
            throw new IllegalArgumentException("Program with ID " + program.getProgramId() + " does not exist");
        }
        loyaltyMap.put(program.getProgramId(), program);
        return program;
    }

    @Override
    public LoyaltyProgram delete(Long id) {
        Helper.requireNonNull(id, "Program ID");
        return loyaltyMap.remove(id);
    }

    @Override
    public List<LoyaltyProgram> getAll() {
        return new ArrayList<>(loyaltyMap.values());
    }

    @Override
    public LoyaltyProgram findById(Long id) {
        return read(id);
    }

    @Override
    public LoyaltyProgram findByCustomerId(Long customerId) {
        Helper.requireNonNull(customerId, "Customer ID");
        return loyaltyMap.values().stream()
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<LoyaltyProgram> findByTier(String tier) {
        Helper.requireNotEmptyOrNull(tier, "Tier");
        return loyaltyMap.values().stream()
                .filter(program -> program.getTier() != null &&
                        program.getTier().equalsIgnoreCase(tier))
                .collect(Collectors.toList());
    }

    @Override
    public List<LoyaltyProgram> findByPointsGreaterThan(int points) {
        Helper.requireNotNegative(points, "Points");
        return loyaltyMap.values().stream()
                .filter(program -> program.getPoints() > points)
                .collect(Collectors.toList());
    }
}