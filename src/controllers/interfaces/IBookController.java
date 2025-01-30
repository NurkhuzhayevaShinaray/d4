package controllers.interfaces;

public interface IBookController {
    String createBook(String title, String author, String genre, double price);
    String getBookById(int id);
    String getAllBooks();
}


