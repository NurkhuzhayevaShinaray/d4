import controllers.BookController;import controllers.CartController;
import controllers.UserController;import controllers.PaymentController;
import controllers.interfaces.IBookController;import controllers.interfaces.ICartController;
import data.PostgresDB;import data.interfaces.IDB;
import models.Payment;
import repositories.BookRepository;import repositories.CartRepository;
import repositories.UserRepository;import repositories.PaymentRepository;
import repositories.interfaces.IBookRepository;import repositories.interfaces.ICartRepository;
import repositories.interfaces.IUserRepository;import repositories.interfaces.IPaymentRepository;
public class Main {
    public static void main(String[] args) {        String url = "jdbc:postgresql://localhost:5432/bookstore";
        String user = "postgres";        String password = "700235";
        IDB db = new PostgresDB(url, user, password);
        IUserRepository userRepo = new UserRepository(db);        UserController userController = new UserController(userRepo);
        IBookRepository bookRepo = new BookRepository(db);
        IBookController bookController = new BookController(bookRepo);
        ICartRepository cartRepo = new CartRepository(db);        ICartController cartController = new CartController(cartRepo);
        IPaymentRepository paymentRepo = new PaymentRepository(db);
        PaymentController paymentController = new PaymentController(paymentRepo) {
            @Override
            public Payment getPayment(int id) {
                return null;
            }

            @Override
            public void showPayments() {

            }
        };
        application.MyApplication app = new application.MyApplication(userController);        BookApplication bookApp = new BookApplication(bookController);
        CartApplication cartApplication = new CartApplication(cartController);        PaymentApplication paymentApp = new PaymentApplication(paymentController);
        app.start();
        bookApp.start();        cartApplication.start();
        paymentApp.start();
        db.close();    }
}
