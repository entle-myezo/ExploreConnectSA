package za.ac.cput.factory;

public class BookingConfirmationFactory {

        private String bookingId;
        private String customerName;
        private String bookingDate;
        private String status;


        private BookingConfirmationFactory(String bookingId, String customerName, String bookingDate, String status) {
            this.bookingId = bookingId;
            this.customerName = customerName;
            this.bookingDate = bookingDate;
            this.status = status;
        }


        public static BookingConfirmationFactory createBookingConfirmation(
                String bookingId,
                String customerName,
                String bookingDate,
                String status) {


            if (isNullOrEmpty(bookingId)) {
                throw new IllegalArgumentException("bookingId cannot be null or empty");
            }
            if (isNullOrEmpty(customerName)) {
                throw new IllegalArgumentException("customerName cannot be null or empty");
            }
            if (isNullOrEmpty(bookingDate)) {
                throw new IllegalArgumentException("bookingDate cannot be null or empty");
            }
            if (isNullOrEmpty(status)) {
                throw new IllegalArgumentException("status cannot be null or empty");
            }

            return new BookingConfirmationFactory(bookingId, customerName, bookingDate, status);
        }


        private static boolean isNullOrEmpty(String str) {
            return str == null || str.trim().isEmpty();
        }


        public String getBookingId() { return bookingId; }
        public String getCustomerName() { return customerName; }
        public String getBookingDate() { return bookingDate; }
        public String getStatus() { return status; }
    }