package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/fittnessclub";
    private static final String USER = "root";
    private static final String PASSWORD = "LabaPassword";

    public static Connection getConnection() {
        try {
            // Load and Register MySQL Connector/J Driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Establish and Return the Connection
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}
