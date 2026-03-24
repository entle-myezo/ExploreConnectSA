package za.ac.cput.factory;

import za.ac.cput.domain.*;

import java.time.LocalDateTime;
import java.util.List;

public class HotelBookingFactory {
    /**
     * ✅ Basic factory (minimum required fields)
     */
    public static HotelBooking createHotelBooking(
            String hotelName,
            String location,
            LocalDateTime checkIn,
            LocalDateTime checkOut
    ) {

        // 🔒 Validation
        if (hotelName == null || hotelName.isEmpty())
            throw new IllegalArgumentException("Hotel name is required");

        if (location == null || location.isEmpty())
            throw new IllegalArgumentException("Location is required");

        if (checkIn == null || checkOut == null || !checkOut.isAfter(checkIn))
            throw new IllegalArgumentException("Invalid check-in/check-out dates");

        return new HotelBooking.Builder(
                hotelName,
                location,
                checkIn,
                checkOut
        ).build();
    }

    /**
     * 🔥 Full factory (advanced booking)
     */
    public static HotelBooking createFullHotelBooking(
            String hotelName,
            String location,
            LocalDateTime checkIn,
            LocalDateTime checkOut,
            String hotelId,
            String hotelAddress,
            String hotelPhone,
            int starRating,
            RoomTypeAvailable roomType,
            RoomTypeByOccupancy occupancy,
            RoomTypeByLayout layout,
            RoomTypeByBedSize bedSize,
            List<String> roomNumbers,
            boolean breakfastIncluded,
            boolean wifiIncluded,
            boolean parkingIncluded,
            List<String> specialRequests,
            String cancellationPolicy,
            Customer customer,
            Traveler traveler,
            PaymentDetails payment,
            CancellationPolicy cancellationPolicyObj
    ) {

        // Extra validation
        if (starRating < 1 || starRating > 5)
            throw new IllegalArgumentException("Star rating must be between 1 and 5");

        return new HotelBooking.Builder(
                hotelName,
                location,
                checkIn,
                checkOut
        )
                .setHotelId(hotelId)
                .setHotelAddress(hotelAddress)
                .setHotelPhone(hotelPhone)
                .setStarRating(starRating)
                .setRoomType(roomType)
                .setOccupancy(occupancy)
                .setLayout(layout)
                .setBedSize(bedSize)
                .setRoomNumbers(roomNumbers)
                .setBreakfastIncluded(breakfastIncluded)
                .setWifiIncluded(wifiIncluded)
                .setParkingIncluded(parkingIncluded)
                .setSpecialRequests(specialRequests)
                .setCancellationPolicy(cancellationPolicy)
                .setBookedBy(customer)
                .setTravelers(traveler)
                .setPayment(payment)
                .setCancellationPolicyObj(cancellationPolicyObj)
                .build();
    }
}
