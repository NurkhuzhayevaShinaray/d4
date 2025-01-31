package repositories;

import data.interfaces.IDB;
import models.Payment;
import repositories.interfaces.IPaymentRepository;

import java.util.ArrayList;
import java.util.List;

public class PaymentRepository implements IPaymentRepository {
    private List<Payment> payments;
    private int idCounter;

    public PaymentRepository() {
        payments = new ArrayList<>();
        idCounter = 1;

    }

    public PaymentRepository(IDB db) {
    }

    @Override
    public boolean createPayment(Payment payment) {
        payment.setId(idCounter++);
        return payments.add(payment);
    }

    @Override
    public Payment getPaymentById(int id) {
        return payments.stream()
                .filter(payment -> payment.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Payment> getAllPayments() {
        return new ArrayList<>(payments);
    }

    @Override
    public boolean updatePaymentStatus(int id, String status) {
        Payment payment = getPaymentById(id);
        if (payment != null) {
            payment.setStatus(status);
            return true;
        }
        return false;
    }
}

