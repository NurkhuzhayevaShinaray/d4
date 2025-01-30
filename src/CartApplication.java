import controllers.CartController;
import controllers.interfaces.ICartController;
import models.Book;
import models.Cart;
import models.User;
import repositories.CartRepository;
import data.PostgresDB;
import data.interfaces.IDB;

import java.util.Scanner;

public class CartApplication {
    private final ICartController cartController;
    private final Scanner scanner;

    public CartApplication(ICartController cartController) {
        this.cartController = cartController;
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        while (true) {
            System.out.println("Welcome to the Cart Application!");
            System.out.println("Choose an option:");
            System.out.println("1. Add book to cart");
            System.out.println("2. Remove book from cart");
            System.out.println("3. View cart");
            System.out.println("4. View total cost");
            System.out.println("5. Clear cart");
            System.out.println("6. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addBookToCart();
                    break;
                case 2:
                    removeBookFromCart();
                    break;
                case 3:
                    viewCart();
                    break;
                case 4:
                    viewTotalCost();
                    break;
                case 5:
                    clearCart();
                    break;
                case 6:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private void addBookToCart() {
        System.out.println("Enter book ID to add to cart:");
        int bookId = scanner.nextInt();
        scanner.nextLine();

        int userId = bookId;


        Book book = getBookById(bookId);

        if (book != null) {
            cartController.addBookToCart(book, userId);
            System.out.println("Book added to cart!");
        } else {
            System.out.println("Book not found!");
        }
    }







    private void removeBookFromCart() {
        System.out.println("Enter book ID to remove from cart:");
        int bookId = scanner.nextInt();
        scanner.nextLine();  // consume newline
        Book book = getBookById(bookId);

        if (book != null) {
            cartController.removeBookFromCart(book);
            System.out.println("Book removed from cart!");
        } else {
            System.out.println("Book not found!");
        }
    }

    private void viewCart() {
        Cart cart = cartController.getCart();
        if (cart.getCartBooks().isEmpty()) {
            System.out.println("Your cart is empty!");
            return;
        }

        System.out.println("Your Cart:");
        for (int i = 0; i < cart.getCartBooks().size(); i++) {
            Book book = cart.getCartBooks().get(i);
            int quantity = cart.getCartQuantities().get(i);
            double price = cart.getCartSum().get(i);
            System.out.println("Book: " + book.getTitle() + ", Quantity: " + quantity + ", Total: " + (quantity * book.getPrice()));
        }
    }


    private void viewTotalCost() {
        double totalCost = cartController.getTotalCost();
        System.out.println("Total Cost: " + totalCost);
    }

    private void clearCart() {
        cartController.clearCart();
        System.out.println("Cart cleared!");
    }


    public Book getBookById(int bookId) {
        // Simulate a method to fetch book by ID (this should be replaced with actual data fetching)
        // Example hardcoded book data
        if (bookId == 1) {
            return new Book("Book 1", "Author 1", "Genre 1", 10.0);
        } else if (bookId == 2) {
            return new Book("Book 2", "Author 2", "Genre 2", 15.0);
        } else if (bookId == 3) {
            return new Book("Book 3", "Author 3", "Genre 3", 20.0);
        }
        return null; // Return null if the book ID doesn't match any of the hardcoded values
    }
}
