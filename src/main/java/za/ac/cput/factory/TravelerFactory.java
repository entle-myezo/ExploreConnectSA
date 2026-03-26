package za.ac.cput.factory;

import za.ac.cput.domain.Traveler;
import za.ac.cput.util.Helper;
import java.time.LocalDate;
import java.util.List;


            public class TravelerFactory  {


                    // Basic Traveler with default 1 adult
                    public static Traveler createTraveler() {
                        return new Traveler.Builder()
                                .build();
                    }

                    // Traveler with specific counts
                    public static Traveler createTravelerWithCounts(int adultCount, int childCount,
                                                                    int infantCount) {
                        Helper.requireNotNegative(adultCount, "Adult Count");
                        Helper.requireNotNegative(childCount, "Child Count");
                        Helper.requireNotNegative(infantCount, "Infant Count");

                        if (adultCount == 0 && childCount == 0 && infantCount == 0) {
                            throw new IllegalArgumentException("At least one traveler required");
                        }

                        if (infantCount > adultCount) {
                            throw new IllegalArgumentException("Infant count cannot exceed adult count");
                        }

                        return new Traveler.Builder()
                                .setAdultCount(adultCount)
                                .setChildCount(childCount)
                                .setInfantCount(infantCount)
                                .build();
                    }

                    // Traveler with names and passport numbers
                    public static Traveler createTravelerWithDetails(int adultCount, int childCount,
                                                                     int infantCount,
                                                                     List<String> travelerNames,
                                                                     List<LocalDate> travelerAges,
                                                                     List<String> passportNumbers) {
                        Traveler traveler = createTravelerWithCounts(adultCount, childCount, infantCount);

                        int totalTravelers = adultCount + childCount + infantCount;

                        if (travelerNames != null && travelerNames.size() != totalTravelers) {
                            throw new IllegalArgumentException("Number of names must match total travelers");
                        }

                        if (passportNumbers != null && passportNumbers.size() != totalTravelers) {
                            throw new IllegalArgumentException("Number of passports must match total travelers");
                        }

                        Traveler.Builder builder = new Traveler.Builder()
                                .setAdultCount(adultCount)
                                .setChildCount(childCount)
                                .setInfantCount(infantCount);

                        if (travelerNames != null) {
                            builder.setTravelerNames(travelerNames);
                        }
                        if (travelerAges != null) {
                            builder.setTravelerAges(travelerAges);
                        }
                        if (passportNumbers != null) {
                            builder.setPassportNumbers(passportNumbers);
                        }

                        return builder.copy(traveler).build();
                    }
                }



