package za.ac.cput.factory;

import za.ac.cput.domain.BoardingPass;
import za.ac.cput.domain.FlightBooking;
import za.ac.cput.util.Helper;

import java.util.HashMap;

public class BoardingPassFactory {
    // Boarding Pass from Flight Booking
    public static BoardingPass createBoardingPass(FlightBooking flightBooking) {
        Helper.requireNonNull(flightBooking, "Flight Booking");

        return new BoardingPass.Builder(flightBooking)
                .build();
    }

    // Boarding Pass with specific gate
    public static BoardingPass createBoardingPassWithGate(FlightBooking flightBooking,
                                                          String gate) {
        BoardingPass pass = createBoardingPass(flightBooking);

        Helper.requireNotEmptyOrNull(gate, "Gate");

        return new BoardingPass.Builder(flightBooking)
                .setGate(gate)
                .copy(pass)
                .build();
    }

    // Boarding Pass with assigned seat
    public static BoardingPass createBoardingPassWithSeat(FlightBooking flightBooking,
                                                          String seatNumber) {
        BoardingPass pass = createBoardingPass(flightBooking);

        Helper.requireNotEmptyOrNull(seatNumber, "Seat Number");

        return new BoardingPass.Builder(flightBooking)
                .setSeatNumber(seatNumber)
                .copy(pass)
                .build();
    }
}