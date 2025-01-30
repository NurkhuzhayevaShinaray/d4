package models;

public class Book {
    private int id;
    private String title;
    private String author;
    private String genre;
    private double price;

    public Book() {
    }

    public Book(String title, String author, String genre, double price) {
        setTitle(title);
        setAuthor(author);
        setGenre(genre);
        setPrice(price);
    }

    public Book(int id, String title, String author, String genre, double price) {
        this(title, author, genre, price);
        setId(id);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", genre='" + genre + '\'' +
                ", price=" + price +
                '}';
    }
}
