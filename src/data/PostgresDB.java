package data;

import data.interfaces.IDB;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PostgresDB implements IDB {
    private String host;
    private String username;
    private String password;
    private String dbName;

    private Connection connection;

    public PostgresDB(String host, String username, String password, String dbName) {
        setHost(host);
        setUsername(username);
        setPassword(password);
        setDbName(dbName);
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    @Override
    public Connection getConnection() {
        String connectionUrl = "jdbc:postgresql://" + host + "/" + dbName;
        try {
            Class.forName("org.postgresql.Driver");

            if (connection != null && !connection.isClosed()) {
                return connection;
            }

            connection = DriverManager.getConnection(connectionUrl, username, password);
            return connection;
        } catch (Exception e) {
            System.out.println("Failed to connect to database: " + e.getMessage());
        }
        return null;
    }

    @Override
    public void close() {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Connection closed successfully.");
            } catch (SQLException e) {
                System.out.println("Failed to close connection: " + e.getMessage());
            }
        }
    }
}
