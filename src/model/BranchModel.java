package model;

import java.sql.*;
import java.util.ArrayList;

public class BranchModel {
    private static Connection connection;

    public BranchModel() {
        this.connection = DatabaseManager.getConnection();
    }

    public boolean saveBranch(String branchCode, String branchName, String city, boolean isActive, String address, String phone) {
        String sql = "INSERT INTO branches (branch_code, branch_name, city, is_active, address, phone) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, branchCode);
            stmt.setString(2, branchName);
            stmt.setString(3, city);
            stmt.setBoolean(4, isActive);
            stmt.setString(5, address);
            stmt.setString(6, phone);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Error inserting branch: " + e.getMessage());
            return false;
        }
    }

    public static ArrayList<String> getBranchNamesFromDatabase() {
        ArrayList<String> branchNames = new ArrayList<>();

        try (Connection conn = DatabaseManager.getConnection();
             Statement statement = conn.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT branch_name FROM branches")) {

            while (resultSet.next()) {
                branchNames.add(resultSet.getString("branch_name"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return branchNames;
    }

    public static ArrayList<String> getActiveBranchNamesFromDatabase() {
        ArrayList<String> activeBranchNames = new ArrayList<>();

        String query = "SELECT branch_name FROM branches WHERE is_active = 1";
        try (Connection conn = DatabaseManager.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                activeBranchNames.add(rs.getString("branch_name"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return activeBranchNames;
    }


    // BranchDetails class to hold branch information
    public static class BranchDetails {
        private String branchName;
        private String city;
        private String address;
        private String phone;
        private boolean isActive;
        private String branchCode;

        public BranchDetails(String branchName, String city, String address, String phone, boolean isActive,String branchCode) {
            this.branchName = branchName;
            this.city = city;
            this.address = address;
            this.phone = phone;
            this.isActive = isActive;
            this.branchCode = branchCode;
        }

        public String getBranchName() { return branchName; }
        public String getCity() { return city; }
        public String getAddress() { return address; }
        public String getPhone() { return phone; }
        public boolean isActive() { return isActive; }
        public String getBranchCode(){return branchCode ;}
    }

    // Fetch branch details by branch name
    public static BranchDetails getBranchDetailsByName(String branchName) {
        String sql = "SELECT * FROM branches WHERE branch_name = ?";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, branchName);
            ResultSet resultSet = stmt.executeQuery();

            if (resultSet.next()) {
                String city = resultSet.getString("city");
                String address = resultSet.getString("address");
                String phone = resultSet.getString("phone");
                boolean isActive = resultSet.getBoolean("is_active");

                return new BranchDetails(branchName, city, address, phone, isActive, resultSet.getString("branch_code"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Update branch details
    public static boolean updateBranch(String branchName, String city, String address, String phone, boolean isActive) {
        String sql = "UPDATE branches SET city = ?, address = ?, phone = ?, is_active = ? WHERE branch_name = ?";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, city);
            stmt.setString(2, address);
            stmt.setString(3, phone);
            stmt.setBoolean(4, isActive);
            stmt.setString(5, branchName);

            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
