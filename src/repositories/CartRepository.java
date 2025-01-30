package repositories;

import data.interfaces.IDB;
import models.Cart;
import models.Book;
import repositories.interfaces.ICartRepository;

import java.sql.*;

public class CartRepository implements ICartRepository {

    private final IDB idb;  // Интерфейс для работы с базой данных

    // Конструктор, принимающий объект IDB для работы с базой данных
    public CartRepository(IDB idb) {
        this.idb = idb;
    }

    // Метод для сохранения корзины в базе данных
    @Override
    public void saveCart(Cart cart, int userId) {  // Добавлен параметр userId
        String query = "INSERT INTO carts (book_id, user_id, quantity, price) VALUES (?, ?, ?, ?)";

        try (Connection connection = idb.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            // Обрабатываем все книги в корзине
            for (int i = 0; i < cart.getCartBooks().size(); i++) {
                Book book = cart.getCartBooks().get(i);
                int quantity = cart.getCartQuantities().get(i);
                double price = cart.getCartSum().get(i);

                // Указываем book_id, user_id, quantity, и price
                statement.setInt(1, userId);
                statement.setInt(2, userId);  // Передаем userId
                statement.setInt(3, quantity);
                statement.setDouble(4, price);

                statement.addBatch();  // Добавляем запрос в batch
            }

            statement.executeBatch();  // Выполняем все запросы в batch
            System.out.println("Cart saved successfully!");
        } catch (SQLException e) {
            e.printStackTrace();  // Для отладки
        }
    }



    // Метод для получения корзины пользователя по его ID
    public Cart getCartByUserId(int userId) {
        Cart cart = new Cart();  // Создаем новую корзину

        String query = "SELECT c.book_id, c.price, b.title, b.author FROM carts c " +
                "JOIN books b ON c.book_id = b.id WHERE c.user_id = ?";

        try (Connection connection = idb.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, userId);  // Устанавливаем ID пользователя в запрос
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int bookId = resultSet.getInt("book_id");
                Double price = resultSet.getDouble("price");
                String title = resultSet.getString("title");
                String author = resultSet.getString("author");

                // Создаем объект книги с дополнительной информацией
                Book book = new Book();
                book.setId(bookId);
                book.setTitle(title);
                book.setAuthor(author);

                // Добавляем книгу и цену в корзину
                cart.getCartBooks().add(book);
                cart.getCartSum().add(price);  // Исправлено на getCartSum().add()
            }
        } catch (SQLException e) {
            e.printStackTrace();  // Для продакшн-окружения использовать логирование
        }

        return cart;
    }

    // Метод для обновления корзины пользователя в базе данных
    public void updateCart(Cart cart, int userId) {
        String deleteQuery = "DELETE FROM carts WHERE user_id = ?";
        String insertQuery = "INSERT INTO carts (user_id, book_id, price) VALUES (?, ?, ?)";

        try (Connection connection = idb.getConnection()) {
            // Сначала удаляем старые данные корзины
            try (PreparedStatement deleteStatement = connection.prepareStatement(deleteQuery)) {
                deleteStatement.setInt(1, userId);
                deleteStatement.executeUpdate();
            }

            // Затем добавляем новые данные корзины
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
            e.printStackTrace();  // Для продакшн-окружения использовать логирование
        }
    }

    // Метод для подсчета общей стоимости корзины
    public double getTotalCost(Cart cart) {
        double totalCost = 0;  // Начинаем с 0
        for (double price : cart.getCartSum()) {
            totalCost += price;  // Складываем все цены в корзине
        }
        return totalCost;
    }

    // Метод для удаления корзины пользователя
    public void deleteCart(int userId) {
        String query = "DELETE FROM carts WHERE user_id = ?";

        try (Connection connection = idb.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, userId);  // Устанавливаем ID пользователя
            statement.executeUpdate();  // Выполняем удаление корзины
        } catch (SQLException e) {
            e.printStackTrace();  // Для продакшн-окружения использовать логирование
        }
    }
}

