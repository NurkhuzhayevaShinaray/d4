import controllers.UserController;
import controllers.BookController;
import controllers.interfaces.IUserController;
import controllers.interfaces.IBookController;
import repositories.UserRepository;
import repositories.BookRepository;
import repositories.interfaces.IUserRepository;
import repositories.interfaces.IBookRepository;
import data.PostgresDB;
import data.interfaces.IDB;

public class Main {
    public static void main(String[] args) {
        String url = "jdbc:postgresql://localhost:5432/bookstore";
        String user = "postgres";
        String password = "Shin127ay";
        IDB db = new PostgresDB(url, user, password);

        IUserRepository userRepo = new UserRepository(db);
        IBookRepository bookRepo = new BookRepository(db);

        IUserController userController = new UserController(userRepo);
        IBookController bookController = new BookController(bookRepo);

        MyApplication userApp = new MyApplication(userController);
        BookApplication bookApp = new BookApplication(bookController);

        userApp.start();
        bookApp.start();
    }
}

