package repositories;

import data.interfaces.IDB;
import models.User;
import repositories.interfaces.IUserRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepository implements IUserRepository {
    private final IDB db;

    public UserRepository(IDB db) {
        this.db = db;
    }

    @Override
    public boolean createUser(User user) {
        Connection connection = null;
        try {
            connection = db.getConnection();
            String sql ="INSERT INTO users(name, surname, gender) VALUES (?, ?, ?)";
            PreparedStatement st = connection.prepareStatement(sql);

            st.setString(1, user.getName());
            st.setString(2, user.getSurname());
            st.setBoolean(3, user.getGender());

            st.execute();

            return true;
        } catch (SQLException e){
            System.out.println("sql error:" + e.getMessage());
        }
        return false;
    }

    @Override
    public User getUserById(int id) {
        Connection connection = null;
        try {
            connection = db.getConnection();
            String sql ="SELECT * FROM users WHERE id = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);

            ResultSet rs = st.executeQuery();
            if (rs.next()){
                return new User(rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("surname"),
                        rs.getBoolean("gender"));
            }
        }catch (SQLException e){
            System.out.println("sql error:" + e.getMessage());
        }
        return null;
    }

    @Override
    public List<User> getAllUsers() {
        Connection connection = null;
        try{
            connection = db.getConnection();
            String sql ="SELECT id, name, surname, gender FROM users";
            Statement st = connection.createStatement();

            ResultSet rs = st.executeQuery(sql);
            List<User> users = new ArrayList<>();
            while(rs.next()){
                User user = new User(rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("surname"),
                        rs.getBoolean("gender"));
                users.add(user);
            }
            return users;
        }catch (SQLException e){
            System.out.println("sql error:" + e.getMessage());
        }
        return null;
    }
}