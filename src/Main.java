import controllers.UserController;
import controllers.ReviewController;
import data.PostgresDB;
import data.interfaces.IDB;
import repositories.UserRepository;
import repositories.ReviewRepository;
import repositories.interfaces.IUserRepository;

import application.MyApplication;

public class Main {
    public static void main(String[] args) {
        IDB db = new PostgresDB("jdbc:postgresql://localhost:5432/", "postgres", "Shin127ay");
        IUserRepository userRepo = new UserRepository(db);
        UserController userController = new UserController(userRepo);

        ReviewRepository reviewRepo = new ReviewRepository(db);
        ReviewController reviewController = new ReviewController(reviewRepo);

        MyApplication app = new MyApplication(userController, reviewController);
        app.start();

        db.close();
    }
}
