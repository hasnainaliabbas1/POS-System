package view;

import model.ManagerModel;
import model.BranchModel;
import model.BranchModel.BranchDetails;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BranchManagerPanel extends JPanel {
    private CardLayout cardLayout;
    private JPanel cardPanel;
    private JComboBox<String> branchComboBox;
    private JTextField branchCodeField, managerNameField, emailField, addressField, salaryField, experienceField, remarksField;
    private JButton saveButton, updateDeleteButton;
    private BranchModel branchModel;

    public BranchManagerPanel() {
        branchModel = new BranchModel(); // Initialize the BranchModel
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);
        JPanel mainPanel = createMainPanel();
        cardPanel.add(mainPanel, "BranchManagerPanel");

        JPanel updateDeletePanel = new UpdateAndDeleteManager();
        cardPanel.add(updateDeletePanel, "UpdateDeletePanel");
        add(cardPanel, BorderLayout.CENTER);

        cardLayout.show(cardPanel, "BranchManagerPanel");
    }

    private JPanel createMainPanel() {
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.WHITE);
        JPanel titlePanel = new JPanel(new BorderLayout());
        titlePanel.setBackground(Color.WHITE);

        JLabel titleLabel = new JLabel("Activate Branches", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titlePanel.add(titleLabel, BorderLayout.CENTER);

        updateDeleteButton = createButton("Update/Delete", Color.LIGHT_GRAY);
        updateDeleteButton.setPreferredSize(new Dimension(150, 40));
        titlePanel.add(updateDeleteButton, BorderLayout.EAST);
        updateDeleteButton.addActionListener(new UpdateDeleteButtonListener());

        mainPanel.add(titlePanel, BorderLayout.NORTH);

        // Branch Selection Panel
        JPanel branchSelectionPanel = new JPanel(new FlowLayout());
        branchSelectionPanel.setBackground(Color.WHITE);
        JLabel branchLabel = new JLabel("Select Branch:");
        branchLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        branchComboBox = new JComboBox<>(getActiveBranchNames()); // Load active branches
        branchComboBox.setPreferredSize(new Dimension(300, 30));
        branchComboBox.addActionListener(e -> onBranchSelected());
        branchSelectionPanel.add(branchLabel);
        branchSelectionPanel.add(branchComboBox);
        mainPanel.add(branchSelectionPanel, BorderLayout.CENTER);

        JPanel managerDetailsPanel = createManagerDetailsPanel();
        mainPanel.add(managerDetailsPanel, BorderLayout.SOUTH);

        return mainPanel;
    }

    private JPanel createManagerDetailsPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createTitledBorder("Add Manager Details"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        branchCodeField = createTextField();
        addField(panel, "Branch Code:", branchCodeField, gbc, 0);

        managerNameField = createTextField();
        addField(panel, "Manager Name:", managerNameField, gbc, 1);

        emailField = createTextField();
        addField(panel, "Email:", emailField, gbc, 2);

        addressField = createTextField();
        addField(panel, "Address:", addressField, gbc, 3);

        salaryField = createTextField();
        addField(panel, "Salary:", salaryField, gbc, 4);

        experienceField = createTextField();
        addField(panel, "Experience:", experienceField, gbc, 5);

        remarksField = createTextField();
        addField(panel, "Remarks:", remarksField, gbc, 6);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.setBackground(Color.WHITE);
        saveButton = createButton("Save", Color.BLUE);
        saveButton.addActionListener(new SaveButtonListener());
        buttonPanel.add(saveButton);

        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(buttonPanel, gbc);

        return panel;
    }

    private void addField(JPanel panel, String labelText, JTextField field, GridBagConstraints gbc, int row) {
        JLabel label = new JLabel(labelText);
        label.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.anchor = GridBagConstraints.EAST;
        panel.add(label, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(field, gbc);
    }

    private JTextField createTextField() {
        JTextField textField = new JTextField(15);
        textField.setFont(new Font("Arial", Font.PLAIN, 14));
        textField.setPreferredSize(new Dimension(300, 30));
        textField.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
        return textField;
    }

    private JButton createButton(String text, Color color) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setPreferredSize(new Dimension(120, 40));
        button.setBackground(color);
        button.setForeground(Color.WHITE);
        return button;
    }

    private String[] getActiveBranchNames() {
        return BranchModel.getActiveBranchNamesFromDatabase().toArray(new String[0]);
    }

    private void onBranchSelected() {
        String selectedBranch = (String) branchComboBox.getSelectedItem();
        if (selectedBranch != null) {
            BranchDetails details = BranchModel.getBranchDetailsByName(selectedBranch);
            if (details != null) {
                branchCodeField.setText(details.getBranchCode());
                addressField.setText(details.getAddress());
            }
        }
    }

    private class SaveButtonListener implements ActionListener {
        private ManagerModel managerModel = new ManagerModel();

        @Override
        public void actionPerformed(ActionEvent e) {
            String branchCode = branchCodeField.getText();
            String managerName = managerNameField.getText();
            String email = emailField.getText();
            String address = addressField.getText();
            String salaryText = salaryField.getText();
            String experienceText = experienceField.getText();
            String remarks = remarksField.getText();

            if (branchCode.isEmpty() || managerName.isEmpty() || email.isEmpty() || salaryText.isEmpty() || experienceText.isEmpty()) {
                JOptionPane.showMessageDialog(BranchManagerPanel.this,
                        "Please fill in all required fields (Branch Code, Manager Name, Email, Salary, Experience).",
                        "Validation Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                double salary = Double.parseDouble(salaryText);
                int experience = Integer.parseInt(experienceText);
                boolean isSaved = managerModel.saveManager(branchCode, managerName, email, address, salary, experience, remarks);

                if (isSaved) {
                    JOptionPane.showMessageDialog(BranchManagerPanel.this,
                            "Manager details saved successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    clearFields();
                } else {
                    JOptionPane.showMessageDialog(BranchManagerPanel.this,
                            "Failed to save manager details. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(BranchManagerPanel.this,
                        "Invalid input for Salary or Experience. Please enter numeric values.",
                        "Input Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public class UpdateDeleteButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Show the UpdateDeletePanel
            cardLayout.show(cardPanel, "UpdateDeletePanel");
        }
    }

    private void clearFields() {
        branchCodeField.setText("");
        managerNameField.setText("");
        emailField.setText("");
        addressField.setText("");
        salaryField.setText("");
        experienceField.setText("");
        remarksField.setText("");
    }
}
