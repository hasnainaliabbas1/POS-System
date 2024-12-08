package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import controller.ProductController;
import model.Product;
import java.awt.*;
import java.util.List;

public class ProductView {
    private JFrame frame;
    private JTable productTable;
    private DefaultTableModel tableModel;
    private ProductController productController;

    public ProductView() {
        productController = new ProductController();

        frame = new JFrame("Product Management");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 700);
        frame.setLayout(new BorderLayout());

        // Create the table model without the "Actions" column
        String[] columnNames = {"ID", "Name", "Category", "Original Price", "Sale Price", "Price Per Unit", "Price Per Carton", "Vendor ID", "Stock Quantity"};
        tableModel = new DefaultTableModel(columnNames, 0);
        productTable = new JTable(tableModel) {
            @Override
            public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
                Component c = super.prepareRenderer(renderer, row, column);
                // Set alternating row colors
                if (isRowSelected(row)) {
                    c.setBackground(getSelectionBackground());
                } else {
                    if (row % 2 == 0) {
                        c.setBackground(new Color(220, 220, 255));  // Light Blue for even rows
                    } else {
                        c.setBackground(Color.WHITE);  // White for odd rows
                    }
                }
                return c;
            }
        };

        JScrollPane scrollPane = new JScrollPane(productTable);
        frame.add(scrollPane, BorderLayout.CENTER);

        // Load existing products into the table
        loadProducts();

        // Create a back button
        JButton backButton = new JButton("Back");
        backButton.setBackground(Color.RED);
        backButton.setForeground(Color.WHITE);
        backButton.addActionListener(e -> {
            // Logic to go back or exit the application
            int confirm = JOptionPane.showConfirmDialog(frame, "Are you sure you want to go back?", "Confirm", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                frame.dispose(); // Close the current window
                // Optionally, you can open another view here
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(backButton);
        frame.add(buttonPanel, BorderLayout.SOUTH); // Add the button panel to the bottom

        frame.getContentPane().setBackground(new Color(240, 240, 240)); // Set background color for the frame
        frame.setVisible(true);
    }
    private void loadProducts() {
        List<Product> products = productController.getAllProducts();

        tableModel.setRowCount(0);
        for (Product product : products) {
            Object[] rowData = {
                    product.getId(),
                    product.getName(),
                    product.getCategory(),
                    product.getOriginalPrice(),
                    product.getSalePrice(),
                    product.getPricePerUnit(),
                    product.getPricePerCarton(),
                    product.getVendorId(),
                    product.getStockQuantity()
            };
            tableModel.addRow(rowData);
        }
    }

    private void editProduct(Product product) {
        JTextField nameField = new JTextField(product.getName(), 20);
        JTextField categoryField = new JTextField(product.getCategory(), 20);
        JTextField originalPriceField = new JTextField(String.valueOf(product.getOriginalPrice()), 10);
        JTextField salePriceField = new JTextField(String.valueOf(product.getSalePrice()), 10);
        JTextField pricePerUnitField = new JTextField(String.valueOf(product.getPricePerUnit()), 10);
        JTextField pricePerCartonField = new JTextField(String.valueOf(product.getPricePerCarton()), 10);
        JTextField vendorIdField = new JTextField(String.valueOf(product.getVendorId()), 10);

        JPanel panel = new JPanel(new GridLayout(0, 2));
        panel.add(new JLabel("Name: panel.add(nameField"));
                panel.add(new JLabel("Category:"));
        panel.add(categoryField);
        panel.add(new JLabel("Original Price:"));
        panel.add(originalPriceField);
        panel.add(new JLabel("Sale Price:"));
        panel.add(salePriceField);
        panel.add(new JLabel("Price Per Unit:"));
        panel.add(pricePerUnitField);
        panel.add(new JLabel("Price Per Carton:"));
        panel.add(pricePerCartonField);
        panel.add(new JLabel("Vendor ID:"));
        panel.add(vendorIdField);

        int result = JOptionPane.showConfirmDialog(frame, panel, "Edit Product", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            product.setName(nameField.getText());
            product.setCategory(categoryField.getText());
            product.setOriginalPrice(Double.parseDouble(originalPriceField.getText()));
            product.setSalePrice(Double.parseDouble(salePriceField.getText()));
            product.setPricePerUnit(Double.parseDouble(pricePerUnitField.getText()));
            product.setPricePerCarton(Double.parseDouble(pricePerCartonField.getText()));
            product.setVendorId(Integer.parseInt(vendorIdField.getText()));

            productController.updateProduct(product); // Update the product in the database
            loadProducts(); // Refresh the table
        }
    }
    private void copyProduct(Product product) {
        Product copiedProduct = new Product();
        copiedProduct.setName(product.getName());
        copiedProduct.setCategory(product.getCategory());
        copiedProduct.setOriginalPrice(product.getOriginalPrice());
        copiedProduct.setSalePrice(product.getSalePrice());
        copiedProduct.setPricePerUnit(product.getPricePerUnit());
        copiedProduct.setPricePerCarton(product.getPricePerCarton());
        copiedProduct.setVendorId(product.getVendorId());

        productController.addProduct(copiedProduct);
        loadProducts(); // Refresh the table
    }
    private void deleteProduct(Product product) {
        int confirmation = JOptionPane.showConfirmDialog(frame, "Are you sure you want to delete this product?", "Delete Product", JOptionPane.YES_NO_OPTION);
        if (confirmation == JOptionPane.YES_OPTION) {
            productController.deleteProduct(product.getId());
            loadProducts(); // Refresh the table
        }
    }

}












