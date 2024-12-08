package view;

import javax.swing.*;

import controller.ProductController;
import controller.VendorController;
import model.Product;
import model.Vendor;



























/*


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class VendorView {

    private JFrame frame;

    // Constructor to set up the main vendor window
    public VendorView() {
        frame = new JFrame("Vendor Management");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600); // Increased frame size
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setBackground(new Color(255, 255, 255)); // White background

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10); // Padding

        // Labels and Fields for Adding Vendor
        JLabel nameLabel = createStyledLabel("Name:");
        JTextField nameField = createStyledTextField();
        JLabel contactLabel = createStyledLabel("Contact Info:");
        JTextField contactField = createStyledTextField();
        JLabel addressLabel = createStyledLabel("Address:");
        JTextField addressField = createStyledTextField();

        JLabel companyNameLabel = createStyledLabel("Company Name:");
        JTextField companyNameField = createStyledTextField();
        JLabel companyContactLabel = createStyledLabel("Company Contact Info:");
        JTextField companyContactField = createStyledTextField();
        JLabel companyAddressLabel = createStyledLabel("Company Address:");
        JTextField companyAddressField = createStyledTextField();

        // Buttons
        JButton addVendorButton = createStyledButton("Add Vendor");
        JButton openOldVendorButton = createStyledButton("Old Vendor");

        // Add components to the panel using GridBagLayout
        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(nameLabel, gbc);
        gbc.gridx = 1;
        panel.add(nameField, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        panel.add(contactLabel, gbc);
        gbc.gridx = 1;
        panel.add(contactField, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        panel.add(addressLabel, gbc);
        gbc.gridx = 1;
        panel.add(addressField, gbc);

        gbc.gridx = 0; gbc.gridy = 3;
        panel.add(companyNameLabel, gbc);
        gbc.gridx = 1;
        panel.add(companyNameField, gbc);

        gbc.gridx = 0; gbc.gridy = 4;
        panel.add(companyContactLabel, gbc);
        gbc.gridx = 1;
        panel.add(companyContactField, gbc);

        gbc.gridx = 0; gbc.gridy = 5;
        panel.add(companyAddressLabel, gbc);
        gbc.gridx = 1;
        panel.add(companyAddressField, gbc);

        gbc.gridx = 0; gbc.gridy = 6;
        panel.add(addVendorButton, gbc);
        gbc.gridx = 1;
        panel.add(openOldVendorButton, gbc);

        frame.add(panel);
        frame.setVisible(true);

        // Add Vendor Action Logic
        addVendorButton.addActionListener(e -> {
            if (nameField.getText().isEmpty() || contactField.getText().isEmpty() || addressField.getText().isEmpty() ||
                    companyNameField.getText().isEmpty() || companyContactField.getText().isEmpty() || companyAddressField.getText().isEmpty()) {
                JOptionPane.showMessageDialog(frame, "All fields are required!", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                Vendor vendor = new Vendor();
                vendor.setName(nameField.getText());
                vendor.setContactInfo(contactField.getText());
                vendor.setAddress(addressField.getText());
                vendor.setCompanyName(companyNameField.getText());
                vendor.setCompanyContactInfo(companyContactField.getText());
                vendor.setCompanyAddress(companyAddressField.getText());

                VendorController vendorController = new VendorController();
                vendorController.addVendor(vendor);

                JOptionPane.showMessageDialog(frame, "Vendor Added Successfully!");
            }
        });

        // Open Old Vendor Action Logic
        openOldVendorButton.addActionListener(e -> openOldVendorWindow(frame));
    }

    // Method to open a window displaying old vendor information
    private void openOldVendorWindow(JFrame parentFrame) {
        JFrame vendorSelectionFrame = new JFrame("Select Vendor");
        vendorSelectionFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        vendorSelectionFrame.setSize(800, 600);  // Increased size
        vendorSelectionFrame.setLocationRelativeTo(parentFrame);

        JPanel vendorPanel = new JPanel();
        vendorPanel.setLayout(new GridBagLayout());
        vendorPanel.setBackground(new Color(255, 255, 255)); // White background

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);

        VendorController vendorController = new VendorController();
        List<Vendor> vendors = vendorController.getAllVendors();

        JComboBox<String> vendorComboBox = new JComboBox<>();
        for (Vendor vendor : vendors) {
            vendorComboBox.addItem(vendor.getName());
        }

        JLabel nameLabel = createStyledLabel("Name:");
        JLabel contactInfoLabel = createStyledLabel("Contact Info:");
        JLabel addressLabel = createStyledLabel("Address:");
        JLabel companyNameLabel = createStyledLabel("Company Name:");
        JLabel companyContactLabel = createStyledLabel("Company Contact Info:");
        JLabel companyAddressLabel = createStyledLabel("Company Address:");

        JButton proceedButton = createStyledButton("Proceed to Add Product");
        JButton backButton = createStyledButton("Back");

        // ComboBox Action Listener to update vendor info
        vendorComboBox.addActionListener(e -> {
            String selectedVendorName = (String) vendorComboBox.getSelectedItem();
            Vendor selectedVendor = getVendorByName(vendors, selectedVendorName);

            if (selectedVendor != null) {
                nameLabel.setText("Name: " + selectedVendor.getName());
                contactInfoLabel.setText("Contact Info: " + selectedVendor.getContactInfo());
                addressLabel.setText("Address: " + selectedVendor.getAddress());
                companyNameLabel.setText("Company Name: " + selectedVendor.getCompanyName());
                companyContactLabel.setText("Company Contact Info: " + selectedVendor.getCompanyContactInfo());
                companyAddressLabel.setText("Company Address: " + selectedVendor.getCompanyAddress());
            }
        });

        // Proceed Button Action Listener
        proceedButton.addActionListener(e -> {
            String selectedVendorName = (String) vendorComboBox.getSelectedItem();
            int vendorId = vendorController.getVendorIdByName(selectedVendorName);

            if (vendorId != -1) {
                openAddProductWindow(vendorId, parentFrame);
                vendorSelectionFrame.dispose();
            } else {
                JOptionPane.showMessageDialog(vendorSelectionFrame, "Vendor not found!");
            }
        });

        // Back Button Action Listener
        backButton.addActionListener(e -> {
            vendorSelectionFrame.dispose();
            parentFrame.setVisible(true); // Return to main vendor management frame
        });

        // Add components to the panel
        gbc.gridx = 0; gbc.gridy = 0;
        vendorPanel.add(new JLabel("Select Vendor:"), gbc);
        gbc.gridx = 1;
        vendorPanel.add(vendorComboBox, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        vendorPanel.add(nameLabel, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        vendorPanel.add(contactInfoLabel, gbc);

        gbc.gridx = 0; gbc.gridy = 3;
        vendorPanel.add(addressLabel, gbc);

        gbc.gridx = 0; gbc.gridy = 4;
        vendorPanel.add(companyNameLabel, gbc);

        gbc.gridx = 0; gbc.gridy = 5;
        vendorPanel.add(companyContactLabel, gbc);

        gbc.gridx = 0; gbc.gridy = 6;
        vendorPanel.add(companyAddressLabel, gbc);

        gbc.gridx = 0; gbc.gridy = 7;
        vendorPanel.add(proceedButton, gbc);

        gbc.gridx = 1; gbc.gridy = 7;
        vendorPanel.add(backButton, gbc);

        vendorSelectionFrame.add(vendorPanel);
        vendorSelectionFrame.setVisible(true);
        parentFrame.setVisible(false);
    }

    // Method to find the vendor by name from the list of vendors
    private Vendor getVendorByName(List<Vendor> vendors, String name) {
        for (Vendor vendor : vendors) {
            if (vendor.getName().equals(name)) {
                return vendor;
            }
        }
        return null;
    }

    // Method to open Add Product window with increased size
    private void openAddProductWindow(int vendorId, JFrame parentFrame) {
        JFrame productFrame = new JFrame("Add Product");
        productFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        productFrame.setSize(500, 500);  // Increased size to accommodate the new fields
        productFrame.setLocationRelativeTo(parentFrame);

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setBackground(new Color(255, 255, 255));  // White background

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);

        // Product form labels and text fields
        JLabel productNameLabel = createStyledLabel("Product Name:");
        JTextField productNameField = createStyledTextField();

        JLabel productCategoryLabel = createStyledLabel("Product Category:");
        JTextField productCategoryField = createStyledTextField();

        JLabel productOriginalPriceLabel = createStyledLabel("Original Price:");
        JTextField productOriginalPriceField = createStyledTextField();

        JLabel productSalePriceLabel = createStyledLabel("Sale Price:");
        JTextField productSalePriceField = createStyledTextField();

        JLabel productPricePerUnitLabel = createStyledLabel("Price per Unit:");
        JTextField productPricePerUnitField = createStyledTextField();

        JLabel productPricePerCartonLabel = createStyledLabel("Price per Carton:");
        JTextField productPricePerCartonField = createStyledTextField();

        JButton addProductButton = createStyledButton("Add Product");

        // Add components to the panel
        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(productNameLabel, gbc);
        gbc.gridx = 1;
        panel.add(productNameField, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        panel.add(productCategoryLabel, gbc);
        gbc.gridx = 1;
        panel.add(productCategoryField, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        panel.add(productOriginalPriceLabel, gbc);
        gbc.gridx = 1;
        panel.add(productOriginalPriceField, gbc);

        gbc.gridx = 0; gbc.gridy = 3;
        panel.add(productSalePriceLabel, gbc);
        gbc.gridx = 1;
        panel.add(productSalePriceField, gbc);

        gbc.gridx = 0; gbc.gridy = 4;
        panel.add(productPricePerUnitLabel, gbc);
        gbc.gridx = 1;
        panel.add(productPricePerUnitField, gbc);

        gbc.gridx = 0; gbc.gridy = 5;
        panel.add(productPricePerCartonLabel, gbc);
        gbc.gridx = 1;
        panel.add(productPricePerCartonField, gbc);

        gbc.gridx = 0; gbc.gridy = 6;
        panel.add(addProductButton, gbc);

        productFrame.add(panel);
        productFrame.setVisible(true);

        // Add product action
        addProductButton.addActionListener(e -> {
            String productName = productNameField.getText();
            String productCategory = productCategoryField.getText();
            String productOriginalPriceText = productOriginalPriceField.getText();
            String productSalePriceText = productSalePriceField.getText();
            String productPricePerUnitText = productPricePerUnitField.getText();
            String productPricePerCartonText = productPricePerCartonField.getText();

            if (productName.isEmpty() || productCategory.isEmpty() || productOriginalPriceText.isEmpty() ||
                    productSalePriceText.isEmpty() || productPricePerUnitText.isEmpty() || productPricePerCartonText.isEmpty()) {
                JOptionPane.showMessageDialog(productFrame, "All fields are required!", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                try {
                    double originalPrice = Double.parseDouble(productOriginalPriceText);
                    double salePrice = Double.parseDouble(productSalePriceText);
                    double pricePerUnit = Double.parseDouble(productPricePerUnitText);
                    double pricePerCarton = Double.parseDouble(productPricePerCartonText);

                    Product product = new Product(vendorId, productName, productCategory, originalPrice, salePrice, pricePerUnit, pricePerCarton);
                    ProductController productController = new ProductController();
                    productController.addProduct(product);

                    if (salePrice <= originalPrice) {
                        JOptionPane.showMessageDialog(productFrame, "Sale price must be greater than the original price!", "Error", JOptionPane.ERROR_MESSAGE);
                    } else {

                        productController.addProduct(product);

                        JOptionPane.showMessageDialog(productFrame, "Product Added Successfully!");
                    }

                    //JOptionPane.showMessageDialog(productFrame, "Product Added Successfully!");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(productFrame, "Invalid price fields!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    // Helper method to create styled labels
    private JLabel createStyledLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Arial", Font.BOLD, 14));
        return label;
    }

    // Helper method to create styled text fields
    private JTextField createStyledTextField() {
        JTextField textField = new JTextField(20);
        textField.setFont(new Font("Arial", Font.PLAIN, 14));
        return textField;
    }

    // Helper method to create styled buttons
    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.PLAIN, 14));
        return button;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new VendorView());
    }
}



 */


































