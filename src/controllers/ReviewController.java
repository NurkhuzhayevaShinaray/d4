package controllers;

import models.Review;
import repositories.interfaces.IReviewRepository;
import java.util.List;

public class ReviewController {
    private final IReviewRepository repo;

    public ReviewController(IReviewRepository repo) {
        this.repo = repo; }

    public String addReview(int userId, String comment, int rating) {
        Review review = new Review(userId, comment, rating);
        return repo.addReview(review) ? "Review added successfully" : "Failed to add review";
    }

    public String getReviewsByUser(int userId) {
        List<Review> reviews = repo.getReviewsByUser(userId);
        return reviews.isEmpty() ? "No reviews found" : reviews.toString();
    }
}
