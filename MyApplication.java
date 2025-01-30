// Обновленный MyApplication.java с поддержкой отзывов
package application;

import controllers.ReviewController;
import controllers.UserController;
import repositories.ReviewRepository;
import repositories.UserRepository;
import data.PostgresDB;
import data.interfaces.IDB;
import java.util.Scanner;

public class MyApplication {
    private final UserController userController;
    private final ReviewController reviewController;
    private final Scanner scanner = new Scanner(System.in);

    public MyApplication(UserController userController, ReviewController reviewController) {
        this.userController = userController;
        this.reviewController = reviewController;
    }

    public void start() {
        while (true) {
            System.out.println("1. Get all users\n2. Get user by id\n3. Create user\n4. Add review\n5. Get reviews by user\n0. Exit");
            int option = scanner.nextInt();
            scanner.nextLine();
            switch (option) {
                case 1 -> System.out.println(userController.getAllUsers());
                case 2 -> {
                    System.out.print("Enter user ID: ");
                    int id = scanner.nextInt();
                    System.out.println(userController.getUserById(id));
                }
                case 3 -> {
                    System.out.print("Enter name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter email: ");
                    String email = scanner.nextLine();
                    System.out.print("Enter password: ");
                    String password = scanner.nextLine();
                    System.out.println(userController.createUser(name, email, password));
                }
                case 4 -> {
                    System.out.print("Enter user ID: ");
                    int userId = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter comment: ");
                    String comment = scanner.nextLine();
                    System.out.print("Enter rating (1-5): ");
                    int rating = scanner.nextInt();
                    System.out.println(reviewController.addReview(userId, comment, rating));
                }
                case 5 -> {
                    System.out.print("Enter user ID: ");
                    int userId = scanner.nextInt();
                    System.out.println(reviewController.getReviewsByUser(userId));
                }
                case 0 -> {
                    System.out.println("Exiting...");
                    return;
                }
                default -> System.out.println("Invalid option");
            }
        }
    }
}
