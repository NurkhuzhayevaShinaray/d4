package controllers;

import controllers.interfaces.IUserController;
import models.User;
import repositories.interfaces.IUserRepository;

import java.util.List;

public class UserController implements IUserController {
    private final IUserRepository repo;

    public UserController(IUserRepository repo) {
        this.repo = repo;
    }
    @Override
    public String createUser(String name, String surname, String gender) {
        boolean male = gender.equalsIgnoreCase("male");
        User user = new User(name, surname, male);
        boolean created = repo.createUser(user);
        return (created) ? "User was created" : "User creation was failed";
    }

    @Override
    public String getUserById(int id) {
        User user = repo.getUserById(id);
        return (user == null) ? "User was not found" : user.toString();
    }

    @Override
    public String getAllUsers() {
        List<User> users = repo.getAllUsers();
        StringBuilder responce = new StringBuilder();
        for (User user : users) {
            responce.append(user.toString()).append("\n");
        }
        return responce.toString();
    }
}