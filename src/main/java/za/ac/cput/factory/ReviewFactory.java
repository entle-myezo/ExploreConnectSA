package za.ac.cput.factory;
/* ReviewFactory.java
   Review Factory class
   Author: Alakhe Mxakato (230485316)

   Date: 28 June 2026
*/
import za.ac.cput.domain.Customer;
import za.ac.cput.domain.Booking;
import za.ac.cput.domain.Review;
import za.ac.cput.util.Helper;
import za.ac.cput.util.IdGenerator;

import java.time.LocalDateTime;

public class ReviewFactory {

    private static final IdGenerator idGenerator = new IdGenerator();

    // Creates a basic review

    public static Review createReview(int rating, String comment, Customer reviewer) {
        Helper.requireNonNull(reviewer, "Reviewer");
        Helper.requireInRange(rating, 1, 5, "Rating");
        Helper.requireNotEmptyOrNull(comment, "Comment");

        Long reviewId = idGenerator.generateLongId();

        return new Review.Builder()
                .setReviewId(reviewId)
                .setRating(rating)
                .setComment(comment)
                .setReviewer(reviewer)
                .setDate(LocalDateTime.now())
                .build();
    }

    // Creates a review with service details

    public static Review createServiceReview(int rating, String comment,
                                             Customer reviewer, String serviceType,
                                             String serviceId, Booking booking) {
        Helper.requireNotEmptyOrNull(serviceType, "Service Type");
        Helper.requireNotEmptyOrNull(serviceId, "Service ID");
        Helper.requireNonNull(booking, "Booking");

        Review review = createReview(rating, comment, reviewer);

        return new Review.Builder()
                .copy(review)
                .setServiceType(serviceType)
                .setServiceId(serviceId)
                .setBooking(booking)
                .build();
    }

    // Creates a flagged review

    public static Review createFlaggedReview(int rating, String comment,
                                             Customer reviewer, boolean isFlagged) {
        Review review = createReview(rating, comment, reviewer);

        return new Review.Builder()
                .copy(review)
                .build();
    }
}
