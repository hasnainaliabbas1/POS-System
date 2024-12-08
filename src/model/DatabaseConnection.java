package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/new";
    private static final String USER = "root";
    private static final String PASSWORD = "";
    private static Connection connection;

    // Private constructor to prevent instantiation
    private DatabaseConnection() {}

    // Thread-safe singleton pattern for obtaining the connection
    public static synchronized Connection getConnection() {
        try {

            if (connection == null || connection.isClosed()) {
                System.out.println("Connecting to the database...");
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
                createTablesIfNotExist();
                System.out.println("Database connected successfully.");
            }
        } catch (SQLException e) {
            System.err.println("Failed to connect to the database.");
            e.printStackTrace();
        }
        return connection;
    }

    private static void createTablesIfNotExist() {
        String userTable = "CREATE TABLE IF NOT EXISTS dataentrytable (" +
                "id INT AUTO_INCREMENT PRIMARY KEY, " +
                "name VARCHAR(100), " +
                "emp_no VARCHAR(50), " +
                "name VARCHAR(100), " +
                "password VARCHAR(255), " +
                "branch_code VARCHAR(50), " +
                "salary DOUBLE);";

        String vendorTable = "CREATE TABLE IF NOT EXISTS vendors (" +
                "id INT AUTO_INCREMENT PRIMARY KEY, " +
                "name VARCHAR(100), " +
                "contact_info VARCHAR(100), " +
                "address VARCHAR(255));";

        String productTable = "CREATE TABLE IF NOT EXISTS products (" +
                "id INT AUTO_INCREMENT PRIMARY KEY, " +
                "name VARCHAR(100), " +
                "category VARCHAR(100), " +
                "original_price DOUBLE, " +
                "sale_price DOUBLE, " +
                "price_per_unit DOUBLE, " +
                "price_per_carton DOUBLE, " +
                "vendor_id INT, " +
                "FOREIGN KEY (vendor_id) REFERENCES vendors(id));";

        try (Statement stmt = connection.createStatement()) {
            stmt.execute(userTable);
            stmt.execute(vendorTable);
            stmt.execute(productTable);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
