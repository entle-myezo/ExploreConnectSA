package za.ac.cput.factory;

import za.ac.cput.domain.*;
import za.ac.cput.util.Helper;

import java.time.LocalDateTime;


public class ShuttleBookingFactory {

    // Basic Shuttle Booking
    public static ShuttleBooking createShuttleBooking(ShuttleCompanies company,
                                                      String pickUpLocation,
                                                      String dropOffLocation,
                                                      LocalDateTime pickupTime,
                                                      Customer customer,
                                                      Traveler traveler) {
        Helper.requireNonNull(company, "Company");
        Helper.requireNotEmptyOrNull(pickUpLocation, "Pickup Location");
        Helper.requireNotEmptyOrNull(dropOffLocation, "Dropoff Location");
        Helper.requireNonNull(pickupTime, "Pickup Time");
        Helper.requireNonNull(customer, "Customer");
        Helper.requireNonNull(traveler, "Traveler");

        return new ShuttleBooking.Builder(company, pickUpLocation, dropOffLocation, pickupTime)
                .setBookedBy(customer)
                .setTravelers(traveler)
                .build();
    }

    // Shuttle with round trip
    public static ShuttleBooking createRoundTripShuttle(ShuttleCompanies company,
                                                        String pickUpLocation,
                                                        String dropOffLocation,
                                                        LocalDateTime pickupTime,
                                                        LocalDateTime returnPickupTime,
                                                        Customer customer,
                                                        Traveler traveler) {
        ShuttleBooking shuttle = createShuttleBooking(company, pickUpLocation, dropOffLocation,
                pickupTime, customer, traveler);

        Helper.requireNonNull(returnPickupTime, "Return Pickup Time");

        return new ShuttleBooking.Builder(company, pickUpLocation, dropOffLocation, pickupTime)
                .setRoundTrip(true)
                .setReturnPickupTime(returnPickupTime)
                .copy(shuttle)
                .build();
    }

    // Shuttle with vehicle and driver details
    public static ShuttleBooking createShuttleWithVehicle(ShuttleCompanies company,
                                                          String pickUpLocation,
                                                          String dropOffLocation,
                                                          LocalDateTime pickupTime,
                                                          VehicleType vehicleType,
                                                          String vehicleModel,
                                                          String driverName,
                                                          String driverPhone,
                                                          Customer customer,
                                                          Traveler traveler) {
        ShuttleBooking shuttle = createShuttleBooking(company, pickUpLocation, dropOffLocation,
                pickupTime, customer, traveler);

        Helper.requireNonNull(vehicleType, "Vehicle Type");
        Helper.requireNotEmptyOrNull(driverName, "Driver Name");
        Helper.isValidSouthAfricanPhone(driverPhone);

        return new ShuttleBooking.Builder(company, pickUpLocation, dropOffLocation, pickupTime)
                .setVehicleType(vehicleType)
                .setVehicleModel(vehicleModel)
                .setDriverName(driverName)
                .setDriverPhone(driverPhone)
                .copy(shuttle)
                .build();
    }

    // Shuttle with special accommodations
    public static ShuttleBooking createAccessibleShuttle(ShuttleCompanies company,
                                                         String pickUpLocation,
                                                         String dropOffLocation,
                                                         LocalDateTime pickupTime,
                                                         boolean wheelchairAccessible,
                                                         boolean childSeat,
                                                         Customer customer,
                                                         Traveler traveler) {
        ShuttleBooking shuttle = createShuttleBooking(company, pickUpLocation, dropOffLocation,
                pickupTime, customer, traveler);

        return new ShuttleBooking.Builder(company, pickUpLocation, dropOffLocation, pickupTime)
                .setWheelchairAccessible(wheelchairAccessible)
                .setChildSeat(childSeat)
                .copy(shuttle)
                .build();
    }
}
