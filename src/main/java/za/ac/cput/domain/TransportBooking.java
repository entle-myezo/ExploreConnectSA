/* TransportBooking.java

   TransportBooking POJO class

   Author: Somila Ndoboza (231157592)

   Date: 21 June 2026
*/

package za.ac.cput.domain;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class TransportBooking extends Booking {

    @Id
    protected String transportId;

    protected String provider;
    protected VehicleType vehicleType;
    protected LocalDateTime bookingTime;
    protected double distance;
    protected String specialInstructions;

    protected TransportBooking() {
    }

    protected TransportBooking(Builder builder) {
        this.transportId = builder.transportId;
        this.provider = builder.provider;
        this.vehicleType = builder.vehicleType;
        this.bookingTime = builder.bookingTime;
        this.distance = builder.distance;
        this.specialInstructions = builder.specialInstructions;
    }

    public String getTransportId() {
        return transportId;
    }

    public String getProvider() {
        return provider;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public LocalDateTime getBookingTime() {
        return bookingTime;
    }

    public double getDistance() {
        return distance;
    }

    public String getSpecialInstructions() {
        return specialInstructions;
    }

    @Override
    public String toString() {
        return "TransportBooking{" +
                "transportId='" + transportId + '\'' +
                ", provider='" + provider + '\'' +
                ", vehicleType=" + vehicleType +
                ", bookingTime=" + bookingTime +
                '}';
    }

    public abstract static class Builder {

        protected Long bookingId;
        protected String bookingReference;
        protected LocalDateTime bookingDate;
        protected LocalDateTime lastModified;
        protected BookingStatus status;
        protected double subtotal;
        protected double taxes;
        protected double totalPrice;
        protected String currency;
        protected Customer bookedBy;
        protected Traveler travelers;
        protected PaymentDetails payment;
        protected CancellationPolicy cancellationPolicy;

        protected String transportId;
        protected String provider;
        protected VehicleType vehicleType;
        protected LocalDateTime bookingTime;
        protected double distance;
        protected String specialInstructions;

        // Setter methods remain exactly as in your original code
        // copy() method remains exactly as in your original code

        public abstract TransportBooking build();
    }
}