package za.ac.cput.repository;

import java.util.List;
import za.ac.cput.domain.Review;

    public interface IReviewRepository extends IRepository<Review, Long> {
            Review findById(Long id);

            List<Review> findByCustomerId(Long customerId);
        List<Review> findByBookingId(Long bookingId);
        List<Review> findByServiceType(String serviceType);
        List<Review> findByRating(int rating);
        double getAverageRatingByServiceType(String serviceType);
}
