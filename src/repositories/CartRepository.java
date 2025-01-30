package repositories;

import data.interfaces.IDB;
import models.Cart;
import models.Book;
import repositories.interfaces.ICartRepository;

import java.sql.*;

public class CartRepository implements ICartRepository {

    private final IDB idb;

    public CartRepository(IDB idb) {
        this.idb = idb;
    }

    @Override
    public void saveCart(Cart cart, int userId) {
        String query = "INSERT INTO carts (book_id, user_id, quantity, price) VALUES (?, ?, ?, ?)";

        try (Connection connection = idb.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            for (int i = 0; i < cart.getCartBooks().size(); i++) {
                Book book = cart.getCartBooks().get(i);
                int quantity = cart.getCartQuantities().get(i);
                double price = cart.getCartSum().get(i);

                statement.setInt(1, userId);
                statement.setInt(2, userId);  // Передаем userId
                statement.setInt(3, quantity);
                statement.setDouble(4, price);

                statement.addBatch();
            }

            statement.executeBatch();
            System.out.println("Cart saved successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    public Cart getCartByUserId(int userId) {
        Cart cart = new Cart();

        String query = "SELECT c.book_id, c.price, b.title, b.author FROM carts c " +
                "JOIN books b ON c.book_id = b.id WHERE c.user_id = ?";

        try (Connection connection = idb.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int bookId = resultSet.getInt("book_id");
                Double price = resultSet.getDouble("price");
                String title = resultSet.getString("title");
                String author = resultSet.getString("author");

                Book book = new Book();
                book.setId(bookId);
                book.setTitle(title);
                book.setAuthor(author);

                cart.getCartBooks().add(book);
                cart.getCartSum().add(price);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cart;
    }

    public void updateCart(Cart cart, int userId) {
        String deleteQuery = "DELETE FROM carts WHERE user_id = ?";
        String insertQuery = "INSERT INTO carts (user_id, book_id, price) VALUES (?, ?, ?)";

        try (Connection connection = idb.getConnection()) {
            try (PreparedStatement deleteStatement = connection.prepareStatement(deleteQuery)) {
                deleteStatement.setInt(1, userId);
                deleteStatement.executeUpdate();
            }

            try (PreparedStatement insertStatement = connection.prepareStatement(insertQuery)) {
                for (int i = 0; i < cart.getCartBooks().size(); i++) {
                    Book book = cart.getCartBooks().get(i);
                    double price = cart.getCartSum().get(i);

                    insertStatement.setInt(1, userId);
                    insertStatement.setInt(2, book.getId());
                    insertStatement.setDouble(3, price);

                    insertStatement.addBatch();
                }
                insertStatement.executeBatch();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public double getTotalCost(Cart cart) {
        double totalCost = 0;
        for (double price : cart.getCartSum()) {
            totalCost += price;
        }
        return totalCost;
    }

    public void deleteCart(int userId) {
        String query = "DELETE FROM carts WHERE user_id = ?";

        try (Connection connection = idb.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, userId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

