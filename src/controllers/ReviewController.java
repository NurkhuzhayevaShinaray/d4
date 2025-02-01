package controllers;

import models.Review;
import repositories.interfaces.IReviewRepository;
import java.util.List;

public class ReviewController implements controllers.interfaces.IReviewController {
    private final IReviewRepository repo;

    public ReviewController(IReviewRepository repo) {
        this.repo = repo;
    }

    @Override
    public String addReview(int userId, String comment, int rating) {
        Review review = new Review(userId, comment, rating);
        boolean created = repo.addReview(review);
        return (created) ? "Review added successfully" : "Failed to add review";
    }

    @Override
    public String getReviewById(int id) {
        Review review = repo.getReviewById(id);
        return (review == null) ? "Review not found" : review.toString();
    }

    @Override
    public String getAllReviews() {
        List<Review> reviews = repo.getAllReviews();
        StringBuilder response = new StringBuilder();
        for (Review review : reviews) {
            response.append(review.toString()).append("\n");
        }
        return response.toString();
    }

    @Override
    public String updateReviewStatus(int id, String status) {
        boolean updated = repo.updateReviewStatus(id, status);
        return (updated) ? "Review status updated" : "Failed to update review status";
    }
}
