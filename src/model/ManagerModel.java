package model;

import java.sql.*;
import java.util.ArrayList;

public class ManagerModel {
    private static Connection connection;

    public ManagerModel() {
        connection = DatabaseManager.getConnection();
    }

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
            stmt.setString(8, "123"); // Initial password is set to "123"
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Error saving manager: " + e.getMessage());
            return false;
        }
    }

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
            return false;
        }
    }

    public boolean deleteManager(int managerId) {
        String sql = "DELETE FROM managers WHERE manager_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, managerId);
            int rowsDeleted = stmt.executeUpdate();
            return rowsDeleted > 0;
        } catch (SQLException e) {
            System.out.println("Error deleting manager: " + e.getMessage());
            return false;
        }
    }

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
        }
        return managers;
    }

    public static class ManagerDetails {
        private int managerId;
        private String branchCode;
        private String name;
        private String email;
        private String address;
        private double salary;
        private int experience;
        private String remarks;

        public ManagerDetails(int managerId, String branchCode, String name, String email, String address, double salary, int experience, String remarks) {
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
