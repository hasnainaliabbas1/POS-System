package view.DataEntryOperator;


import view.LoginScreenView;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.sql.*;
import java.util.ArrayList;

public class SaleGeneration   extends   JPanel{
    private JFrame frame;
    private JPanel mainMenuPanel;
    private JPanel salePanel;
    private JPanel reportPanel;
    private JPanel settingsPanel;
    private JComboBox<String> productComboBox;
    private JTextField quantityField;
    private JTable purchasedProductsTable;
    private JLabel totalAmountLabel;
    private JButton generateBillButton;
    private ArrayList<ProductItem> selectedProducts;

    private static final String DB_URL = "jdbc:mysql://localhost:3306/new";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    private int lastSaleId;

    public SaleGeneration() {
        frame = new JFrame("Cashier Screen");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setUndecorated(true); // Remove title bar
       // frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setSize(1000, 800);
        frame.setBackground(Color.decode("#3498db"));

        selectedProducts = new ArrayList<>();
        createMainMenu();
        frame.setVisible(true);
    }

    private void createMainMenu() {
        frame.getContentPane().removeAll(); // Clear existing content
        frame.setLayout(new BorderLayout()); // Use BorderLayout for flexible positioning
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH); // Make the frame fullscreen
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Ensure proper exit on close
        frame.setUndecorated(true); // Remove the title bar

        // Header panel
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(70, 130, 180)); // Steel Blue color
        JLabel headerLabel = new JLabel("Main Menu", JLabel.CENTER);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 24));
        headerLabel.setForeground(Color.WHITE); // White text for contrast
        headerPanel.add(headerLabel);

        // Main menu panel
        mainMenuPanel = new JPanel();
        mainMenuPanel.setLayout(new GridLayout(4, 1, 10, 10));
        mainMenuPanel.setBackground(Color.decode("#f1c40f")); // Golden background for the panel

        // Buttons for the main menu
        Color buttonColor = new Color(41, 128, 185); // Button background color (Blue shade)
        JButton startSaleButton = createMenuButton("Start Sale", e -> openSaleScreen(), buttonColor);
        JButton viewReportsButton = createMenuButton("View Reports", e -> openReportScreen(), buttonColor);
        JButton logoutButton = createMenuButton("Logout", e -> System.exit(0), buttonColor);
        JButton backButton = createMenuButton("Back", e -> navigateBack(), buttonColor);

        // Add buttons to the main menu panel
        mainMenuPanel.add(startSaleButton);
        mainMenuPanel.add(viewReportsButton);
        mainMenuPanel.add(logoutButton);
        mainMenuPanel.add(backButton);

        // Add panels to the frame
        frame.add(headerPanel, BorderLayout.NORTH); // Add header at the top
        frame.add(mainMenuPanel, BorderLayout.CENTER); // Add main menu at the center
        frame.revalidate();
        frame.repaint();
    }

    /**
     * Creates a styled menu button with a consistent color and action listener.
     *
     * @param text         The button text.
     * @param action       The action to perform when clicked.
     * @param buttonColor  The background color for the button.
     * @return The configured JButton.
     */
    private JButton createMenuButton(String text, ActionListener action, Color buttonColor) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 18));
        button.setBackground(buttonColor);
        button.setForeground(Color.WHITE); // White text for contrast
        button.setFocusPainted(false); // Remove focus border
        button.setBorder(BorderFactory.createLineBorder(Color.BLACK)); // Add a border for style
        button.addActionListener(action);
        return button;
    }



    // Method to navigate back (you can customize this logic)
    private void navigateBack() {
        // Dispose the current frame to close it
        frame.dispose();

        // Create a new instance of LoginScreenView and make it visible
        LoginScreenView login = new LoginScreenView();
        login.setVisible(true);  // Corrected method name
    }







    private void openReportScreen() {
        mainMenuPanel.setVisible(false);
        createReportPanel();
        frame.add(reportPanel);
        reportPanel.setVisible(true);
        frame.revalidate();
    }

    private void openSettingsScreen() {
        mainMenuPanel.setVisible(false);
        createSettingsPanel();
        frame.add(settingsPanel);
        settingsPanel.setVisible(true);
        frame.revalidate();
    }
