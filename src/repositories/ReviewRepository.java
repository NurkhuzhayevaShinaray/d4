package repositories;

import data.interfaces.IDB;
import models.Review;
import repositories.interfaces.IReviewRepository;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReviewRepository implements IReviewRepository {
    private final IDB db;

    public ReviewRepository(IDB db) { this.db = db; }

    @Override
    public boolean addReview(Review review) {
        try (Connection con = db.getConnection();
             PreparedStatement stmt = con.prepareStatement("INSERT INTO reviews (user_id, comment, rating) VALUES (?, ?, ?)", Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, review.getUserId());
            stmt.setString(2, review.getComment());
            stmt.setInt(3, review.getRating());
            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Review> getReviewsByUser(int userId) {
        List<Review> reviews = new ArrayList<>();
        try (Connection con = db.getConnection();
             PreparedStatement stmt = con.prepareStatement("SELECT * FROM reviews WHERE user_id = ?")) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                reviews.add(new Review(userId, rs.getString("comment"), rs.getInt("rating")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reviews;
    }
}
