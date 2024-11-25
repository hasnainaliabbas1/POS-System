package model;

import java.sql.*;
import java.util.ArrayList;

public class ManagerModel {
    private static Connection connection;

    // Constructor to initialize the database connection
    public ManagerModel() {
        connection = DatabaseManager.getConnection();
    }

    // Save a new manager to the database
    public boolean saveManager(String branchCode, String name, String email, String address, double salary, int experience, String remarks) {
        String sql = "INSERT INTO managers (branch_code, name, email, address, salary, experience, remarks, password) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, branchCode);
            stmt.setString(2, name);
            stmt.setString(3, email);
            stmt.setString(4, address);
            stmt.setDouble(5, salary);
            stmt.setInt(6, experience);
            stmt.setString(7, remarks);
            stmt.setString(8, "123"); // You may consider hashing the password for security
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Error saving manager: " + e.getMessage());
            e.printStackTrace();  // Print stack trace for better debugging
            return false;
        }
    }

    // Update an existing manager's details
    public boolean updateManager(int managerId, String name, String email, double salary, int experience, String address, String remarks) {
        String sql = "UPDATE managers SET name = ?, email = ?, address = ?, salary = ?, experience = ?, remarks = ? WHERE manager_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, name);
            stmt.setString(2, email);
            stmt.setString(3, address);
            stmt.setDouble(4, salary);
            stmt.setInt(5, experience);
            stmt.setString(6, remarks);
            stmt.setInt(7, managerId);
            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            System.out.println("Error updating manager: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    // Delete a manager by ID
    public boolean deleteManager(int managerId) {
        String sql = "DELETE FROM managers WHERE manager_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, managerId);
            int rowsDeleted = stmt.executeUpdate();
            return rowsDeleted > 0;
        } catch (SQLException e) {
            System.out.println("Error deleting manager: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    // Fetch all managers
    public ArrayList<ManagerDetails> getManagerData() {
        ArrayList<ManagerDetails> managers = new ArrayList<>();
        String sql = "SELECT * FROM managers";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                managers.add(new ManagerDetails(
                        resultSet.getInt("manager_id"),
                        resultSet.getString("branch_code"),
                        resultSet.getString("name"),
                        resultSet.getString("email"),
                        resultSet.getString("address"),
                        resultSet.getDouble("salary"),
                        resultSet.getInt("experience"),
                        resultSet.getString("remarks")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Error fetching manager data: " + e.getMessage());
            e.printStackTrace();
        }
        return managers;
    }

    // Validate manager credentials
    public boolean validateManager(String username, String password) {
        String sql = "SELECT * FROM managers WHERE name = ? AND password = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet resultSet = stmt.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            System.out.println("Error validating manager: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    // Update manager password
    public boolean updatePassword(String username, String newPassword) {
        String sql = "UPDATE managers SET password = ? WHERE name = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, newPassword);
            stmt.setString(2, username);
            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            System.out.println("Error updating password: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    // Check if manager exists by username
    public boolean isManagerExists(String username) {
        String sql = "SELECT * FROM managers WHERE name = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, username);
            ResultSet resultSet = stmt.executeQuery();
            return resultSet.next();  // If the result set has data, the manager exists
        } catch (SQLException e) {
            System.out.println("Error checking if manager exists: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    // Inner class to hold manager details
    public static class ManagerDetails {
        private int managerId;
        private String branchCode;
        private String name;
        private String email;
        private String address;
        private double salary;
        private int experience;
        private String remarks;
        private String branchName;

        public ManagerDetails(int managerId, String branchCode, String name, String email, String address,
                              double salary, int experience, String remarks) {
            this.managerId = managerId;
            this.branchCode = branchCode;
            this.name = name;
            this.email = email;
            this.address = address;
            this.salary = salary;
            this.experience = experience;
            this.remarks = remarks;
        }

        public int getManagerId() {
            return managerId;
        }

        public String getBranchCode() {
            return branchCode;
        }

        public String getName() {
            return name;
        }

        public String getEmail() {
            return email;
        }

        public String getAddress() {
            return address;
        }

        public double getSalary() {
            return salary;
        }

        public int getExperience() {
            return experience;
        }

        public String getRemarks() {
            return remarks;
        }
    }
}
