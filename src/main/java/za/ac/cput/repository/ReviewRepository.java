package za.ac.cput.repository;

import za.ac.cput.domain.Review;
import za.ac.cput.util.Helper;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ReviewRepository implements IReviewRepository {
    private static ReviewRepository repo = null;
    private Map<Long, Review> reviewMap;

    private ReviewRepository() {
        reviewMap = new HashMap<>();
    }

    public static ReviewRepository getRepository() {
        if (repo == null) {
            repo = new ReviewRepository();
        }
        return repo;
    }

    @Override
    public Review create(Review review) {
        Helper.requireNonNull(review, "Review");
        if (review.getReviewId() == null) {
            throw new IllegalArgumentException("Review ID cannot be null");
        }
        if (reviewMap.containsKey(review.getReviewId())) {
            throw new IllegalArgumentException("Review with ID " + review.getReviewId() + " already exists");
        }
        reviewMap.put(review.getReviewId(), review);
        return review;
    }

    @Override
    public Review read(Long id) {
        Helper.requireNonNull(id, "Review ID");
        return reviewMap.get(id);
    }

    @Override
    public Review update(Review review) {
        Helper.requireNonNull(review, "Review");
        if (review.getReviewId() == null) {
            throw new IllegalArgumentException("Review ID cannot be null");
        }
        if (!reviewMap.containsKey(review.getReviewId())) {
            throw new IllegalArgumentException("Review with ID " + review.getReviewId() + " does not exist");
        }
        reviewMap.put(review.getReviewId(), review);
        return review;
    }

    @Override
    public Review delete(Long id) {
        Helper.requireNonNull(id, "Review ID");
        return reviewMap.remove(id);
    }

    @Override
    public List<Review> getAll() {
        return new ArrayList<>(reviewMap.values());
    }

    @Override
    public Review findById(Long id) {
        return read(id);
    }

    @Override
    public List<Review> findByCustomerId(Long customerId) {
        Helper.requireNonNull(customerId, "Customer ID");
        return reviewMap.values().stream()
                .filter(review -> review.getReviewer() != null &&
                        review.getReviewer().getUserId().equals(customerId))
                .collect(Collectors.toList());
    }

    @Override
    public List<Review> findByBookingId(Long bookingId) {
        Helper.requireNonNull(bookingId, "Booking ID");
        return reviewMap.values().stream()
                .filter(review -> review.getBooking() != null &&
                        review.getBooking().getBookingId().equals(bookingId))
                .collect(Collectors.toList());
    }

    @Override
    public List<Review> findByServiceType(String serviceType) {
        Helper.requireNotEmptyOrNull(serviceType, "Service Type");
        return reviewMap.values().stream()
                .filter(review -> review.getServiceType() != null &&
                        review.getServiceType().equalsIgnoreCase(serviceType))
                .collect(Collectors.toList());
    }

    @Override
    public List<Review> findByRating(int rating) {
        if (rating < 1 || rating > 5) {
            throw new IllegalArgumentException("Rating must be between 1 and 5");
        }
        return reviewMap.values().stream()
                .filter(review -> review.getRating() == rating)
                .collect(Collectors.toList());
    }

    @Override
    public double getAverageRatingByServiceType(String serviceType) {
        List<Review> reviews = findByServiceType(serviceType);
        if (reviews.isEmpty()) {
            return 0.0;
        }
        return reviews.stream()
                .mapToInt(Review::getRating)
                .average()
                .orElse(0.0);
    }
}