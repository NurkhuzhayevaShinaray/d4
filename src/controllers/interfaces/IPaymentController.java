package controllers;

import models.Payment;

public interface IPaymentController {
    String createPayment(int userId, double amount);
    Payment getPayment(int id);

    String getPaymentById(int id);

    String getAllPayments();

    String updatePaymentStatus(int id, String status);
    void showPayments();
}
