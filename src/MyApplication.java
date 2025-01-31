package application;

import controllers.UserController;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MyApplication {
    private final UserController userController;
    private final Scanner scanner = new Scanner(System.in);

    public MyApplication(UserController userController) {
        this.userController = userController;
    }

    private void mainMenu() {
        System.out.println();
        System.out.println("Welcome to User Management");
        System.out.println("Select one of the following options:");
        System.out.println("1. Get all users");
        System.out.println("2. Get user by id");
        System.out.println("3. Create new user");
        System.out.println("0. Exit");
        System.out.println();
        System.out.print("Select an option (0-3): ");
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
                    case 0 -> {
                        System.out.println("Exiting User Management...");
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
}
