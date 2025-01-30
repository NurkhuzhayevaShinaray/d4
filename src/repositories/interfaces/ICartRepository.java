package repositories.interfaces;

import models.Cart;

public interface ICartRepository {

    void saveCart(Cart cart, int id);

    Cart getCartByUserId(int userId);

    void updateCart(Cart cart, int userId);

    void deleteCart(int userId);

    public double getTotalCost(Cart cart);

}