import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class VendorView {

    private JFrame frame;

    // Constructor to set up the main vendor window
    public VendorView() {
        frame = new JFrame("Vendor Management");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 800); // Increased frame size
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setBackground(new Color(240, 248, 255)); // Alice Blue

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10); // Padding

        // Labels and Fields for Adding Vendor
        JLabel nameLabel = createStyledLabel("Name:");
        JTextField nameField = createStyledTextField();
        JLabel contactLabel = createStyledLabel("Contact Info:");
        JTextField contactField = createStyledTextField();
        JLabel addressLabel = createStyledLabel("Address:");
        JTextField addressField = createStyledTextField();

        JLabel companyNameLabel = createStyledLabel("Company Name:");
        JTextField companyNameField = createStyledTextField();
        JLabel companyContactLabel = createStyledLabel("Company Contact Info:");
        JTextField companyContactField = createStyledTextField();
        JLabel companyAddressLabel = createStyledLabel("Company Address:");
        JTextField companyAddressField = createStyledTextField();

        // Buttons
        JButton addVendorButton = createStyledButton("Add Vendor");
        JButton openOldVendorButton = createStyledButton("Old Vendor");

        // Add components to the panel using GridBagLayout
        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(nameLabel, gbc);
        gbc.gridx = 1;
        panel.add(nameField, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        panel.add(contactLabel, gbc);
        gbc.gridx = 1;
        panel.add(contactField, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        panel.add(addressLabel, gbc);
        gbc.gridx = 1;
        panel.add(addressField, gbc);

        gbc.gridx = 0; gbc.gridy = 3;
        panel.add(companyNameLabel, gbc);
        gbc.gridx = 1;
        panel.add(companyNameField, gbc);

        gbc.gridx = 0; gbc.gridy = 4;
        panel.add(companyContactLabel, gbc);
        gbc.gridx = 1;
        panel.add(companyContactField, gbc);

        gbc.gridx = 0; gbc.gridy = 5;
        panel.add(companyAddressLabel, gbc);
        gbc.gridx = 1;
        panel.add(companyAddressField, gbc);

        gbc.gridx = 0; gbc.gridy = 6;
        panel.add(addVendorButton, gbc);
        gbc.gridx = 1;
        panel.add(openOldVendorButton, gbc);

        frame.add(panel);
        frame.setVisible(true);

        // Add Vendor Action Logic
        addVendorButton.addActionListener(e -> {
            if (nameField.getText().isEmpty() || contactField.getText().isEmpty() || addressField.getText().isEmpty() ||
                    companyNameField.getText().isEmpty() || companyContactField.getText().isEmpty() || companyAddressField.getText().isEmpty()) {
                JOptionPane.showMessageDialog(frame, "All fields are required!", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                Vendor vendor = new Vendor();
                vendor.setName(nameField.getText());
                vendor.setContactInfo(contactField.getText());
                vendor.setAddress(addressField.getText());
                vendor.setCompanyName(companyNameField.getText());
                vendor.setCompanyContactInfo(companyContactField.getText());
                vendor.setCompanyAddress(companyAddressField.getText());

                VendorController vendorController = new VendorController();
                vendorController.addVendor(vendor);

                JOptionPane.showMessageDialog(frame, "Vendor Added Successfully!");
            }
        });

        // Open Old Vendor Action Logic
        openOldVendorButton.addActionListener(e -> openOldVendorWindow(frame));
    }

    // Method to open a window displaying old vendor information
    private void openOldVendorWindow(JFrame parentFrame) {
        JFrame vendorSelectionFrame = new JFrame("Select Vendor");
        vendorSelectionFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        vendorSelectionFrame.setSize(1000, 800); // Increased size
        vendorSelectionFrame.setLocationRelativeTo(parentFrame);

        JPanel vendorPanel = new JPanel();
        vendorPanel.setLayout(new GridBagLayout());
        vendorPanel.setBackground(new Color(240, 248, 255)); // Alice Blue

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);

        VendorController vendorController = new VendorController();
        List<Vendor> vendors = vendorController.getAllVendors();

        JComboBox<String> vendorComboBox = new JComboBox<>();
        for (Vendor vendor : vendors) {
            vendorComboBox.addItem(vendor.getName());
        }

        JLabel nameLabel = createStyledLabel("Name:");
        JLabel contactInfoLabel = createStyledLabel("Contact Info:");
        JLabel addressLabel = createStyledLabel("Address:");
        JLabel companyNameLabel = createStyledLabel("Company Name:");
        JLabel companyContactLabel = createStyledLabel("Company Contact Info:");
        JLabel companyAddressLabel = createStyledLabel("Company Address:");

        JButton proceedButton = createStyledButton("Proceed to Add Product");
        JButton backButton = createStyledButton("Back");

        // ComboBox Action Listener to update vendor info
        vendorComboBox.addActionListener(e -> {
            String selectedVendorName = (String) vendorComboBox.getSelectedItem();
            Vendor selectedVendor = getVendorByName(vendors, selectedVendorName);

            if (selectedVendor != null) {
                nameLabel.setText("Name: " + selectedVendor.getName());
                contactInfoLabel.setText("Contact Info: " + selectedVendor.getContactInfo());
                addressLabel.setText("Address: " + selectedVendor.getAddress());
                companyNameLabel.setText("Company Name: " + selectedVendor.getCompanyName());
                companyContactLabel.setText("Company Contact Info: " + selectedVendor.getCompanyContactInfo());
                companyAddressLabel.setText("Company Address: " + selectedVendor.getCompanyAddress());
            }
        });

        // Proceed Button Action Listener
        proceedButton.addActionListener(e -> {
            String selectedVendorName = (String) vendorComboBox.getSelectedItem();
            int vendorId = vendorController.getVendorIdByName(selectedVendorName);

            if (vendorId != -1) {
                openAddProductWindow(vendorId, parentFrame);
                vendorSelectionFrame.dispose();
            } else {
                JOptionPane.showMessageDialog(vendorSelectionFrame, "Vendor not found!");
            }
        });

        // Back Button Action Listener
        backButton.addActionListener(e -> {
            vendorSelectionFrame.dispose();
            parentFrame.setVisible(true); // Return to main vendor management frame
        });

        // Add components to the panel
        gbc.gridx = 0; gbc.gridy = 0;
        vendorPanel.add(new JLabel("Select Vendor:"), gbc);
        gbc.gridx = 1;
        vendorPanel.add(vendorComboBox, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        vendorPanel.add(nameLabel, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        vendorPanel.add(contactInfoLabel, gbc);

        gbc.gridx = 0; gbc.gridy = 3;
        vendorPanel.add(addressLabel, gbc);

        gbc.gridx = 0; gbc.gridy = 4;
        vendorPanel.add(companyNameLabel, gbc);

        gbc.gridx = 0; gbc.gridy = 5;
        vendorPanel.add(companyContactLabel, gbc);

        gbc.gridx = 0; gbc.gridy = 6;
        vendorPanel.add(companyAddressLabel, gbc);

        gbc.gridx = 0; gbc.gridy = 7;
        vendorPanel.add(proceedButton, gbc);

        gbc.gridx = 1; gbc.gridy = 7;
        vendorPanel.add(backButton, gbc);

        vendorSelectionFrame.add(vendorPanel);
        vendorSelectionFrame.setVisible(true);
        parentFrame.setVisible(false);
    }

    // Method to find the vendor by name from the list of vendors
    private Vendor getVendorByName(List<Vendor> vendors, String name) {
        for (Vendor vendor : vendors) {
            if (vendor.getName().equals(name)) {
                return vendor;
            }
        }
        return null;
    }








    private void openAddProductWindow(int vendorId, JFrame parentFrame) {
        JFrame productFrame = new JFrame("Add Product");
        productFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        productFrame.setSize(800, 600);
        productFrame.setLocationRelativeTo(parentFrame);

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setBackground(new Color(240, 248, 255)); // Alice Blue

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);

        // Product form labels and text fields
        JLabel productNameLabel = createStyledLabel("Product Name:");
        JTextField productNameField = createStyledTextField();

        JLabel productCategoryLabel = createStyledLabel("Product Category:");
        JTextField productCategoryField = createStyledTextField();

        JLabel productOriginalPriceLabel = createStyledLabel("Original Price:");
        JTextField productOriginalPriceField = createStyledTextField();

        JLabel productSalePriceLabel = createStyledLabel("Sale Price:");
        JTextField productSalePriceField = createStyledTextField();

        JLabel productPricePerUnitLabel = createStyledLabel("Price per Unit:");
        JTextField productPricePerUnitField = createStyledTextField();

        JLabel productPricePerCartonLabel = createStyledLabel("Price per Carton:");
        JTextField productPricePerCartonField = createStyledTextField();

        JLabel productStockQuantityLabel = createStyledLabel("Stock Quantity:");
        JTextField productStockQuantityField = createStyledTextField();

        JButton addProductButton = createStyledButton("Add Product");

        JButton backButton = createStyledButton("Back");
        // Add components to the panel
        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(productNameLabel, gbc);
        gbc.gridx = 1;
        panel.add(productNameField, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        panel.add(productCategoryLabel, gbc);
        gbc.gridx = 1;
        panel.add(productCategoryField, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        panel.add(productOriginalPriceLabel, gbc);
        gbc.gridx = 1;
        panel.add(productOriginalPriceField, gbc);

        gbc.gridx = 0; gbc.gridy = 3;
        panel.add(productSalePriceLabel, gbc);
        gbc.gridx = 1;
        panel.add(productSalePriceField, gbc);

        gbc.gridx = 0; gbc.gridy = 4;
        panel.add(productPricePerUnitLabel, gbc);
        gbc.gridx = 1;
        panel.add(productPricePerUnitField, gbc);

        gbc.gridx = 0; gbc.gridy = 5;
        panel.add(productPricePerCartonLabel, gbc);
        gbc.gridx = 1;
        panel.add(productPricePerCartonField, gbc);

        gbc.gridx = 0; gbc.gridy = 6;
        panel.add(productStockQuantityLabel, gbc);
        gbc.gridx = 1;
        panel.add(productStockQuantityField, gbc);

        gbc.gridx = 0; gbc.gridy = 7;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(addProductButton, gbc);

        gbc.gridx = 1; gbc.gridy = 8;  // Place Back button below
        panel.add(backButton, gbc);


        productFrame.add(panel);
        productFrame.setVisible(true);





        // Add "Back" button functionality
        backButton.addActionListener(e -> {
            productFrame.dispose();  // Close the current frame
            parentFrame.setVisible(true);  // Show the parent frame
        });

        // Add product action listener
        addProductButton.addActionListener(e -> {
            // Get and trim input values
            String productName = productNameField.getText().trim();
            String productCategory = productCategoryField.getText().trim();
            String productOriginalPriceText = productOriginalPriceField.getText().trim();
            String productSalePriceText = productSalePriceField.getText().trim();
            String productPricePerUnitText = productPricePerUnitField.getText().trim();
            String productPricePerCartonText = productPricePerCartonField.getText().trim();
            String productStockQuantityText = productStockQuantityField.getText().trim();

            // Validate input fields
            if (productName.isEmpty() || productCategory.isEmpty() || productOriginalPriceText.isEmpty() ||
                    productSalePriceText.isEmpty() || productPricePerUnitText.isEmpty() ||
                    productPricePerCartonText.isEmpty() || productStockQuantityText.isEmpty()) {
                JOptionPane.showMessageDialog(productFrame, "All fields are required!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                // Parse numeric fields
                double originalPrice = Double.parseDouble(productOriginalPriceText);
                double salePrice = Double.parseDouble(productSalePriceText);
                double pricePerUnit = Double.parseDouble(productPricePerUnitText);
                double pricePerCarton = Double.parseDouble(productPricePerCartonText);
                int stockQuantity = Integer.parseInt(productStockQuantityText);

                // Validate sale price
                if (salePrice <= originalPrice) {
                    JOptionPane.showMessageDialog(productFrame, "Sale price must be greater than the original price!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Create a product instance and add it to the database
                Product product = new Product(vendorId, productName, productCategory, originalPrice, salePrice, pricePerUnit, pricePerCarton, stockQuantity);
                ProductController productController = new ProductController();
                productController.addProduct(product);

                // Show success message
                JOptionPane.showMessageDialog(productFrame, "Product Added Successfully!");

                // Clear fields after successful addition
                productNameField.setText("");
                productCategoryField.setText("");
                productOriginalPriceField.setText("");
                productSalePriceField.setText("");
                productPricePerUnitField.setText("");
                productPricePerCartonField.setText("");
                productStockQuantityField.setText("");

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(productFrame, "Please enter valid numeric values in price and stock quantity fields!", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(productFrame, "An unexpected error occurred: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            }
        });
    }









    // Helper method to create styled labels
    private JLabel createStyledLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Arial", Font.BOLD, 14));
        return label;
    }

    // Helper method to create styled text fields
    private JTextField createStyledTextField() {
        JTextField textField = new JTextField(20);
        textField.setFont(new Font("Arial", Font.PLAIN, 14));
        return textField;
    }

    // Helper method to create styled buttons
    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.PLAIN, 14));
        return button;
    }
}


