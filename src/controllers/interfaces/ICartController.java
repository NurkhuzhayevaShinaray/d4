package controllers.interfaces;

import models.Book;
import models.Cart;

public interface ICartController {

    // Метод для добавления книги в корзину. Цена теперь извлекается из объекта Book.
    void addBookToCart(Book book, int user);

    // Метод для удаления книги из корзины
    void removeBookFromCart(Book book);

    // Метод для получения текущей корзины
    Cart getCart();

    // Метод для вычисления общей стоимости корзины
    double getTotalCost();

    // Метод для очистки корзины
    void clearCart();

    // Метод для обновления книги в корзине (например, обновление количества или цены)
    void updateBookInCart(Book book, int userId);
}
