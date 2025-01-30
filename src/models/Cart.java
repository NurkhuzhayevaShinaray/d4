package models;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private List<Book> cartBooks;
    private List<Integer> cartQuantities;
    private List<Double> cartSum;  // Список для хранения сумм каждой книги

    public Cart() {
        this.cartBooks = new ArrayList<>();
        this.cartQuantities = new ArrayList<>();
        this.cartSum = new ArrayList<>();  // Инициализация списка для сумм
    }

    public List<Book> getCartBooks() {
        return cartBooks;
    }

    public void setCartBooks(List<Book> cartBooks) {
        this.cartBooks = cartBooks;
    }

    public List<Integer> getCartQuantities() {
        return cartQuantities;
    }

    public void setCartQuantities(List<Integer> cartQuantities) {
        this.cartQuantities = cartQuantities;
    }

    public List<Double> getCartSum() {
        return cartSum;
    }

    public void setCartSum(List<Double> cartSum) {
        this.cartSum = cartSum;
    }
}
