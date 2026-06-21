/* BoardingPass.java

   BoardingPass POJO class

   Author: Somila Ndoboza (231157592)

   Date: 21 June 2026
*/

package za.ac.cput.domain;

import jakarta.persistence.Id;
import java.time.LocalDateTime;

public class BoardingPass {

    @Id
    private String bookingReference;

    private String flightNumber;
    private String seatNumber;
    private String gate;
    private LocalDateTime boardingTime;
    private String qrCode;

    protected BoardingPass() {
    }

    private BoardingPass(Builder builder) {
        this.bookingReference = builder.bookingReference;
        this.flightNumber = builder.flightNumber;
        this.seatNumber = builder.seatNumber;
        this.gate = builder.gate;
        this.boardingTime = builder.boardingTime;
        this.qrCode = builder.qrCode;
    }

    public String getBookingReference() {
        return bookingReference;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public String getGate() {
        return gate;
    }

    public LocalDateTime getBoardingTime() {
        return boardingTime;
    }

    public String getQrCode() {
        return qrCode;
    }

    public void generate() {
        this.qrCode = "BP-" + bookingReference + "-" + seatNumber;
        this.boardingTime = LocalDateTime.now().plusHours(2);
    }

    public boolean validate() {
        return qrCode != null && qrCode.startsWith("BP-");
    }

    @Override
    public String toString() {
        return "BoardingPass{" +
                "flightNumber='" + flightNumber + '\'' +
                ", seatNumber='" + seatNumber + '\'' +
                ", gate='" + gate + '\'' +
                ", boardingTime=" + boardingTime +
                '}';
    }

    public static class Builder {

        private String bookingReference;
        private String flightNumber;
        private String seatNumber;
        private String gate;
        private LocalDateTime boardingTime;
        private String qrCode;

        public Builder(FlightBooking flightBooking) {
            this.bookingReference = flightBooking.getBookingReference();
            this.flightNumber = flightBooking.getFlightNumber();
            this.seatNumber = flightBooking.getSeatNumbers();
            this.gate = "A" + (int) (Math.random() * 10);
            this.boardingTime = flightBooking.getDepartureTime().minusHours(2);
            generateQR();
        }

        private void generateQR() {
            this.qrCode = "BP-" + bookingReference + "-" + seatNumber;
        }

        public Builder setGate(String gate) {
            this.gate = gate;
            return this;
        }

        public Builder setSeatNumber(String seatNumber) {
            this.seatNumber = seatNumber;
            generateQR();
            return this;
        }

        public Builder copy(BoardingPass pass) {
            this.bookingReference = pass.bookingReference;
            this.flightNumber = pass.flightNumber;
            this.seatNumber = pass.seatNumber;
            this.gate = pass.gate;
            this.boardingTime = pass.boardingTime;
            this.qrCode = pass.qrCode;
            return this;
        }

        public BoardingPass build() {
            return new BoardingPass(this);
        }
    }
}