package za.ac.cput.factory;

import za.ac.cput.domain.*;
import za.ac.cput.util.Helper;

import java.time.LocalDateTime;
import java.util.List;

public class HotelBookingFactory {

    // Basic Hotel Booking
    public static HotelBooking createHotelBooking(String hotelName, String location,
                                                  LocalDateTime checkIn, LocalDateTime checkOut,
                                                  Customer customer, Traveler traveler) {
        Helper.requireNotEmptyOrNull(hotelName, "Hotel Name");
        Helper.requireNotEmptyOrNull(location, "Location");
        Helper.requireNonNull(checkIn, "Check-in Date");
        Helper.requireNonNull(checkOut, "Check-out Date");
        Helper.requireNonNull(customer, "Customer");
        Helper.requireNonNull(traveler, "Traveler");

        Helper.validateDateRange(checkIn, checkOut, "Check-in", "Check-out");

        return new HotelBooking.Builder(hotelName, location, checkIn, checkOut)
                .setBookedBy(customer)
                .setTravelers(traveler)
                .build();
    }

    // Full Hotel Booking with room configuration
    public static HotelBooking createFullHotelBooking(String hotelName, String location,
                                                      LocalDateTime checkIn, LocalDateTime checkOut,
                                                      RoomTypeAvailable roomType,
                                                      RoomTypeByOccupancy occupancy,
                                                      RoomTypeByLayout layout,
                                                      RoomTypeByBedSize bedSize,
                                                      int numberOfRooms,
                                                      Customer customer, Traveler traveler,
                                                      boolean breakfastIncluded,
                                                      boolean wifiIncluded) {
        HotelBooking hotel = createHotelBooking(hotelName, location, checkIn, checkOut,
                customer, traveler);

        Helper.requirePositive(numberOfRooms, "Number of Rooms");
        Helper.requireNonNull(roomType, "Room Type");
        Helper.requireNonNull(occupancy, "Occupancy");

        return new HotelBooking.Builder(hotelName, location, checkIn, checkOut)
                .setRoomType(roomType)
                .setOccupancy(occupancy)
                .setLayout(layout)
                .setBedSize(bedSize)
                .setBreakfastIncluded(breakfastIncluded)
                .setWifiIncluded(wifiIncluded)
                .copy(hotel)
                .build();
    }

    // Hotel Booking with special requests
    public static HotelBooking createHotelBookingWithRequests(String hotelName, String location,
                                                              LocalDateTime checkIn, LocalDateTime checkOut,
                                                              Customer customer, Traveler traveler,
                                                              List<String> specialRequests) {
        HotelBooking hotel = createHotelBooking(hotelName, location, checkIn, checkOut,
                customer, traveler);

        Helper.requireNonNull(specialRequests, "Special Requests");

        return new HotelBooking.Builder(hotelName, location, checkIn, checkOut)
                .setSpecialRequests(specialRequests)
                .copy(hotel)
                .build();
    }
}
