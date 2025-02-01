package models;

public class Review {
    private int id;
    private int userId;
    private String comment;
    private int rating;
    private String status; 

    public Review() {
    }

    public Review(int userId, String comment, int rating) {
        this.userId = userId;
        this.comment = comment;
        this.rating = rating;
        this.status = "Pending"; 
    }

    public Review(int id, int userId, String comment, int rating, String status) {
        this(userId, comment, rating);
        this.id = id;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Review{" +
                "id=" + id +
                ", userId=" + userId +
                ", comment='" + comment + '\'' +
                ", rating=" + rating +
                ", status='" + status + '\'' +
                '}';
    }
}
