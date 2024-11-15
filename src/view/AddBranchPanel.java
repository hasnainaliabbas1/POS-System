package view;

import model.BranchModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AddBranchPanel extends JPanel {
    private JTextField branchCodeField, branchNameField, cityField, addressField, phoneField, creationDateField;
    private JCheckBox activeBox;
    private BranchModel branchModel;
    private CardLayout cardLayout;
    private JPanel cardPanel;

    public AddBranchPanel(CardLayout cardLayout, JPanel cardPanel) {
        this.cardLayout = cardLayout;
        this.cardPanel = cardPanel;
        branchModel = new BranchModel();

        setLayout(new GridBagLayout());
        setBorder(BorderFactory.createTitledBorder("Create Branch"));
        setBackground(Color.WHITE);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(20, 20, 20, 20);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        branchCodeField = createTextField();
        branchNameField = createTextField();
        cityField = createTextField();
        addressField = createTextField();
        phoneField = createTextField();
        creationDateField = createTextField();
        activeBox = new JCheckBox("Active", true);

        String currentDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        creationDateField.setText(currentDate);
        creationDateField.setEditable(false);

        addField(gbc, new JLabel("Branch Code:"), branchCodeField, 0);
        addField(gbc, new JLabel("Branch Name:"), branchNameField, 1);
        addField(gbc, new JLabel("City:"), cityField, 2);
        addField(gbc, new JLabel("Address:"), addressField, 3);
        addField(gbc, new JLabel("Phone:"), phoneField, 4);
        addField(gbc, new JLabel("Date of Creation:"), creationDateField, 5);
        addField(gbc, new JLabel("Active:"), activeBox, 6);

        JButton saveBranchButton = createSaveButton();
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(saveBranchButton, gbc);

        JButton backButton = createBackButton();
        gbc.gridx = 1;
        gbc.gridy = 9;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.SOUTHEAST;
        gbc.insets = new Insets(20, 0, 20, 20); // Add some padding
        add(backButton, gbc);
    }

    private JTextField createTextField() {
        JTextField textField = new JTextField(15);
        textField.setFont(new Font("Arial", Font.PLAIN, 14));
        textField.setPreferredSize(new Dimension(400, 30));
        textField.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1));
        return textField;
    }

    private JButton createSaveButton() {
        JButton saveButton = new JButton("Save Branch");
        saveButton.setFont(new Font("Arial", Font.BOLD, 16));
        saveButton.setBackground(new Color(0, 102, 204));
        saveButton.setForeground(Color.WHITE);
        saveButton.setFocusPainted(false);
        saveButton.setPreferredSize(new Dimension(220, 50));
        saveButton.addActionListener(new SaveBranchListener());
        return saveButton;
    }

    private JButton createBackButton() {
        JButton backButton = new JButton("Back");
        backButton.setFont(new Font("Arial", Font.BOLD, 14));
        backButton.setBackground(new Color(220, 53, 69));
        backButton.setForeground(Color.WHITE);
        backButton.setFocusPainted(false);
        backButton.setPreferredSize(new Dimension(100, 40));
        backButton.addActionListener(e -> cardLayout.show(cardPanel, "mainPanel"));
        return backButton;
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

    private class SaveBranchListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String code = branchCodeField.getText().trim();
            String name = branchNameField.getText().trim();
            String city = cityField.getText().trim();
            String address = addressField.getText().trim();
            String phone = phoneField.getText().trim();
            String creationDate = creationDateField.getText().trim();
            boolean isActive = activeBox.isSelected();

            if (code.isEmpty() || name.isEmpty() || city.isEmpty() || address.isEmpty() || phone.isEmpty() || creationDate.isEmpty()) {
                JOptionPane.showMessageDialog(AddBranchPanel.this, "Please fill in all fields.", "Missing Information", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (branchModel.saveBranch(code, name, city, isActive, address, phone)) {
                JOptionPane.showMessageDialog(AddBranchPanel.this, "Branch saved successfully!");
                clearFields();
            } else {
                JOptionPane.showMessageDialog(AddBranchPanel.this, "Failed to save branch.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        private void clearFields() {
            branchCodeField.setText("");
            branchNameField.setText("");
            cityField.setText("");
            addressField.setText("");
            phoneField.setText("");
            activeBox.setSelected(true);
            String currentDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
            creationDateField.setText(currentDate);
        }
    }
}
