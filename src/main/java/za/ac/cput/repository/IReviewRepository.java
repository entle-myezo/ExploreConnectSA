package za.ac.cput.repository;

import java.util.List;
import za.ac.cput.domain.Review;
public interface IReviewRepository {

        Review create(Review review);

        Review read(Long reviewId);

        Review update(Review review);

        boolean delete(Long reviewId);

        List<Review> getAll();
    }

