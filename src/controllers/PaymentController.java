package controllers;

import models.Payment;
import repositories.interfaces.IPaymentRepository;
import java.util.List;

public abstract class PaymentController implements controllers.IPaymentController {
    private final IPaymentRepository repo;

    public PaymentController(IPaymentRepository repo) {
        this.repo = repo;
    }

    @Override
    public String createPayment(int userId, double amount) {
        Payment payment = new Payment(userId, amount);
        boolean created = repo.createPayment(payment);
        return (created) ? "Payment was created" : "Payment creation failed";
    }

    @Override
    public String getPaymentById(int id) {
        Payment payment = repo.getPaymentById(id);
        return (payment == null) ? "Payment was not found" : payment.toString();
    }

    @Override
    public String getAllPayments() {
        List<Payment> payments = repo.getAllPayments();
        StringBuilder response = new StringBuilder();
        for (Payment payment : payments) {
            response.append(payment.toString()).append("\n");
        }
        return response.toString();
    }

    @Override
    public String updatePaymentStatus(int id, String status) {
        boolean updated = repo.updatePaymentStatus(id, status);
        return (updated) ? "Payment status updated" : "Payment status update failed";
    }
}
