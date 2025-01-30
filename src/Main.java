import controllers.BookController;
import controllers.CartController;
import controllers.UserController;
import controllers.ReviewController;
import controllers.interfaces.IBookController;
import controllers.interfaces.ICartController;
import data.PostgresDB;
import data.interfaces.IDB;
import repositories.BookRepository;
import repositories.CartRepository;
import repositories.UserRepository;
import repositories.ReviewRepository;
import repositories.interfaces.IBookRepository;
import repositories.interfaces.ICartRepository;
import repositories.interfaces.IUserRepository;

import application.MyApplication;

public class Main {
    public static void main(String[] args) {
        String url = "jdbc:postgresql://localhost:5432/bookstore";
        String user = "postgres";
        String password = "700235";
        IDB db = new PostgresDB(url, user, password);

        IUserRepository userRepo = new UserRepository(db);
        UserController userController = new UserController(userRepo);

        IBookRepository bookRepo = new BookRepository(db);
        IBookController bookController = new BookController(bookRepo);


        ICartRepository cartRepo = new CartRepository(db);
        ICartController cartController = new CartController(cartRepo);



        ReviewRepository reviewRepo = new ReviewRepository(db);
        ReviewController reviewController = new ReviewController(reviewRepo);

        MyApplication app = new MyApplication(userController, reviewController);
        BookApplication bookApp = new BookApplication(bookController);
        CartApplication cartApplication = new CartApplication(cartController);


        app.start();

        db.close();
    }
}
