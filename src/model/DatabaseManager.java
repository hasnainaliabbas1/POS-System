
package model;

import java.sql.*;

public class DatabaseManager {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/husnain";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "Captainali";

    public void initializeDatabase() {
        try (Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String createTableQuery = "CREATE TABLE IF NOT EXISTS users (" +
                    "id INT NOT NULL PRIMARY KEY AUTO_INCREMENT, " +
                    "name VARCHAR(255) UNIQUE, " +
                    "password VARCHAR(255))";
            Statement stmt = con.createStatement();
            stmt.executeUpdate(createTableQuery);

            String insertAdminQuery = "INSERT IGNORE INTO users (name, password) VALUES ('boss', '1234')";
            stmt.executeUpdate(insertAdminQuery);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        } catch (SQLException e) {
            System.out.println("Database connection failed: " + e.getMessage());
            return null;
        }
    }
}
