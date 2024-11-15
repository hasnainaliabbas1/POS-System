package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import model.BranchModel;

public class UpdateBranchPanel extends JPanel {

    private JComboBox<String> branchComboBox;
    private DefaultComboBoxModel<String> comboBoxModel;
    private JTextField branchNameField, cityField, addressField, phoneField;
    private JCheckBox isActiveCheckBox;
    private JButton saveButton;

    public UpdateBranchPanel() {
        setLayout(new BorderLayout());
        initializeComponents();
        loadBranchNamesFromDatabase();
    }

    private void initializeComponents() {
        comboBoxModel = new DefaultComboBoxModel<>();
        branchComboBox = new JComboBox<>(comboBoxModel);
        branchComboBox.addActionListener(e -> {
            String selectedBranchName = (String) branchComboBox.getSelectedItem();
            if (selectedBranchName != null) {
                loadBranchDetails(selectedBranchName);
            }
        });

        // Panel for selecting branch
        JPanel comboPanel = new JPanel();
        comboPanel.setLayout(new FlowLayout());
        comboPanel.add(new JLabel("Select Branch:"));
        comboPanel.add(branchComboBox);
        add(comboPanel, BorderLayout.NORTH);

        // Panel for branch detail fields
        JPanel detailPanel = new JPanel();
        detailPanel.setLayout(new GridLayout(5, 2, 10, 10));

        branchNameField = new JTextField(20);
        cityField = new JTextField(20);
        addressField = new JTextField(20);
        phoneField = new JTextField(20);
        isActiveCheckBox = new JCheckBox("Is Active");

        detailPanel.add(new JLabel("Branch Name:"));
        detailPanel.add(branchNameField);

        detailPanel.add(new JLabel("City:"));
        detailPanel.add(cityField);

        detailPanel.add(new JLabel("Address:"));
        detailPanel.add(addressField);

        detailPanel.add(new JLabel("Phone:"));
        detailPanel.add(phoneField);

        detailPanel.add(isActiveCheckBox);

        add(detailPanel, BorderLayout.CENTER);

        // Save button
        saveButton = new JButton("Save");
        saveButton.addActionListener(e -> saveBranchDetails());
        add(saveButton, BorderLayout.SOUTH);

        // Initially hide the detail panel and save button
        detailPanel.setVisible(false);
        saveButton.setVisible(false);
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

            branchNameField.setEditable(false); // Make branch name read-only
            ((JPanel) branchNameField.getParent()).setVisible(true);
            saveButton.setVisible(true);
        }
    }

    private void saveBranchDetails() {
        String branchName = branchNameField.getText();
        String city = cityField.getText();
        String address = addressField.getText();
        String phone = phoneField.getText();
        boolean isActive = isActiveCheckBox.isSelected();

        // Update branch details in the database
        boolean success = BranchModel.updateBranch(branchName, city, address, phone, isActive);
        if (success) {
            JOptionPane.showMessageDialog(this, "Branch details updated successfully!");
        } else {
            JOptionPane.showMessageDialog(this, "Error updating branch details.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
