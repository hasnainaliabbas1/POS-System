package model;

import java.sql.*;

public class SuperAdminModel {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/husnain";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "Captainali";

    public boolean validateUser(String username, String password) {
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM users WHERE name = ? AND password = ?")) {
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            return rs.next();  // If result set is not empty, credentials are valid
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
