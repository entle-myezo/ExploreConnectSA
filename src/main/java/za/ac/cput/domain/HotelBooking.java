package za.ac.cput.domain;

import za.ac.cput.util.IdGenerator;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class HotelBooking extends Booking {
    private String hotelId;
    private String hotelName;
    private String location;
    private String hotelAddress;
    private String hotelPhone;
    private int starRating;
    private CancellationPolicy cancellationPolicyObj;
    private LocalDateTime checkIn;
    private LocalDateTime checkOut;
    private RoomTypeAvailable roomType;
    private RoomTypeByOccupancy occupancy;
    private RoomTypeByLayout layout;
    private RoomTypeByBedSize bedSize;
    private List<String> roomNumbers;
    private boolean breakfastIncluded;
    private boolean wifiIncluded;
    private boolean parkingIncluded;
    private List<String> specialRequests;
    private CancellationPolicy cancellationPolicy;

    private HotelBooking(Builder builder) {
        // Booking fields
        this.bookingId = builder.bookingId;
        this.bookingReference = builder.bookingReference;
        this.bookingDate = builder.bookingDate;
        this.lastModified = builder.lastModified;
        this.status = builder.status;
        this.subtotal = builder.subtotal;
        this.taxes = builder.taxes;
        this.totalPrice = builder.totalPrice;
        this.currency = builder.currency;
        this.bookedBy = builder.bookedBy;
        this.travelers = builder.travelers;
        this.payment = builder.payment;
        this.cancellationPolicyObj = builder.cancellationPolicyObj;

        // Hotel specific fields
        this.hotelId = builder.hotelId;
        this.hotelName = builder.hotelName;
        this.location = builder.location;
        this.hotelAddress = builder.hotelAddress;
        this.hotelPhone = builder.hotelPhone;
        this.starRating = builder.starRating;
        this.checkIn = builder.checkIn;
        this.checkOut = builder.checkOut;
        this.roomType = builder.roomType;
        this.occupancy = builder.occupancy;
        this.layout = builder.layout;
        this.bedSize = builder.bedSize;
        this.roomNumbers = builder.roomNumbers != null ? builder.roomNumbers : new ArrayList<>();
        this.breakfastIncluded = builder.breakfastIncluded;
        this.wifiIncluded = builder.wifiIncluded;
        this.parkingIncluded = builder.parkingIncluded;
        this.specialRequests = builder.specialRequests != null ? builder.specialRequests : new ArrayList<>();
        this.cancellationPolicy = builder.cancellationPolicy;
    }

    // Getters
    public String getHotelId() { return hotelId; }
    public String getHotelName() { return hotelName; }
    public String getLocation() { return location; }
    public String getHotelAddress() { return hotelAddress; }
    public String getHotelPhone() { return hotelPhone; }
    public int getStarRating() { return starRating; }
    public LocalDateTime getCheckIn() { return checkIn; }
    public LocalDateTime getCheckOut() { return checkOut; }
    public RoomTypeAvailable getRoomType() { return roomType; }
    public RoomTypeByOccupancy getOccupancy() { return occupancy; }
    public RoomTypeByLayout getLayout() { return layout; }
    public RoomTypeByBedSize getBedSize() { return bedSize; }
    public List<String> getRoomNumbers() { return roomNumbers; }
    public boolean isBreakfastIncluded() { return breakfastIncluded; }
    public boolean isWifiIncluded() { return wifiIncluded; }
    public boolean isParkingIncluded() { return parkingIncluded; }
    public List<String> getSpecialRequests() { return specialRequests; }
    public CancellationPolicy getCancellationPolicy() { return cancellationPolicy; }
    public CancellationPolicy getCancellationPolicyObj(){
        return cancellationPolicyObj;
    }

    // Business methods
    public long calculateNights() {
        return ChronoUnit.DAYS.between(checkIn, checkOut);
    }

    public boolean checkRoomAvailability() {
        // Implementation would check with hotel system
        return true;
    }

    public boolean requestEarlyCheckIn(LocalDateTime requestedTime) {
        return requestedTime.isBefore(checkIn) &&
                requestedTime.isAfter(checkIn.minusHours(4));
    }

    public boolean requestLateCheckOut(LocalDateTime requestedTime) {
        return requestedTime.isAfter(checkOut) &&
                requestedTime.isBefore(checkOut.plusHours(4));
    }

    public void addSpecialRequest(String request) {
        this.specialRequests.add(request);
    }

    @Override
    public Booking modifyBooking() {
        this.lastModified = LocalDateTime.now();
        return this;
    }

    @Override
    public String getBookingDetails() {
        return String.format("Hotel %s in %s from %s to %s",
                hotelName, location, checkIn.toLocalDate(), checkOut.toLocalDate());
    }

    @Override
    public Invoice generateInvoice() {
        return new Invoice.Builder(this).build();
    }

    @Override
    public String toString() {
        return "HotelBooking{" +
                "bookingId=" + bookingId +
                ", hotelName='" + hotelName + '\'' +
                ", location='" + location + '\'' +
                ", starRating=" + starRating +
                ", checkIn=" + checkIn +
                ", checkOut=" + checkOut +
                ", totalPrice=" + totalPrice +
                '}';
    }

    public static class Builder {
        // Booking fields
        private Long bookingId;
        private String bookingReference;
        private LocalDateTime bookingDate;
        private LocalDateTime lastModified;
        private BookingStatus status;
        private double subtotal;
        private double taxes;
        private double totalPrice;
        private String currency;
        private Customer bookedBy;
        private Traveler travelers;
        private PaymentDetails payment;
        private CancellationPolicy cancellationPolicyObj;

        // Hotel specific fields
        private String hotelId;
        private String hotelName;
        private String location;
        private String hotelAddress;
        private String hotelPhone;
        private int starRating;
        private LocalDateTime checkIn;
        private LocalDateTime checkOut;
        private RoomTypeAvailable roomType;
        private RoomTypeByOccupancy occupancy;
        private RoomTypeByLayout layout;
        private RoomTypeByBedSize bedSize;
        private List<String> roomNumbers;
        private boolean breakfastIncluded;
        private boolean wifiIncluded;
        private boolean parkingIncluded;
        private List<String> specialRequests;
        private CancellationPolicy cancellationPolicy;

        public Builder(String hotelName, String location,
                       LocalDateTime checkIn, LocalDateTime checkOut) {
            this.bookingId = IdGenerator.getInstance().generateId();
            this.bookingReference = "HTL-" + IdGenerator.getInstance().toString().substring(0, 8);
            this.bookingDate = LocalDateTime.now();
            this.lastModified = LocalDateTime.now();
            this.status = BookingStatus.PENDING;
            this.currency = "ZAR";

            this.hotelName = hotelName;
            this.location = location;
            this.checkIn = checkIn;
            this.checkOut = checkOut;
        }

        public Builder setHotelId(String hotelId) {
            this.hotelId = hotelId;
            return this;
        }

        public Builder setHotelAddress(String hotelAddress) {
            this.hotelAddress = hotelAddress;
            return this;
        }

        public Builder setHotelPhone(String hotelPhone) {
            this.hotelPhone = hotelPhone;
            return this;
        }

        public Builder setStarRating(int starRating) {
            this.starRating = starRating;
            return this;
        }

        public Builder setRoomType(RoomTypeAvailable roomType) {
            this.roomType = roomType;
            return this;
        }

        public Builder setOccupancy(RoomTypeByOccupancy occupancy) {
            this.occupancy = occupancy;
            return this;
        }

        public Builder setLayout(RoomTypeByLayout layout) {
            this.layout = layout;
            return this;
        }

        public Builder setBedSize(RoomTypeByBedSize bedSize) {
            this.bedSize = bedSize;
            return this;
        }

        public Builder setRoomNumbers(List<String> roomNumbers) {
            this.roomNumbers = roomNumbers;
            return this;
        }

        public Builder addRoomNumber(String roomNumber) {
            if (this.roomNumbers == null) {
                this.roomNumbers = new ArrayList<>();
            }
            this.roomNumbers.add(roomNumber);
            return this;
        }

        public Builder setBreakfastIncluded(boolean breakfastIncluded) {
            this.breakfastIncluded = breakfastIncluded;
            return this;
        }

        public Builder setWifiIncluded(boolean wifiIncluded) {
            this.wifiIncluded = wifiIncluded;
            return this;
        }

        public Builder setParkingIncluded(boolean parkingIncluded) {
            this.parkingIncluded = parkingIncluded;
            return this;
        }

        public Builder setSpecialRequests(List<String> specialRequests) {
            this.specialRequests = specialRequests;
            return this;
        }

        public Builder addSpecialRequest(String request) {
            if (this.specialRequests == null) {
                this.specialRequests = new ArrayList<>();
            }
            this.specialRequests.add(request);
            return this;
        }

        public Builder setCancellationPolicy(CancellationPolicy cancellationPolicy) {
            this.cancellationPolicy = cancellationPolicy;
            return this;
        }

        public Builder setBookedBy(Customer bookedBy) {
            this.bookedBy = bookedBy;
            return this;
        }

        public Builder setTravelers(Traveler travelers) {
            this.travelers = travelers;
            return this;
        }

        public Builder setPayment(PaymentDetails payment) {
            this.payment = payment;
            return this;
        }

        public Builder setCancellationPolicyObj(CancellationPolicy cancellationPolicyObj) {
            this.cancellationPolicyObj = cancellationPolicyObj;
            return this;
        }

        public Builder setSubtotal(double subtotal) {
            this.subtotal = subtotal;
            return this;
        }

        public Builder setTaxes(double taxes) {
            this.taxes = taxes;
            return this;
        }

        public Builder setTotalPrice(double totalPrice) {
            this.totalPrice = totalPrice;
            return this;
        }

        public Builder copy(HotelBooking hotelBooking) {
            this.bookingId = hotelBooking.bookingId;
            this.bookingReference = hotelBooking.bookingReference;
            this.bookingDate = hotelBooking.bookingDate;
            this.lastModified = hotelBooking.lastModified;
            this.status = hotelBooking.status;
            this.subtotal = hotelBooking.subtotal;
            this.taxes = hotelBooking.taxes;
            this.totalPrice = hotelBooking.totalPrice;
            this.currency = hotelBooking.currency;
            this.bookedBy = hotelBooking.bookedBy;
            this.travelers = hotelBooking.travelers;
            this.payment = hotelBooking.payment;
            this.cancellationPolicyObj = hotelBooking.cancellationPolicyObj;

            this.hotelId = hotelBooking.hotelId;
            this.hotelName = hotelBooking.hotelName;
            this.location = hotelBooking.location;
            this.hotelAddress = hotelBooking.hotelAddress;
            this.hotelPhone = hotelBooking.hotelPhone;
            this.starRating = hotelBooking.starRating;
            this.checkIn = hotelBooking.checkIn;
            this.checkOut = hotelBooking.checkOut;
            this.roomType = hotelBooking.roomType;
            this.occupancy = hotelBooking.occupancy;
            this.layout = hotelBooking.layout;
            this.bedSize = hotelBooking.bedSize;
            this.roomNumbers = hotelBooking.roomNumbers;
            this.breakfastIncluded = hotelBooking.breakfastIncluded;
            this.wifiIncluded = hotelBooking.wifiIncluded;
            this.parkingIncluded = hotelBooking.parkingIncluded;
            this.specialRequests = hotelBooking.specialRequests;
            this.cancellationPolicy = hotelBooking.cancellationPolicy;
            return this;
        }

        public HotelBooking build() {
            return new HotelBooking(this);
        }
    }
}
