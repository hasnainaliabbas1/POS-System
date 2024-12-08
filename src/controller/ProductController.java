package controller;


/*
import model.Product;
import util.DatabaseConnection;
import java.sql.*;
import java.util.List;

public class ProductController {
    public void addProduct(Product product) {
        String query = "INSERT INTO products (name, category, original_price, sale_price, price_per_unit, price_per_carton, vendor_id) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, product.getName());
            stmt.setString(2, product.getCategory());
            stmt.setDouble(3, product.getOriginalPrice());
            stmt.setDouble(4, product.getSalePrice());
            stmt.setDouble(5, product.getPricePerUnit());
            stmt.setDouble(6, product.getPricePerCarton());
            stmt.setInt(7, product.getVendorId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}

 */



























import model.Product;
import util.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static util.DatabaseConnection.getConnection;

public class ProductController {

    // Method to add a product to the database
    public void addProduct(Product product) {
        String query = "INSERT INTO products (name, category, original_price, sale_price, price_per_unit, price_per_carton, vendor_id, stock_quantity) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, product.getName());
            stmt.setString(2, product.getCategory());
            stmt.setDouble(3, product.getOriginalPrice());
            stmt.setDouble(4, product.getSalePrice());
            stmt.setDouble(5, product.getPricePerUnit());
            stmt.setDouble(6, product.getPricePerCarton());
            stmt.setInt(7, product.getVendorId());
            stmt.setInt(8, product.getStockQuantity()); // Assuming you have this field in your Product class
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to retrieve all products from the database
    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM products"; // Adjust the SQL query as needed

        try (Connection connection = DatabaseConnection.getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Product product = new Product();
                product.setId(rs.getInt("id"));
                product.setName(rs.getString("name"));
                product.setCategory(rs.getString("category"));
                product.setOriginalPrice(rs.getDouble("original_price"));
                product.setSalePrice(rs.getDouble("sale_price"));
                product.setPricePerUnit(rs.getDouble("price_per_unit"));
                product.setPricePerCarton(rs.getDouble("price_per_carton"));
                product.setVendorId(rs.getInt("vendor_id"));
                product.setStockQuantity(rs.getInt("stock_quantity")); // Assuming you have this field in your Product class
                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }



    public void deleteProduct(int productId) {
        String sql = "DELETE FROM products WHERE id = ?"; // Assuming the table is named 'products'

        try (PreparedStatement statement =  getConnection().prepareStatement(sql)) {
            statement.setInt(1, productId);
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Product deleted successfully.");
            } else {
                System.out.println("Product not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    public void updateProduct(Product product) {
        String sql = "UPDATE products SET name = ?, category = ?, original_price = ?, sale_price = ?, price_per_unit = ?, price_per_carton = ?, vendor_id = ?, stock_quantity = ? WHERE id = ?";

        try (PreparedStatement statement =  getConnection().prepareStatement(sql)) {
            statement.setString(1, product.getName());
            statement.setString(2, product.getCategory());
            statement.setDouble(3, product.getOriginalPrice());
            statement.setDouble(4, product.getSalePrice());
            statement.setDouble(5, product.getPricePerUnit());
            statement.setDouble(6, product.getPricePerCarton());
            statement.setInt(7, product.getVendorId());
            statement.setInt(8, product.getStockQuantity());
            statement.setInt(9, product.getId()); // Set the ID of the product to update

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Product updated successfully.");
            } else {
                System.out.println("Product not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }








}
