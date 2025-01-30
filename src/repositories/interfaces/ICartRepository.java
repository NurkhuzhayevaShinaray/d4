package repositories.interfaces;

import models.Cart;

public interface ICartRepository {

    // Method to save a cart to the database
    void saveCart(Cart cart, int id);

    // Method to retrieve a cart by user ID
    Cart getCartByUserId(int userId);

    // Method to update a cart (e.g., modifying items or prices)
    void updateCart(Cart cart, int userId);

    // Method to delete a cart by user ID
    void deleteCart(int userId);

    public double getTotalCost(Cart cart);

}


