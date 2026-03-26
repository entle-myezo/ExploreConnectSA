package za.ac.cput.repository;

import za.ac.cput.domain.LoyaltyProgram;

import java.util.ArrayList;
import java.util.List;
import za.ac.cput.util.Helper;

public class LoyaltyProgramRepository implements ILoyaltyProgramRepository{

        // Singleton repository instance
        private static LoyaltyProgramRepository repository = null;

        private final List<LoyaltyProgram> loyaltyProgramList;

        private LoyaltyProgramRepository() {
            loyaltyProgramList = new ArrayList<>();
        }

        // Method to get single repository instance
        public static LoyaltyProgramRepository getRepository() {
            if (repository == null) {
                repository = new LoyaltyProgramRepository();
            }
            return repository;
        }

    // Validate object is not null, adding an object to list, returning created object
    @Override
        public LoyaltyProgram create(LoyaltyProgram loyaltyProgram) {

            Helper.requireNonNull(loyaltyProgram, "loyaltyProgram");

            loyaltyProgramList.add(loyaltyProgram);

            return loyaltyProgram;
        }

    // Loop through list to find matching ID
        @Override
        public LoyaltyProgram read(Long programId) {

            Helper.requireNonNull(programId, "programId");

            for (LoyaltyProgram lp : loyaltyProgramList) {
                if (lp.getProgramId().equals(programId)) {
                    return lp;
                }
            }
            // Return null if not found
            return null;
        }

        // Find existing records to update
        @Override
        public LoyaltyProgram update(LoyaltyProgram loyaltyProgram) {

            Helper.requireNonNull(loyaltyProgram, "loyaltyProgram");

            LoyaltyProgram existing = read(loyaltyProgram.getProgramId());

            if (existing != null) {
                loyaltyProgramList.remove(existing);
                loyaltyProgramList.add(loyaltyProgram);

                return loyaltyProgram;
            }

            return null;
        }

    // Finds objects to delete and removes them
        @Override
        public boolean delete(Long programId) {

            Helper.requireNonNull(programId, "programId");

            LoyaltyProgram loyaltyProgram = read(programId);

            if (loyaltyProgram != null) {
                loyaltyProgramList.remove(loyaltyProgram);
                return true;
            }

            return false;
        }

        // Return all stored objects
        @Override
        public List<LoyaltyProgram> getAll() {

            return loyaltyProgramList;
        }
    }

