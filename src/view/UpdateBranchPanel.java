package view;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import model.BranchModel;

public class UpdateBranchPanel extends JPanel {

    private JComboBox<String> branchComboBox;
    private DefaultComboBoxModel<String> comboBoxModel;
    private JTextField branchNameField, cityField, addressField, phoneField;
    private JCheckBox isActiveCheckBox;
    private JButton saveButton, clearButton;

    public UpdateBranchPanel() {
        setLayout(new GridBagLayout());
        setBorder(BorderFactory.createTitledBorder("Update Branch"));
        setBackground(Color.WHITE);
        initializeComponents();
        loadBranchNamesFromDatabase();
    }

    private void initializeComponents() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(20, 20, 20, 20);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        comboBoxModel = new DefaultComboBoxModel<>();
        branchComboBox = new JComboBox<>(comboBoxModel);
        branchComboBox.setPreferredSize(new Dimension(300, 30));
        branchComboBox.setFont(new Font("Arial", Font.PLAIN, 14));
        branchComboBox.addActionListener(e -> {
            String selectedBranchName = (String) branchComboBox.getSelectedItem();
            if (selectedBranchName != null) {
                loadBranchDetails(selectedBranchName);
            }
        });

        JLabel comboLabel = new JLabel("Select Branch:");
        comboLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        addField(gbc, comboLabel, branchComboBox, 0);

        branchNameField = createTextField();
        cityField = createTextField();
        addressField = createTextField();
        phoneField = createTextField();
        isActiveCheckBox = new JCheckBox("Active");

        addField(gbc, new JLabel("Branch Name:"), branchNameField, 1);
        addField(gbc, new JLabel("City:"), cityField, 2);
        addField(gbc, new JLabel("Address:"), addressField, 3);
        addField(gbc, new JLabel("Phone:"), phoneField, 4);
        addField(gbc, new JLabel("Active:"), isActiveCheckBox, 5);

        saveButton = createSaveButton();
        clearButton = createClearButton();

        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(saveButton);
        buttonPanel.add(clearButton);
        add(buttonPanel, gbc);
    }

    private JTextField createTextField() {
        JTextField textField = new JTextField(15);
        textField.setFont(new Font("Arial", Font.PLAIN, 14));
        textField.setPreferredSize(new Dimension(300, 30));
        textField.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1));
        return textField;
    }

    private JButton createSaveButton() {
        JButton saveButton = new JButton("Save Changes");
        saveButton.setFont(new Font("Arial", Font.BOLD, 16));
        saveButton.setBackground(new Color(0, 102, 204));
        saveButton.setForeground(Color.WHITE);
        saveButton.setFocusPainted(false);
        saveButton.setPreferredSize(new Dimension(150, 40));
        saveButton.addActionListener(e -> saveBranchDetails());
        return saveButton;
    }

    private JButton createClearButton() {
        JButton clearButton = new JButton("Clear Fields");
        clearButton.setFont(new Font("Arial", Font.BOLD, 16));
        clearButton.setBackground(new Color(220, 53, 69));
        clearButton.setForeground(Color.WHITE);
        clearButton.setFocusPainted(false);
        clearButton.setPreferredSize(new Dimension(150, 40));
        clearButton.addActionListener(e -> clearFields());
        return clearButton;
    }

    private void addField(GridBagConstraints gbc, JComponent label, JComponent field, int row) {
        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.anchor = GridBagConstraints.EAST;
        add(label, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        add(field, gbc);
    }

    private void loadBranchNamesFromDatabase() {
        ArrayList<String> branchNames = BranchModel.getBranchNamesFromDatabase();
        comboBoxModel.removeAllElements();
        for (String branchName : branchNames) {
            comboBoxModel.addElement(branchName);
        }
    }

    private void loadBranchDetails(String branchName) {
        BranchModel.BranchDetails branchDetails = BranchModel.getBranchDetailsByName(branchName);

        if (branchDetails != null) {
            branchNameField.setText(branchDetails.getBranchName());
            cityField.setText(branchDetails.getCity());
            addressField.setText(branchDetails.getAddress());
            phoneField.setText(branchDetails.getPhone());
            isActiveCheckBox.setSelected(branchDetails.isActive());
            branchNameField.setEditable(false);
        }
    }

    private void saveBranchDetails() {
        String branchName = branchNameField.getText();
        String city = cityField.getText();
        String address = addressField.getText();
        String phone = phoneField.getText();
        boolean isActive = isActiveCheckBox.isSelected();

        boolean success = BranchModel.updateBranch(branchName, city, address, phone, isActive);
        if (success) {
            JOptionPane.showMessageDialog(this, "Branch details updated successfully!");
            clearFields();
        } else {
            JOptionPane.showMessageDialog(this, "Error updating branch details.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void clearFields() {
        branchNameField.setText("");
        cityField.setText("");
        addressField.setText("");
        phoneField.setText("");
        isActiveCheckBox.setSelected(false);
    }
}
