package application;

import controllers.ReviewController;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ReviewApplication {
    private final ReviewController reviewController;
    private final Scanner scanner = new Scanner(System.in);

    public ReviewApplication(ReviewController reviewController) {
        this.reviewController = reviewController;
    }

    private void mainMenu() {
        System.out.println();
        System.out.println("Welcome to Review System");
        System.out.println("Select one of the following options:");
        System.out.println("1. Add review");
        System.out.println("2. Get reviews by user");
        System.out.println("0. Exit");
        System.out.println();
        System.out.print("Select an option (0-2): ");
    }

    public void start() {
        while (true) {
            mainMenu();
            try {
                int option = scanner.nextInt();
                scanner.nextLine();
                switch (option) {
                    case 1 -> addReviewMenu();
                    case 2 -> getReviewsByUserMenu();
                    case 0 -> {
                        System.out.println("Exiting Review System...");
                        return;
                    }
                    default -> System.out.println("Invalid option. Please try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Please enter a valid option! " + e);
                scanner.nextLine(); // Ignore invalid input
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
            System.out.println("----------------------------------------");
        }
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
