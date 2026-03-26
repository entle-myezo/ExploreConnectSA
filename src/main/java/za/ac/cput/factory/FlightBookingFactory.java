package za.ac.cput.factory;

import za.ac.cput.domain.*;

import java.time.LocalDateTime;

public class FlightBookingFactory {
    /**
     * Basic factory (minimum required fields)
     */
    public static FlightBooking createFlightBooking(
            String flightNumber,
            String airline,
            String fromLocation,
            String toLocation,
            LocalDateTime departureTime
    ) {

        // 🔒 Validation
        if (flightNumber == null || flightNumber.isEmpty())
            throw new IllegalArgumentException("Flight number is required");

        if (airline == null || airline.isEmpty())
            throw new IllegalArgumentException("Airline is required");

        if (fromLocation == null || toLocation == null)
            throw new IllegalArgumentException("Locations cannot be null");

        if (departureTime == null)
            throw new IllegalArgumentException("Departure time is required");

        return new FlightBooking.Builder(
                flightNumber,
                airline,
                fromLocation,
                toLocation,
                departureTime
        ).build();
    }

    /**
     *  Full factory (advanced booking)
     */
    public static FlightBooking createFullFlightBooking(
            String flightNumber,
            String airline,
            String fromLocation,
            String toLocation,
            LocalDateTime departureTime,
            LocalDateTime arrivalTime,
            FJourney journeyType,
            FBookingClass bookingClass,
            FlightType aircraftType,
            boolean isDirectFlight,
            int stopOvers,
            double baggageAllowance,
            boolean travelInsurance,
            Customer customer,
            Traveler traveler,
            PaymentDetails payment,
            CancellationPolicy cancellationPolicy
    ) {

        // Extra validation
        if (arrivalTime != null && arrivalTime.isBefore(departureTime))
            throw new IllegalArgumentException("Arrival time cannot be before departure");

        return new FlightBooking.Builder(
                flightNumber,
                airline,
                fromLocation,
                toLocation,
                departureTime
        )
                .setArrivalTime(arrivalTime)
                .setJourneyType(journeyType)
                .setBookingClass(bookingClass)
                .setAircraftType(aircraftType)
                .setDirectFlight(isDirectFlight)
                .setStopOvers(stopOvers)
                .setBaggageAllowance(baggageAllowance)
                .setTravelInsurance(travelInsurance)
                .setBookedBy(customer)
                .setTravelers(traveler)
                .setPayment(payment)
                .setCancellationPolicy(cancellationPolicy)
                .build();
    }

}
