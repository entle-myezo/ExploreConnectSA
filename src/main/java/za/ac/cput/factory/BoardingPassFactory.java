package za.ac.cput.factory;

import za.ac.cput.domain.BoardingPass;
import za.ac.cput.domain.FlightBooking;

import java.util.HashMap;

public class BoardingPassRepository implements IBoardingPassRepository {

    private static BoardingPassRepository repo = null;
    private Map<String, BoardingPass> boardingPassMap;

    private BoardingPassRepository() {
        boardingPassMap = new HashMap<>();
    }

    public static BoardingPassRepository getRepository() {
        if (repo == null) repo = new BoardingPassRepository();
        return repo;
    }

    @Override
    public BoardingPass create(BoardingPass boardingPass) {
        Helper.requireNonNull(boardingPass, "Boarding Pass");
        if (boardingPass.getBookingReference() == null) {
            throw new IllegalArgumentException("Booking Reference cannot be null");
        }
        if (boardingPassMap.containsKey(boardingPass.getBookingReference())) {
            throw new IllegalArgumentException("Boarding Pass for booking " + boardingPass.getBookingReference() + " already exists");
        }
        boardingPassMap.put(boardingPass.getBookingReference(),
                boardingPass);
        return boardingPass;
    }

    @Override
    public BoardingPass read(String id) {
        Helper.requireNonNull(id, "Booking Reference");
        return boardingPassMap.get(id);
    }

    @Override
    public BoardingPass update(BoardingPass boardingPass) {
        Helper.requireNonNull(boardingPass, "Boarding Pass");
        if (boardingPass.getBookingReference() == null) throw new IllegalArgumentException("Booking Reference cannot be null");
        if (!boardingPassMap.containsKey(boardingPass.getBookingReference()))
            throw new IllegalArgumentException("Boarding Pass for booking " + boardingPass.getBookingReference() + " does not exist");

        boardingPassMap.put(boardingPass.getBookingReference(), boardingPass);
        return boardingPass;
    }

    @Override
    public BoardingPass delete(String id) {
        Helper.requireNonNull(id, "Booking Reference");
        return boardingPassMap.remove(id);
    }

    @Override
    public List<BoardingPass> getAll() {
        return new ArrayList<>(boardingPassMap.values());
    }

    @Override
    public BoardingPass findById(String id) {
        return read(id);
    }

    @Override
    public BoardingPass findByBookingReference(String bookingReference) {
        Helper.requireNotEmptyOrNull(bookingReference, "Booking Reference");
        return boardingPassMap.get(bookingReference);
    }

    @Override
    public BoardingPass findByFlightNumber(String flightNumber) {
        Helper.requireNotEmptyOrNull(flightNumber, "Flight Number");
        return boardingPassMap.values().stream()
                .filter(pass -> pass.getFlightNumber() != null &&
                        pass.getFlightNumber().equals(flightNumber))
                .findFirst()
                .orElse(null);
    }
}