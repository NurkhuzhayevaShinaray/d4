package controllers;

public interface IUserController {
    String createUser(String name, String surname, String gender);
    String getUserById(int id);
    String getAllUsers();

}