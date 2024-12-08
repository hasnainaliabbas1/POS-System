package controller;

import util.OfflineFileHandler;
import util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class OfflineSyncController {

    /**
     * Checks and syncs offline data stored in the offline file to the database.
     */
    public void checkAndSyncData() {
        String offlineData = OfflineFileHandler.readOfflineData();
        if (!offlineData.isEmpty()) {
            System.out.println("Syncing offline data...");

            // Split the offline data into lines
            String[] dataLines = offlineData.split("\n");

            // Connect to the database
            try (Connection connection = DatabaseConnection.getConnection()) {
                for (String dataLine : dataLines) {
                    if (dataLine.trim().isEmpty()) {
                        continue; // Skip empty lines
                    }

                    // Parse and sync data based on your application's format
                    syncDataToDatabase(connection, dataLine);
                }

                // Clear offline data file after successful sync
                OfflineFileHandler.clearOfflineData();
                System.out.println("Offline data synced successfully.");
            } catch (SQLException e) {
                System.err.println("Error while syncing data to the database: " + e.getMessage());
            }
        } else {
            System.out.println("No offline data to sync.");
        }
    }

    /**
     * Syncs a single line of data to the database based on the data type.
     * Modify this method to match your application's data structure.
     *
     * @param connection The database connection.
     * @param dataLine   The line of data to sync.
     */
    private void syncDataToDatabase(Connection connection, String dataLine) {
        // Split the line into fields, with the first field indicating the data type
        String[] fields = dataLine.split("\\|");
        if (fields.length == 0) {
            System.err.println("Invalid data format: " + dataLine);
            return;
        }

        String dataType = fields[0].trim();
        switch (dataType) {
            case "User":
                syncUserData(connection, fields);
                break;
            case "Vendor":
                syncVendorData(connection, fields);
                break;
            case "Product":
                syncProductData(connection, fields);
                break;
            default:
                System.err.println("Unknown data type: " + dataType);
        }
    }

    /**
     * Syncs user data to the database.
     *
     * @param connection The database connection.
     * @param fields     The data fields.
     */
    private void syncUserData(Connection connection, String[] fields) {
        if (fields.length < 6) {
            System.err.println("Invalid User data format: " + String.join("|", fields));
            return;
        }

        String insertQuery = "INSERT INTO users (name, emp_no, email, password, branch_code, salary) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
            preparedStatement.setString(1, fields[1].trim()); // Name
            preparedStatement.setString(2, fields[2].trim()); // Emp No
            preparedStatement.setString(3, fields[3].trim()); // Email
            preparedStatement.setString(4, fields[4].trim()); // Password
            preparedStatement.setString(5, fields[5].trim()); // Branch Code
            preparedStatement.setDouble(6, Double.parseDouble(fields[6].trim())); // Salary

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error while inserting User data: " + e.getMessage());
        }
    }

    /**
     * Syncs vendor data to the database.
     *
     * @param connection The database connection.
     * @param fields     The data fields.
     */
    private void syncVendorData(Connection connection, String[] fields) {
        if (fields.length < 4) {
            System.err.println("Invalid Vendor data format: " + String.join("|", fields));
            return;
        }

        String insertQuery = "INSERT INTO vendors (name, contact_info, address) VALUES (?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
            preparedStatement.setString(1, fields[1].trim()); // Vendor Name
            preparedStatement.setString(2, fields[2].trim()); // Contact Info
            preparedStatement.setString(3, fields[3].trim()); // Address

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error while inserting Vendor data: " + e.getMessage());
        }
    }

    /**
     * Syncs product data to the database.
     *
     * @param connection The database connection.
     * @param fields     The data fields.
     */
    private void syncProductData(Connection connection, String[] fields) {
        if (fields.length < 8) {
            System.err.println("Invalid Product data format: " + String.join("|", fields));
            return;
        }

        String insertQuery = "INSERT INTO products (name, category, original_price, sale_price, price_per_unit, price_per_carton, vendor_id) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
            preparedStatement.setString(1, fields[1].trim()); // Product Name
            preparedStatement.setString(2, fields[2].trim()); // Category
            preparedStatement.setDouble(3, Double.parseDouble(fields[3].trim())); // Original Price
            preparedStatement.setDouble(4, Double.parseDouble(fields[4].trim())); // Sale Price
            preparedStatement.setDouble(5, Double.parseDouble(fields[5].trim())); // Price Per Unit
            preparedStatement.setDouble(6, Double.parseDouble(fields[6].trim())); // Price Per Carton
            preparedStatement.setInt(7, Integer.parseInt(fields[7].trim())); // Vendor ID

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error while inserting Product data: " + e.getMessage());
        }
    }
}
