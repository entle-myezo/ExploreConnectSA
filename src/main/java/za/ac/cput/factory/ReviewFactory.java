package za.ac.cput.factory;

import za.ac.cput.domain.Booking;
import za.ac.cput.domain.Customer;
import za.ac.cput.domain.Review;
import za.ac.cput.util.Helper;


public class ReviewFactory {

    // Basic Review
    public static Review createReview(int rating, String comment, Customer reviewer) {
        Helper.requireNonNull(reviewer, "Reviewer");

        if (rating < 1 || rating > 5) {
            throw new IllegalArgumentException("Rating must be between 1 and 5");
        }

        Helper.requireNotEmptyOrNull(comment, "Comment");

        return new Review.Builder(rating, comment, reviewer)
                .build();
    }

    // Review with service details
    public static Review createServiceReview(int rating, String comment,
                                             Customer reviewer, String serviceType,
                                             String serviceId, Booking booking) {
        Review review = createReview(rating, comment, reviewer);

        Helper.requireNotEmptyOrNull(serviceType, "Service Type");
        Helper.requireNotEmptyOrNull(serviceId, "Service ID");
        Helper.requireNonNull(booking, "Booking");

        return new Review.Builder(rating, comment, reviewer)
                .setServiceType(serviceType)
                .setServiceId(serviceId)
                .setBooking(booking)
                .copy(review)
                .build();
    }
}

