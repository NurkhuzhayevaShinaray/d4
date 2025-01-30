import controllers.interfaces.IBookController;

import java.util.InputMismatchException;
import java.util.Scanner;

public class BookApplication {
    private final IBookController controller;
    private final Scanner scanner = new Scanner(System.in);

    public BookApplication(IBookController controller) {
        this.controller = controller;
    }

    private void mainMenu() {
        System.out.println();
        System.out.println("Welcome to the Book Management Application");
        System.out.println("Select one of the following options:");
        System.out.println("1. Get all books");
        System.out.println("2. Get book by id");
        System.out.println("3. Create new book");
        System.out.println("0. Exit");
        System.out.println();
        System.out.print("Select an option (1-3): ");
    }

    public void start() {
        while (true) {
            mainMenu();
            try {
                int option = scanner.nextInt();
                scanner.nextLine();
                switch (option) {
                    case 1: getAllBooksMenu(); break;
                    case 2: getBookByIdMenu(); break;
                    case 3: createBookMenu(); break;
                    default: return;
                }
            } catch (InputMismatchException e) {
                System.out.println("Please enter a valid option!" + e);
                scanner.nextLine(); // to ignore incorrect input
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            System.out.println("----------------------------------------");
        }
    }

    private void createBookMenu() {
        System.out.println("Please enter title: ");
        String title = scanner.nextLine();
        System.out.println("Please enter author: ");
        String author = scanner.nextLine();
        System.out.println("Please enter genre: ");
        String genre = scanner.nextLine();
        System.out.println("Please enter price: ");
        double price = scanner.nextDouble();

        String response = controller.createBook(title, author, genre, price);
        System.out.println(response);
    }

    private void getBookByIdMenu() {
        System.out.println("Please enter a book id: ");
        int id = scanner.nextInt();

        String response = controller.getBookById(id);
        System.out.println(response);
    }

    private void getAllBooksMenu() {
        String response = controller.getAllBooks();
        System.out.println(response);
    }
}
