package za.ac.cput.factory;

import za.ac.cput.domain.*;
import za.ac.cput.util.Helper;

import java.time.LocalDateTime;

public class CarRentalBookingFactory {

    // Basic Car Rental
    public static CarRentalBooking createCarRental(String rentalCompany, String carModel,
                                                   LocalDateTime pickupDate, LocalDateTime returnDate,
                                                   Customer customer, Traveler traveler) {
        Helper.requireNotEmptyOrNull(rentalCompany, "Rental Company");
        Helper.requireNotEmptyOrNull(carModel, "Car Model");
        Helper.requireNonNull(pickupDate, "Pickup Date");
        Helper.requireNonNull(returnDate, "Return Date");
        Helper.requireNonNull(customer, "Customer");
        Helper.requireNonNull(traveler, "Traveler");

        Helper.validateDateRange(pickupDate, returnDate, "Pickup Date", "Return Date");

        return new CarRentalBooking.Builder(rentalCompany, carModel, pickupDate, returnDate)
                .setBookedBy(customer)
                .setTravelers(traveler)
                .build();
    }

    // Car Rental with insurance and extras
    public static CarRentalBooking createCarRentalWithExtras(String rentalCompany, String carModel,
                                                             LocalDateTime pickupDate, LocalDateTime returnDate,
                                                             boolean insurance, boolean gps,
                                                             boolean childSeat, boolean additionalDriver,
                                                             Customer customer, Traveler traveler) {
        CarRentalBooking rental = createCarRental(rentalCompany, carModel, pickupDate, returnDate,
                customer, traveler);

        return new CarRentalBooking.Builder(rentalCompany, carModel, pickupDate, returnDate)
                .setInsurance(insurance)
                .setGps(gps)
                .setChildSeat(childSeat)
                .setAdditionalDriver(additionalDriver)
                .copy(rental)
                .build();
    }

    // Car Rental with different return location
    public static CarRentalBooking createOneWayCarRental(String rentalCompany, String carModel,
                                                         LocalDateTime pickupDate, LocalDateTime returnDate,
                                                         String pickupLocation, String returnLocation,
                                                         Customer customer, Traveler traveler) {
        CarRentalBooking rental = createCarRental(rentalCompany, carModel, pickupDate, returnDate,
                customer, traveler);

        Helper.requireNotEmptyOrNull(pickupLocation, "Pickup Location");
        Helper.requireNotEmptyOrNull(returnLocation, "Return Location");

        return new CarRentalBooking.Builder(rentalCompany, carModel, pickupDate, returnDate)
                .setPickupLocation(pickupLocation)
                .setReturnLocation(returnLocation)
                .setDifferentReturnLocation(true)
                .copy(rental)
                .build();
    }

    // Car Rental with fuel policy and mileage limit
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

        return new CarRentalBooking.Builder(rentalCompany, carModel, pickupDate, returnDate)
                .setFuelPolicy(fuelPolicy)
                .setMileageLimit(mileageLimit)
                .setSecurityDeposit(securityDeposit)
                .copy(rental)
                .build();
    }
}
