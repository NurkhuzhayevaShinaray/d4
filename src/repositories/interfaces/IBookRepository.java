package repositories.interfaces;

import models.Book;

import java.util.List;

public interface IBookRepository {
    boolean createBook(Book book);
    Book getBookById(int id);
    List<Book> getAllBooks();
}