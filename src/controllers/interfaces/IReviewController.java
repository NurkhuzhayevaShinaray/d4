package controllers.interfaces;

public interface IReviewController {
    String addReview(int userId, String comment, int rating);
    String getReviewsByUser(int userId);
}
