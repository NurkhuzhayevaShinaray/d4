package application;

import controllers.IUserController;
import controllers.ReviewController;
import repositories.interfaces.IUserRepository;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MyApplication {
    private final IUserController userController;
    private final ReviewController reviewController;
    private final Scanner scanner = new Scanner(System.in);

    public MyApplication(IUserController userController, ReviewController reviewController) {
        this.userController = userController;
        this.reviewController = reviewController;
    }

    private void mainMenu() {
        System.out.println();
        System.out.println("Welcome to My Application");
        System.out.println("Select one of the following options:");
        System.out.println("1. Get all users");
        System.out.println("2. Get user by id");
        System.out.println("3. Create new user");
        System.out.println("4. Add review");
        System.out.println("5. Get reviews by user");
        System.out.println("0. Exit");
        System.out.println();
        System.out.print("Select an option (0-5): ");
    }

    public void start() {
        while (true) {
            mainMenu();
            try {
                int option = scanner.nextInt();
                scanner.nextLine();
                switch (option) {
                    case 1 -> getAllUsersMenu();
                    case 2 -> getUserByIdMenu();
                    case 3 -> createUserMenu();
                    case 4 -> addReviewMenu();
                    case 5 -> getReviewsByUserMenu();
                    default -> {
                        System.out.println("Exiting...");
                        return;
                    }
                }
            } catch (InputMismatchException e) {
                System.out.println("Please enter a valid option! " + e);
                scanner.nextLine(); // Ignore incorrect input
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
            System.out.println("----------------------------------------");
        }
    }

    private void createUserMenu() {
        System.out.println("Please enter name: ");
        String name = scanner.nextLine();
        System.out.println("Please enter email: ");
        String email = scanner.nextLine();
        System.out.println("Please enter password: ");
        String password = scanner.nextLine();

        String response = userController.createUser(name, email, password);
        System.out.println(response);
    }

    private void getUserByIdMenu() {
        System.out.println("Please enter a user id: ");
        int id = scanner.nextInt();

        String response = userController.getUserById(id);
        System.out.println(response);
    }

    private void getAllUsersMenu() {
        String response = userController.getAllUsers();
        System.out.println(response);
    }

    private void addReviewMenu() {
        System.out.print("Enter user ID: ");
        int userId = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter comment: ");
        String comment = scanner.nextLine();
        System.out.print("Enter rating (1-5): ");
        int rating = scanner.nextInt();

        String response = reviewController.addReview(userId, comment, rating);
        System.out.println(response);
    }

    private void getReviewsByUserMenu() {
        System.out.print("Enter user ID: ");
        int userId = scanner.nextInt();

        String response = reviewController.getReviewsByUser(userId);
        System.out.println(response);
    }
}
