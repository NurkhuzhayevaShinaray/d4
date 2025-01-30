package controllers;

import controllers.interfaces.ICartController;
import models.Book;
import models.Cart;
import models.User;
import repositories.CartRepository;
import repositories.interfaces.ICartRepository;

public class CartController implements ICartController {

    private final ICartRepository cartRepository;  // Репозиторий для работы с корзиной
    private Cart cart;

    // Конструктор, инициализирующий репозиторий и корзину
    public CartController(ICartRepository cartRepository) {
        this.cartRepository = cartRepository;
        this.cart = new Cart(); // Инициализируем пустую корзину
    }

    @Override
    public void addBookToCart(Book book, int userId) {


        System.out.println("Adding book with ID: "+ userId + ", Title: " + book.getTitle());

        int index = cart.getCartBooks().indexOf(book);
        double price = book.getPrice();

        if (index != -1) {
            // Если книга уже в корзине, увеличиваем количество и пересчитываем сумму
            int currentQuantity = cart.getCartQuantities().get(index);
            cart.getCartQuantities().set(index, currentQuantity + 1);

            double currentSum = cart.getCartSum().get(index);
            double newSum = currentSum + price;
            cart.getCartSum().set(index, newSum);
        } else {
            // Если книга еще не в корзине, добавляем её с количеством 1 и суммой
            cart.getCartBooks().add(book);
            cart.getCartQuantities().add(1);
            cart.getCartSum().add(price);
        }

        // Сохраняем корзину в базе данных
        cartRepository.saveCart(cart, userId);
        System.out.println("Cart saved!");
    }





    @Override
    public void removeBookFromCart(Book book) {
        int index = cart.getCartBooks().indexOf(book);
        if (index != -1) {
            // Удаляем книгу и обновляем корзину
            cart.getCartBooks().remove(index);
            cart.getCartQuantities().remove(index);
            cart.getCartSum().remove(index);
        }

        // Можно добавить логику для обновления корзины в базе данных после удаления
        // cartRepository.updateCart(cart, userId);  // Пример обновления корзины
    }

    @Override
    public Cart getCart() {
        return cart; // Возвращаем текущую корзину
    }

    @Override
    public double getTotalCost() {
        // Получаем общую стоимость корзины через репозиторий
        return cartRepository.getTotalCost(cart);  // Метод в репозитории должен возвращать double
    }

    @Override
    public void clearCart() {
        cart.getCartBooks().clear();  // Очищаем список книг
        cart.getCartQuantities().clear();    // Очищаем список количеств
        cart.getCartSum().clear();    // Очищаем список сумм

        // Можно добавить логику для очистки корзины в базе данных
        // cartRepository.clearCart(userId);  // Пример очистки корзины в базе данных
    }

    @Override
    public void updateBookInCart(Book book, int userId) {
        int index = cart.getCartBooks().indexOf(book);
        if (index != -1) {
            // Обновляем количество книги в корзине
            int newQuantity = cart.getCartQuantities().get(index) + 1; // Например, увеличиваем количество на 1
            cart.getCartQuantities().set(index, newQuantity);

            // Пересчитываем сумму для этой книги
            double price = book.getPrice();
            double newSum = price * newQuantity;
            cart.getCartSum().set(index, newSum);

            // Синхронизируем с базой данных
            cartRepository.updateCart(cart, userId);  // Обновляем корзину в базе данных
        }
    }
}

