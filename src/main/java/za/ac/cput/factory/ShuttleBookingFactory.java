package za.ac.cput.factory;

import za.ac.cput.domain.*;

import java.time.LocalDateTime;


    public class ShuttleBookingFactory {

        /**
         * ✅ Basic factory (minimum required fields)
         */
        public static ShuttleBooking createShuttleBooking(
                ShuttleCompanies company,
                String pickUpLocation,
                String dropOffLocation,
                LocalDateTime pickupTime
        ) {

            // 🔒 Validation
            if (company == null)
                throw new IllegalArgumentException("Shuttle company is required");

            if (pickUpLocation == null || pickUpLocation.isEmpty())
                throw new IllegalArgumentException("Pickup location is required");

            if (dropOffLocation == null || dropOffLocation.isEmpty())
                throw new IllegalArgumentException("Drop-off location is required");

            if (pickupTime == null)
                throw new IllegalArgumentException("Pickup time is required");

            return new ShuttleBooking.Builder(
                    company,
                    pickUpLocation,
                    dropOffLocation,
                    pickupTime
            ).build();
        }

        /**
         * 🔥 Full factory (advanced booking)
         */
        public static ShuttleBooking createFullShuttleBooking(
                ShuttleCompanies company,
                String pickUpLocation,
                String dropOffLocation,
                LocalDateTime pickupTime,
                LocalDateTime estimatedDropoffTime,
                boolean isRoundTrip,
                String returnPickupLocation,
                LocalDateTime returnPickupTime,
                int numberOfPassengers,
                String vehicleModel,
                VehicleType vehicleType,
                String licensePlate,
                String driverName,
                String driverPhone,
                boolean childSeat,
                boolean wheelchairAccessible,
                double estimatedDistance,
                Customer customer,
                Traveler traveler,
                PaymentDetails payment,
                CancellationPolicy cancellationPolicy
        ) {

            // Extra validation
            if (numberOfPassengers <= 0)
                throw new IllegalArgumentException("Passengers must be greater than 0");

            if (estimatedDropoffTime != null && estimatedDropoffTime.isBefore(pickupTime))
                throw new IllegalArgumentException("Drop-off time cannot be before pickup time");

            return new ShuttleBooking.Builder(
                    company,
                    pickUpLocation,
                    dropOffLocation,
                    pickupTime
            )
                    .setEstimatedDropoffTime(estimatedDropoffTime)
                    .setRoundTrip(isRoundTrip)
                    .setReturnPickupLocation(returnPickupLocation)
                    .setReturnPickupTime(returnPickupTime)
                    .setNumberOfPassengers(numberOfPassengers)
                    .setVehicleModel(vehicleModel)
                    .setVehicleType(vehicleType)
                    .setLicensePlate(licensePlate)
                    .setDriverName(driverName)
                    .setDriverPhone(driverPhone)
                    .setChildSeat(childSeat)
                    .setWheelchairAccessible(wheelchairAccessible)
                    .setEstimatedDistance(estimatedDistance)
                    .setBookedBy(customer)
                    .setTravelers(traveler)
                    .setPayment(payment)
                    .setCancellationPolicy(cancellationPolicy)
                    .build();
        }

}
