package za.ac.cput.factory;
/* TravelerFactory.java
   Traveler Factory class
   Author: Alakhe Mxakato (230485316)

   Date: 28 June 2026
*/
import za.ac.cput.domain.*;
import za.ac.cput.util.Helper;
import za.ac.cput.util.IdGenerator;
import java.util.List;

public class TravelerFactory {

    private static final IdGenerator idGenerator = new IdGenerator();

    // Creates a traveler with default 1 adult

    public static Traveler createTraveler() {
        Long travelerId = idGenerator.generateLongId();

        return new Traveler.Builder()
                .setTravelerId(travelerId)
                .setAdultCount(1)
                .setChildCount(0)
                .setInfantCount(0)
                .build();
    }

    //Creates a traveler with specific counts

    public static Traveler createTravelerWithCounts(int adultCount, int childCount, int infantCount) {
        Helper.requireNotNegative(adultCount, "Adult Count");
        Helper.requireNotNegative(childCount, "Child Count");
        Helper.requireNotNegative(infantCount, "Infant Count");

        if (adultCount == 0 && childCount == 0 && infantCount == 0) {
            throw new IllegalArgumentException("At least one traveler is required");
        }
        if (infantCount > adultCount) {
            throw new IllegalArgumentException("Infant count cannot exceed adult count");
        }

        Long travelerId = idGenerator.generateLongId();

        return new Traveler.Builder()
                .setTravelerId(travelerId)
                .setAdultCount(adultCount)
                .setChildCount(childCount)
                .setInfantCount(infantCount)
                .build();
    }

    // Creates a traveler with names and passport details

    public static Traveler createTravelerWithDetails(int adultCount, int childCount, int infantCount,
                                                     List<String> travelerNames, List<String> passportNumbers) {
        Traveler traveler = createTravelerWithCounts(adultCount, childCount, infantCount);
        int totalTravelers = adultCount + childCount + infantCount;

        if (travelerNames != null && travelerNames.size() != totalTravelers) {
            throw new IllegalArgumentException("Number of names must match total travelers");
        }
        if (passportNumbers != null && passportNumbers.size() != totalTravelers) {
            throw new IllegalArgumentException("Number of passports must match total travelers");
        }

        Traveler.Builder builder = new Traveler.Builder()
                .copy(traveler);

        if (travelerNames != null) {
            builder.setTravelerNames(travelerNames);
        }
        if (passportNumbers != null) {
            builder.setPassportNumbers(passportNumbers);
        }

        return builder.build();
    }
}