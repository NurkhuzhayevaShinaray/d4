package controllers.interfaces;

import models.Book;
import models.Cart;

public interface ICartController {

    void addBookToCart(Book book, int user);

    void removeBookFromCart(Book book);

    Cart getCart();

    double getTotalCost();

    void clearCart();

    void updateBookInCart(Book book, int userId);
}
