package models;

public class Review {
    private int id;
    private int userId;
    private String comment;
    private int rating;

    public Review(int userId, String comment, int rating) {
        this.userId = userId;
        this.comment = comment;
        this.rating = rating;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getUserId() { return userId; }
    public String getComment() { return comment; }
    public int getRating() { return rating; }
}