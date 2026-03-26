package za.ac.cput.repository;

import za.ac.cput.domain.Review;
import za.ac.cput.util.Helper;
import java.util.ArrayList;
import java.util.List;

public class ReviewRepository implements IReviewRepository{

    private static ReviewRepository repository = null;


    private List<Review> reviewList;
    private ReviewRepository() {
        reviewList = new ArrayList<>();
    }

    public static ReviewRepository getRepository() {
        if (repository == null) {
            repository = new ReviewRepository();
        }
        return repository;
    }

    @Override
    public Review create(Review review) {

        Helper.requireNonNull(review, "review");

        reviewList.add(review);
        return review;
    }

    @Override
    public Review read(Long reviewId) {

        Helper.requireNonNull(reviewId, "reviewId");

        for (Review review : reviewList) {
            if (review.getReviewId().equals(reviewId)) {
                return review;
            }
        }
        return null;
    }

    @Override
    public Review update(Review review) {

        Helper.requireNonNull(review, "review");

        Review existing = read(review.getReviewId());

        if (existing != null) {
            reviewList.remove(existing);
            reviewList.add(review);
            return review;
        }

        return null;
    }

    @Override
    public boolean delete(Long reviewId) {

        Helper.requireNonNull(reviewId, "reviewId");

        Review review = read(reviewId);

        if (review != null) {
            reviewList.remove(review);
            return true;
        }

        return false;
    }

    @Override
    public List<Review> getAll() {
        return reviewList;
    }
}
