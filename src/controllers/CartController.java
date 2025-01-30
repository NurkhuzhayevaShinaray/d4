package controllers;

import controllers.interfaces.ICartController;
import models.Book;
import models.Cart;
import models.User;
import repositories.CartRepository;
import repositories.interfaces.ICartRepository;

public class CartController implements ICartController {

    private final ICartRepository cartRepository;
    private Cart cart;

    public CartController(ICartRepository cartRepository) {
        this.cartRepository = cartRepository;
        this.cart = new Cart();
    }

    @Override
    public void addBookToCart(Book book, int userId) {


        System.out.println("Adding book with ID: "+ userId + ", Title: " + book.getTitle());

        int index = cart.getCartBooks().indexOf(book);
        double price = book.getPrice();

        if (index != -1) {
            int currentQuantity = cart.getCartQuantities().get(index);
            cart.getCartQuantities().set(index, currentQuantity + 1);

            double currentSum = cart.getCartSum().get(index);
            double newSum = currentSum + price;
            cart.getCartSum().set(index, newSum);
        } else {
            cart.getCartBooks().add(book);
            cart.getCartQuantities().add(1);
            cart.getCartSum().add(price);
        }

        cartRepository.saveCart(cart, userId);
        System.out.println("Cart saved!");
    }





    @Override
    public void removeBookFromCart(Book book) {
        int index = cart.getCartBooks().indexOf(book);
        if (index != -1) {
            cart.getCartBooks().remove(index);
            cart.getCartQuantities().remove(index);
            cart.getCartSum().remove(index);
        }


    }

    @Override
    public Cart getCart() {
        return cart;
    }

    @Override
    public double getTotalCost() {
        return cartRepository.getTotalCost(cart);
    }

    @Override
    public void clearCart() {
        cart.getCartBooks().clear();
        cart.getCartQuantities().clear();
        cart.getCartSum().clear();


    }

    @Override
    public void updateBookInCart(Book book, int userId) {
        int index = cart.getCartBooks().indexOf(book);
        if (index != -1) {
            int newQuantity = cart.getCartQuantities().get(index) + 1;
            cart.getCartQuantities().set(index, newQuantity);

            double price = book.getPrice();
            double newSum = price * newQuantity;
            cart.getCartSum().set(index, newSum);

            cartRepository.updateCart(cart, userId);
        }
    }
}

