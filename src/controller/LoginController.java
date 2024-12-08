package controller;

import util.DatabaseConnection;
import java.sql.*;

public class LoginController {

    // Authenticate user with email and password
    public boolean authenticate(String email, String password) {
        String query = "SELECT * FROM dataentrytable WHERE name = ? AND password = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, email);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            return rs.next(); // Returns true if user is found
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false; // Returns false if an exception occurs
    }

    // Check if user is logging in for the first time (default password scenario)
    public boolean isFirstLogin(String email) {
        String query = "SELECT password FROM dataentrytable WHERE name = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String currentPassword = rs.getString("password");
                return "Password_123".equals(currentPassword); // Default password check
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false; // Returns false if an exception occurs
    }

    // Change user's password
    public boolean changePassword(String email, String currentPassword, String newPassword) {
        // Validate the current password
        String validateQuery = "SELECT * FROM dataentrytable WHERE name = ? AND password = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement validateStmt = connection.prepareStatement(validateQuery)) {
            validateStmt.setString(1, email);
            validateStmt.setString(2, currentPassword);
            ResultSet rs = validateStmt.executeQuery();

            if (rs.next()) { // Current password is correct
                // Update the password
                String updateQuery = "UPDATE dataentrytable SET password = ? WHERE name = ?";
                try (PreparedStatement updateStmt = connection.prepareStatement(updateQuery)) {
                    updateStmt.setString(1, newPassword);
                    updateStmt.setString(2, email);
                    int rowsAffected = updateStmt.executeUpdate();
                    return rowsAffected > 0; // Returns true if update was successful
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false; // Returns false if validation fails or an exception occurs
    }

    // Retrieve user role for additional logic (if applicable)
    public String getUserRole(String email) {
        String query = "SELECT role FROM dataentrytable WHERE name = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getString("role"); // Returns the role of the user
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // Returns null if an exception occurs or role is not found
    }
}
