package controllers;

import controllers.interfaces.IBookController;
import models.Book;
import repositories.interfaces.IBookRepository;

import java.util.List;

public class BookController implements IBookController {
    private final IBookRepository repo;

    public BookController(IBookRepository repo) {
        this.repo = repo;
    }

    @Override
    public String createBook(String title, String author, String genre, double price) {
        Book book = new Book(title, author, genre, price);
        boolean created = repo.createBook(book);
        return (created) ? "Book was created" : "Book creation failed";
    }

    @Override
    public String getBookById(int id) {
        Book book = repo.getBookById(id);
        return (book == null) ? "Book was not found" : book.toString();
    }

    @Override
    public String getAllBooks() {
        List<Book> books = repo.getAllBooks();
        StringBuilder response = new StringBuilder();
        for (Book book : books) {
            response.append(book.toString()).append("\n");
        }
        return response.toString();
    }
}

