package za.ac.cput.factory;
/* CarRentalBookingFactory.java

   CarRentalBooking Factory class

   Author: Zamandlovu C Ndlovu (211204803)

   Date: 28 June 2026
*/
import za.ac.cput.domain.*;
import za.ac.cput.util.Helper;
import za.ac.cput.util.IdGenerator;

import java.time.LocalDateTime;

public class CarRentalBookingFactory{
    private static final IdGenerator idGenerator = new IdGenerator();

    //Creates a basic car rental

    public static CarRentalBooking createCarRental(String rentalCompany, String carModel,
                                                   LocalDateTime pickupDate, LocalDateTime returnDate,
                                                   Customer customer, Traveler traveler) {
        Helper.requireNotEmptyOrNull(rentalCompany, "Rental Company");
        Helper.requireNotEmptyOrNull(carModel, "Car Model");
        Helper.requireNonNullDate(pickupDate, "Pickup Date");
        Helper.requireNonNullDate(returnDate, "Return Date");
        Helper.requireDateRange(pickupDate, returnDate, "Pickup Date", "Return Date");

        String bookingReference = idGenerator.generateBookingReference("CAR");

        return new CarRentalBooking.Builder()
                .setBookingReference(bookingReference)
                .setBookingDate(LocalDateTime.now())
                .setLastModified(LocalDateTime.now())
                .setBookedBy(customer)
                .setTravelers(traveler)
                .setCurrency("ZAR")
                .setProvider(rentalCompany)
                .setVehicleType(VehicleType.FourSeater)
                .setRentalCompany(rentalCompany)
                .setCarModel(carModel)
                .setPickupDate(pickupDate)
                .setReturnDate(returnDate)
                .setRentalDays(calculateRentalDays(pickupDate, returnDate))
                .build();
    }

    // Creates a car rental with extras

    public static CarRentalBooking createCarRentalWithExtras(String rentalCompany, String carModel,
                                                             LocalDateTime pickupDate, LocalDateTime returnDate,
                                                             boolean insurance, boolean gps,
                                                             boolean childSeat, boolean additionalDriver,
                                                             Customer customer, Traveler traveler) {
        CarRentalBooking rental = createCarRental(rentalCompany, carModel, pickupDate, returnDate,
                customer, traveler);

        return new CarRentalBooking.Builder()
                .copy(rental)
                .setInsurance(insurance)
                .setGps(gps)
                .setChildSeat(childSeat)
                .setAdditionalDriver(additionalDriver)
                .build();
    }

    // Creates a one-way car rental

    public static CarRentalBooking createOneWayCarRental(String rentalCompany, String carModel,
                                                         LocalDateTime pickupDate, LocalDateTime returnDate,
                                                         String pickupLocation, String returnLocation,
                                                         Customer customer, Traveler traveler) {
        CarRentalBooking rental = createCarRental(rentalCompany, carModel, pickupDate, returnDate,
                customer, traveler);

        Helper.requireNotEmptyOrNull(pickupLocation, "Pickup Location");
        Helper.requireNotEmptyOrNull(returnLocation, "Return Location");

        return new CarRentalBooking.Builder()
                .copy(rental)
                .setPickupLocation(pickupLocation)
                .setReturnLocation(returnLocation)
                .setDifferentReturnLocation(true)
                .build();
    }

    //Creates a car rental with policy details

    public static CarRentalBooking createCarRentalWithPolicy(String rentalCompany, String carModel,
                                                             LocalDateTime pickupDate, LocalDateTime returnDate,
                                                             String fuelPolicy, int mileageLimit,
                                                             double securityDeposit,
                                                             Customer customer, Traveler traveler) {
        CarRentalBooking rental = createCarRental(rentalCompany, carModel, pickupDate, returnDate,
                customer, traveler);

        Helper.requireNotEmptyOrNull(fuelPolicy, "Fuel Policy");
        Helper.requirePositive(mileageLimit, "Mileage Limit");
        Helper.requirePositive(securityDeposit, "Security Deposit");

        return new CarRentalBooking.Builder()
                .copy(rental)
                .setFuelPolicy(fuelPolicy)
                .setMileageLimit(mileageLimit)
                .setSecurityDeposit(securityDeposit)
                .build();
    }

    // Calculates rental days between two dates

    private static double calculateRentalDays(LocalDateTime pickupDate, LocalDateTime returnDate) {
        return java.time.temporal.ChronoUnit.DAYS.between(pickupDate, returnDate);
    }
}