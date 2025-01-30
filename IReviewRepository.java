package repositories.interfaces;

import models.Review;
import java.util.List;

public interface IReviewRepository {
    boolean addReview(Review review);
    List<Review> getReviewsByUser(int userId);
}