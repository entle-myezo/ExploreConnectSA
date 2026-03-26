package za.ac.cput.factory;

import za.ac.cput.domain.*;

import java.time.LocalDateTime;

public class CarRentalBookingFactory {
    public static CarRentalBooking createCarRentalBooking(
            String rentalCompany,
            String carModel,
            LocalDateTime pickupDate,
            LocalDateTime returnDate
    ) {

        // Basic validation
        if (rentalCompany == null || rentalCompany.isEmpty())
            throw new IllegalArgumentException("Rental company is required");

        if (carModel == null || carModel.isEmpty())
            throw new IllegalArgumentException("Car model is required");

        if (pickupDate == null || returnDate == null || !returnDate.isAfter(pickupDate))
            throw new IllegalArgumentException("Invalid rental dates");

        // Create object using Builder
        return new CarRentalBooking.Builder(
                rentalCompany,
                carModel,
                pickupDate,
                returnDate
        ).build();
    }

    //  FULL VERSION (with all optional fields)
    public static CarRentalBooking createFullCarRentalBooking(
            String rentalCompany,
            String carModel,
            LocalDateTime pickupDate,
            LocalDateTime returnDate,
            String carCategory,
            VehicleType vehicleType,
            String pickupLocation,
            String returnLocation,
            boolean insurance,
            Customer customer,
            Traveler traveler,
            PaymentDetails payment,
            CancellationPolicy cancellationPolicy
    ) {

        return new CarRentalBooking.Builder(
                rentalCompany,
                carModel,
                pickupDate,
                returnDate
        )
                .setCarCategory(carCategory)
                .setVehicleType(vehicleType)
                .setPickupLocation(pickupLocation)
                .setReturnLocation(returnLocation)
                .setInsurance(insurance)
                .setBookedBy(customer)
                .setTravelers(traveler)
                .setPayment(payment)
                .setCancellationPolicy(cancellationPolicy)
                .build();
    }
}
