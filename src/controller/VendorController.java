package controller;






/*
import model.Vendor;
import util.DatabaseConnection;
import java.sql.*;

public class VendorController {
    public void addVendor(Vendor vendor) {
        String query = "INSERT INTO vendors (name, contact_info, address) VALUES (?, ?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, vendor.getName());
            stmt.setString(2, vendor.getContactInfo());
            stmt.setString(3, vendor.getAddress());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}



 */

















import model.Vendor;
import util.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VendorController {

    // Method to add a new vendor to the database
    public void addVendor(Vendor vendor) {
        // SQL query to insert vendor information including company info
        String query = "INSERT INTO vendors (name, contact_info, address, company_name, company_contact_info, company_address) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            // Set values for the vendor's personal info
            stmt.setString(1, vendor.getName());
            stmt.setString(2, vendor.getContactInfo());
            stmt.setString(3, vendor.getAddress());

            // Set values for the company info
            stmt.setString(4, vendor.getCompanyName());
            stmt.setString(5, vendor.getCompanyContactInfo());
            stmt.setString(6, vendor.getCompanyAddress());

            // Execute the insert query
            stmt.executeUpdate();
            System.out.println("Vendor added successfully!");

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error while adding vendor: " + e.getMessage());
        }
    }


    // Method to get all vendors from the database
    public List<Vendor> getAllVendors() {
        List<Vendor> vendorList = new ArrayList<>();
        String query = "SELECT * FROM vendors";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet resultSet = stmt.executeQuery()) {

            while (resultSet.next()) {
                Vendor vendor = new Vendor();

                // Set vendor's personal information
                vendor.setId(resultSet.getInt("id"));
                vendor.setName(resultSet.getString("name"));
                vendor.setContactInfo(resultSet.getString("contact_info"));
                vendor.setAddress(resultSet.getString("address"));

                // Set company information
                vendor.setCompanyName(resultSet.getString("company_name"));
                vendor.setCompanyContactInfo(resultSet.getString("company_contact_info"));
                vendor.setCompanyAddress(resultSet.getString("company_address"));

                // Add vendor to the list
                vendorList.add(vendor);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vendorList;
    }


    // Method to get vendor ID by vendor name
    public int getVendorIdByName(String vendorName) {
        String query = "SELECT id FROM vendors WHERE name = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, vendorName);
            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1; // Return -1 if vendor not found
    }
}

