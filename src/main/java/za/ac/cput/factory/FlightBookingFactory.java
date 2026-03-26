package za.ac.cput.factory;

import za.ac.cput.domain.*;
import za.ac.cput.util.Helper;

import java.time.LocalDateTime;

public class FlightBookingFactory {

    // Basic Flight Booking
    public static FlightBooking createFlightBooking(String flightNumber, String airline,
                                                    String fromLocation, String toLocation,
                                                    LocalDateTime departureTime,
                                                    Customer customer, Traveler traveler) {
        Helper.requireNotEmptyOrNull(flightNumber, "Flight Number");
        Helper.requireNotEmptyOrNull(airline, "Airline");
        Helper.requireNotEmptyOrNull(fromLocation, "From Location");
        Helper.requireNotEmptyOrNull(toLocation, "To Location");
        Helper.requireNonNull(departureTime, "Departure Time");
        Helper.requireNonNull(customer, "Customer");
        Helper.requireNonNull(traveler, "Traveler");

        return new FlightBooking.Builder(flightNumber, airline, fromLocation, toLocation, departureTime)
                .setBookedBy(customer)
                .setTravelers(traveler)
                .build();
    }

    // Full Flight Booking with all details
    public static FlightBooking createFullFlightBooking(String flightNumber, String airline,
                                                        String fromLocation, String toLocation,
                                                        LocalDateTime departureTime,
                                                        LocalDateTime arrivalTime,
                                                        FJourney journeyType,
                                                        FBookingClass bookingClass,
                                                        FlightType aircraftType,
                                                        Customer customer,
                                                        Traveler traveler,
                                                        CancellationPolicy cancellationPolicy,
                                                        double subtotal, double taxes) {
        Helper.requireNonNull(arrivalTime, "Arrival Time");
        Helper.requireNonNull(journeyType, "Journey Type");
        Helper.requireNonNull(bookingClass, "Booking Class");
        Helper.requirePositive(subtotal, "Subtotal");

        FlightBooking flight = createFlightBooking(flightNumber, airline, fromLocation,
                toLocation, departureTime, customer, traveler);

        return new FlightBooking.Builder(flightNumber, airline, fromLocation, toLocation, departureTime)
                .setArrivalTime(arrivalTime)
                .setJourneyType(journeyType)
                .setBookingClass(bookingClass)
                .setAircraftType(aircraftType)
                .setBookedBy(customer)
                .setTravelers(traveler)
                .setCancellationPolicy(cancellationPolicy)
                .setSubtotal(subtotal)
                .setTaxes(taxes)
                .setTotalPrice(subtotal + taxes)
                .copy(flight)
                .build();
    }

    // Direct Flight Booking
    public static FlightBooking createDirectFlightBooking(String flightNumber, String airline,
                                                          String fromLocation, String toLocation,
                                                          LocalDateTime departureTime,
                                                          LocalDateTime arrivalTime,
                                                          Customer customer, Traveler traveler) {
        FlightBooking flight = createFlightBooking(flightNumber, airline, fromLocation,
                toLocation, departureTime, customer, traveler);

        return new FlightBooking.Builder(flightNumber, airline, fromLocation, toLocation, departureTime)
                .setArrivalTime(arrivalTime)
                .setDirectFlight(true)
                .setStopOvers(0)
                .copy(flight)
                .build();
    }
}