/*
    private void createReportPanel() {
        reportPanel = new JPanel(new BorderLayout());
        reportPanel.setBackground(Color.decode("#2ecc71")); // Set background color
        JTable reportTable = new JTable(new DefaultTableModel(new Object[]{"Sale ID", "Total Amount", "Product ID", "Quantity", "Total Price", "Product Name"}, 0));
        loadSalesReports(reportTable);
        reportPanel.add(new JScrollPane(reportTable), BorderLayout.CENTER);
        JButton backButton = new JButton("Back to Main Menu");
        backButton.addActionListener(e -> backToMainMenu());
        reportPanel.add(backButton, BorderLayout.SOUTH);
    }

    private void loadSalesReports(JTable reportTable) {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD)) {
            String query = "SELECT sale_detail_id, total_price, , product_id,quantity,     FROM  salesdetails    ";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            DefaultTableModel model = (DefaultTableModel) reportTable.getModel();

            while (rs.next()) {
                model.addRow(new Object[]{rs.getInt("t.id"), rs.getDouble("t.total_amount"), rs.getDate("t.sale_date"),
                        rs.getInt("sd.product_id"), rs.getInt("sd.quantity"), rs.getDouble("sd.total_price"), rs.getString("p.name")});
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(frame, "Error loading sales reports: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }


 */
    private void createReportPanel() {
        reportPanel = new JPanel(new BorderLayout());
        reportPanel.setBackground(Color.decode("#2ecc71"));

        // Create a JTable for sales details
        JTable reportTable = new JTable(new DefaultTableModel(new Object[]{"Sale Detail ID", "Sale ID", "Product ID", "Quantity", "Total Price"}, 0));
        loadSalesReports(reportTable);
        reportPanel.add(new JScrollPane(reportTable), BorderLayout.CENTER);

        // Set alternating row colors
        reportTable.setDefaultRenderer(Object.class, new javax.swing.table.DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                if (row % 2 == 0) {
                    c.setBackground(Color.decode("#d5f5e3"));
                } else {
                    c.setBackground(Color.decode("#f9e79f"));
                }
                return c;
            }
        });

        JButton backButton = new JButton("Back to Main Menu");
        backButton.addActionListener(e -> backToMainMenu());
        backButton.setSize(300, 200);
        reportPanel.add(backButton, BorderLayout.SOUTH);
    }

    private void loadSalesReports(JTable reportTable) {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD)) {
            String query = "SELECT sale_detail_id, sale_id, product_id, quantity, total_price FROM salesdetails";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            DefaultTableModel model = (DefaultTableModel) reportTable.getModel();

            while (rs.next()) {
                model.addRow(new Object[]{
                        rs.getInt("sale_detail_id"),
                        rs.getInt("sale_id"),
                        rs.getInt("product_id"),
                        rs.getInt("quantity"),
                        rs.getDouble("total_price")
                });
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(frame, "Error loading sales reports: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }




    private void createSettingsPanel() {
        settingsPanel = new JPanel(new GridLayout(4, 2));
        settingsPanel.setBackground(Color.decode("#9b59b6")); // Set background color

        // Placeholder for settings fields
        settingsPanel.add(new JLabel("Database URL:"));
        JTextField dbUrlField = new JTextField(DB_URL);
        settingsPanel.add(dbUrlField);

        settingsPanel.add(new JLabel("Username:"));
        JTextField userField = new JTextField(USER);
        settingsPanel.add(userField);

        settingsPanel.add(new JLabel("Password:"));
        JPasswordField passwordField = new JPasswordField(PASSWORD);
        settingsPanel.add(passwordField);

        JButton saveButton = new JButton("Save Settings");
        saveButton.addActionListener(e -> {
            // Save settings logic (e.g., update DB_URL, USER, PASSWORD)
            // This is just a placeholder; implement actual saving logic as needed
            JOptionPane.showMessageDialog(frame, "Settings saved successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
        });
        settingsPanel.add(saveButton);

        JButton backButton = new JButton("Back to Main Menu");
        backButton.addActionListener(e -> backToMainMenu());
        settingsPanel.add(backButton);
    }

    private void backToMainMenu() {
        if (salePanel != null) salePanel.setVisible(false);
        if (reportPanel != null) reportPanel.setVisible(false);
        if (settingsPanel != null) settingsPanel.setVisible(false);
        mainMenuPanel.setVisible(true);
        frame.revalidate();
    }

    private void openSaleScreen() {
        mainMenuPanel.setVisible(false);
        createSalePanel();
        frame.add(salePanel);
        salePanel.setVisible(true);
        frame.revalidate();
    }

    private void createSalePanel() {
        salePanel = new JPanel(new GridLayout(7, 2));
        salePanel.setBackground(Color.decode("#1abc9c"));

        // Initialize Components
        JLabel productLabel = new JLabel("Select Product:");
        productComboBox = new JComboBox<>();
        loadProducts();

        JLabel quantityLabel = new JLabel("Quantity:");
        quantityField = new JTextField();

        JButton addProductButton = createLargeButton("Add Product", e -> addProductToSale());
        JButton removeProductButton = createLargeButton("Remove Product", e -> removeProductFromSale());
        JButton finalizeButton = createLargeButton("Finalize Sale", e -> finalizeSale());
        generateBillButton = createLargeButton("Generate Bill", e -> generateBill());
        generateBillButton.setEnabled(false);
        JButton cancelButton = createLargeButton("Cancel Sale", e -> cancelSale());
        JButton backButton = createLargeButton("Back to Main Menu", e -> backToMainMenu());

        totalAmountLabel = new JLabel("Total: $0.00");
        purchasedProductsTable = new JTable(new DefaultTableModel(new Object[]{"Product", "Quantity", "Unit Price", "Total"}, 0));

        salePanel.add(productLabel);
        salePanel.add(productComboBox);
        salePanel.add(quantityLabel);
        salePanel.add(quantityField);
        salePanel.add(addProductButton);
        salePanel.add(removeProductButton);
        salePanel.add(new JScrollPane(purchasedProductsTable));
        salePanel.add(totalAmountLabel);
        salePanel.add(finalizeButton);
        salePanel.add(generateBillButton);
        salePanel.add(cancelButton);
        salePanel.add(backButton);

        addProductButton.setBackground(Color.GREEN);
        removeProductButton.setBackground(Color.RED);
        finalizeButton.setBackground(Color.ORANGE);
        generateBillButton.setBackground(Color.BLUE);
        cancelButton.setBackground(Color.MAGENTA);
        backButton.setBackground(Color.LIGHT_GRAY);
    }

    private JButton createLargeButton(String text, ActionListener actionListener) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 24));
        button.setPreferredSize(new Dimension(300, 60)); // Larger button size
        button.addActionListener(actionListener);
        return button;
    }

    private JButton createMenuButton(String text, ActionListener actionListener) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 24));
        button.addActionListener(actionListener);
        return button;
    }

    private void loadProducts() {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD)) {
            String query = "SELECT name FROM products";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                productComboBox.addItem(rs.getString("name"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(frame, "Error loading products: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void addProductToSale() {
        String productName = (String) productComboBox.getSelectedItem();
        String quantityText = quantityField.getText();

        if (quantityText.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Please enter a quantity.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int quantity;
        try {
            quantity = Integer.parseInt(quantityText);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, "Invalid quantity. Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD)) {
            String productQuery = "SELECT id, price_per_unit, stock_quantity FROM products WHERE name = ?";
            PreparedStatement productStmt = conn.prepareStatement(productQuery);
            productStmt.setString(1, productName);
            ResultSet rs = productStmt.executeQuery();

            if (rs.next()) {
                int productId = rs.getInt("id");
                double unitPrice = rs.getDouble("price_per_unit");
                int stockQuantity = rs.getInt("stock_quantity");

                if (stockQuantity < quantity) {
                    JOptionPane.showMessageDialog(frame, "Not enough stock!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                double totalPrice = unitPrice * quantity;
                selectedProducts.add(new ProductItem(productId, productName, unitPrice, quantity, totalPrice));

                DefaultTableModel model = (DefaultTableModel) purchasedProductsTable.getModel();
                model.addRow(new Object[]{productName, quantity, unitPrice, totalPrice});

                double totalAmount = selectedProducts.stream().mapToDouble(ProductItem::getTotalPrice).sum();
                totalAmountLabel.setText("Total: $" + totalAmount);

                String updateStockQuery = "UPDATE products SET stock_quantity = stock_quantity - ? WHERE id = ?";
                PreparedStatement updateStockStmt = conn.prepareStatement(updateStockQuery);
                updateStockStmt.setInt(1, quantity);
                updateStockStmt.setInt(2, productId);
                updateStockStmt.executeUpdate();
            } else {
                JOptionPane.showMessageDialog(frame, "Product not found in the inventory.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void removeProductFromSale() {
        int selectedRow = purchasedProductsTable.getSelectedRow();

        if (selectedRow >= 0) {
            ProductItem removedProduct = selectedProducts.remove(selectedRow);
            ((DefaultTableModel) purchasedProductsTable.getModel()).removeRow(selectedRow);

            double totalAmount = selectedProducts.stream().mapToDouble(ProductItem::getTotalPrice).sum();
            totalAmountLabel.setText("Total: $" + totalAmount);

            try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD)) {
                // Update stock quantity in Product Inventory (increase stock for removed product)
                String updateStockQuery = "UPDATE products SET stock_quantity = stock_quantity + ? WHERE id = ?";
                PreparedStatement updateStockStmt = conn.prepareStatement(updateStockQuery);
                updateStockStmt.setInt(1, removedProduct.getQuantity());
                updateStockStmt.setInt(2, removedProduct.getProductId());
                updateStockStmt.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            JOptionPane.showMessageDialog(frame, "No product selected to remove.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void finalizeSale() {
        double totalAmount = selectedProducts.stream().mapToDouble(ProductItem::getTotalPrice).sum();

        if (totalAmount > 0) {
            try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD)) {
                conn.setAutoCommit(false);  // Begin transaction

                // Insert sale into TransactionSales table
                String insertSaleQuery = "INSERT INTO transactionsales (total_amount) VALUES (?)";
                try (PreparedStatement insertSaleStmt = conn.prepareStatement(insertSaleQuery, Statement.RETURN_GENERATED_KEYS)) {
                    insertSaleStmt.setDouble(1, totalAmount);
                    int affectedRows = insertSaleStmt.executeUpdate();

                    if (affectedRows > 0) {
                        ResultSet generatedKeys = insertSaleStmt.getGeneratedKeys();
                        if (generatedKeys.next()) {
                            lastSaleId = generatedKeys.getInt(1);

                            String insertSaleDetailQuery = "INSERT INTO salesdetails (sale_id, product_id, quantity, total_price) VALUES (?, ?, ?, ?)";
                            try (PreparedStatement insertSaleDetailStmt = conn.prepareStatement(insertSaleDetailQuery)) {
                                for (ProductItem productItem : selectedProducts) {
                                    insertSaleDetailStmt.setInt(1, lastSaleId);
                                    insertSaleDetailStmt.setInt(2, productItem.getProductId());
                                    insertSaleDetailStmt.setInt(3, productItem.getQuantity());
                                    insertSaleDetailStmt.setDouble(4, productItem.getTotalPrice());
                                    insertSaleDetailStmt.addBatch();
                                }
                                insertSaleDetailStmt.executeBatch();
                            }
                            String updateStockQuery = "UPDATE products SET stock_quantity = stock_quantity - ? WHERE id = ?";
                            try (PreparedStatement updateStockStmt = conn.prepareStatement(updateStockQuery)) {
                                for (ProductItem productItem : selectedProducts) {
                                    updateStockStmt.setInt(1, productItem.getQuantity());
                                    updateStockStmt.setInt(2, productItem.getProductId());
                                    updateStockStmt.addBatch();
                                }
                                updateStockStmt.executeBatch();
                            }
                            conn.commit();
                            JOptionPane.showMessageDialog(frame, "Sale finalized successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                            generateBillButton.setEnabled(true);
                        }
                    } else {
                        conn.rollback();
                        JOptionPane.showMessageDialog(frame, "Error finalizing sale: No rows inserted into TransactionSales.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (SQLException e) {
                    conn.rollback();
                    JOptionPane.showMessageDialog(frame, "Error finalizing sale: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    e.printStackTrace();
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(frame, "Error finalizing sale: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            }
        } else {
            JOptionPane.showMessageDialog(frame, "No products added to the sale.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void generateBill() {
        try {
            String filePath = "Sale_" + lastSaleId + ".txt";
            File billFile = new File(filePath);
            if (!billFile.exists()) {
                billFile.createNewFile();
            }

            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));

            writer.write("Sale Bill\n");
            writer.write("Sale ID: " + lastSaleId + "\n");
            writer.write("Total Amount: $" + totalAmountLabel.getText() + "\n");
            writer.write("Details:\n");

            writer.write(String.format("%-20s %-10s %-15s %-15s\n", "Product", "Quantity", "Unit Price", "Total Price"));
            for (ProductItem item : selectedProducts) {
                writer.write(String.format("%-20s %-10d %-15.2f %-15.2f\n",
                        item.getProductName(), item.getQuantity(), item.getUnitPrice(), item.getTotalPrice()));
            }
            writer.close();
            JOptionPane.showMessageDialog(frame, "Bill generated successfully in text file.", "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {

            JOptionPane.showMessageDialog(frame, "Error generating bill: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    private void cancelSale() {
        int confirm = JOptionPane.showConfirmDialog(frame, "Are you sure you want to cancel this sale?", "Confirm Cancel", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD)) {
                for (ProductItem productItem : selectedProducts) {
                    String updateStockQuery = "UPDATE products SET stock_quantity = stock_quantity + ? WHERE id = ?";
                    PreparedStatement updateStockStmt = conn.prepareStatement(updateStockQuery);
                    updateStockStmt.setInt(1, productItem.getQuantity());
                    updateStockStmt.setInt(2, productItem.getProductId());
                    updateStockStmt.executeUpdate();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            selectedProducts.clear();
            ((DefaultTableModel) purchasedProductsTable.getModel()).setRowCount(0);
            totalAmountLabel.setText("Total: $0.00");
            JOptionPane.showMessageDialog(frame, "Sale canceled.", "Info", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(SaleGeneration::new);
    }

    static class ProductItem {
        private int productId;

        private String productName;
        private double unitPrice;
        private int quantity;
        private double totalPrice;

        public ProductItem(int productId, String productName, double unitPrice, int quantity, double totalPrice) {
            this.productId = productId;
            this.productName = productName;
            this.unitPrice = unitPrice;
            this.quantity = quantity;
            this.totalPrice = totalPrice;
        }

        public int getProductId() {

            return productId;
        }

        public String getProductName() {
            return productName;
        }

        public double getUnitPrice() {
            return unitPrice;
        }

        public int getQuantity() {
            return quantity;
        }

        public double getTotalPrice() {
            return totalPrice;
        }
    }
}
