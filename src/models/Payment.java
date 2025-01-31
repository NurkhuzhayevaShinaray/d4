package models;

public class Payment {
    private int id;
    private int userId;
    private double amount;
    private String status;


    public Payment() {
    }

    public Payment(int userId, double amount) {
        this.userId = userId;
        this.amount = amount;
        this.status = "Pending";
    }


    public Payment(int id, int userId, double amount, String status) {
        this(userId, amount);  // Calling the second constructor
        this.id = id;
        this.status = status;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }


    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "id=" + id +
                ", userId=" + userId +
                ", amount=" + amount +
                ", status='" + status + '\'' +
                '}';
    }
}
