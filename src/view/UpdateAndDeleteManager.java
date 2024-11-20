package view;

import model.ManagerModel;

import javax.swing.*;
import java.awt.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UpdateAndDeleteManager extends JPanel {

    private JTable managerTable;
    private ManagerModel managerModel;
    private JPanel updatePanel;

    public UpdateAndDeleteManager() {
        managerModel = new ManagerModel();
        setLayout(new BorderLayout());

        // Back button
        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> {
            // Handle navigation to the previous screen
            updatePanel.setVisible(false); // Hide the update panel when Back button is clicked
        });
        add(backButton, BorderLayout.NORTH);

        // Create the table
        managerTable = new JTable();
        loadManagerDataIntoTable(); // Populate the table with data
        JScrollPane scrollPane = new JScrollPane(managerTable);
        add(scrollPane, BorderLayout.CENTER);

        // Create the Update panel (hidden initially)
        updatePanel = createUpdatePanel();
        add(updatePanel, BorderLayout.SOUTH);
    }

    // Load manager data into the JTable
    private void loadManagerDataIntoTable() {
        String[] columnNames = {"Manager ID", "Branch Code", "Name", "Email", "Address", "Salary", "Experience", "Remarks", "Update", "Delete"};

        // Fetch manager data from the database
        ArrayList<ManagerModel.ManagerDetails> managerList = managerModel.getManagerData();

        // Populate data into an Object array
        Object[][] data = new Object[managerList.size()][columnNames.length];
        for (int i = 0; i < managerList.size(); i++) {
            ManagerModel.ManagerDetails manager = managerList.get(i);
            data[i][0] = manager.getManagerId();
            data[i][1] = manager.getBranchCode();
            data[i][2] = manager.getName();
            data[i][3] = manager.getEmail();
            data[i][4] = manager.getAddress();
            data[i][5] = manager.getSalary();
            data[i][6] = manager.getExperience();
            data[i][7] = manager.getRemarks();
            data[i][8] = "Update"; // Set initial label for Update
            data[i][9] = "Delete"; // Set initial label for Delete
        }

        DefaultTableModel model = new DefaultTableModel(data, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                // Make only Update and Delete columns editable
                return column == 8 || column == 9;
            }
        };
        managerTable.setModel(model);

        // Add button renderers for the Update and Delete columns
        TableColumn updateColumn = managerTable.getColumnModel().getColumn(8);
        updateColumn.setCellRenderer(new ButtonRenderer("Update"));
        updateColumn.setCellEditor(new ButtonEditor(new JCheckBox(), true));

        TableColumn deleteColumn = managerTable.getColumnModel().getColumn(9);
        deleteColumn.setCellRenderer(new ButtonRenderer("Delete"));
        deleteColumn.setCellEditor(new ButtonEditor(new JCheckBox(), false));
    }

    // Create the update form panel
    private JPanel createUpdatePanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createTitledBorder("Update Manager Details"));
        panel.setVisible(false); // Initially hidden

        // Form fields for manager data
        JTextField managerIdField = new JTextField(15);
        JTextField managerNameField = new JTextField(15);
        JTextField emailField = new JTextField(15);
        JTextField salaryField = new JTextField(15);
        JTextField experienceField = new JTextField(15);
        JTextField addressField = new JTextField(15);
        JTextField remarksField = new JTextField(15);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(new JLabel("Manager ID:"), gbc);
        gbc.gridx = 1;
        panel.add(managerIdField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(new JLabel("Name:"), gbc);
        gbc.gridx = 1;
        panel.add(managerNameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(new JLabel("Email:"), gbc);
        gbc.gridx = 1;
        panel.add(emailField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(new JLabel("Salary:"), gbc);
        gbc.gridx = 1;
        panel.add(salaryField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        panel.add(new JLabel("Experience:"), gbc);
        gbc.gridx = 1;
        panel.add(experienceField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        panel.add(new JLabel("Address:"), gbc);
        gbc.gridx = 1;
        panel.add(addressField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        panel.add(new JLabel("Remarks:"), gbc);
        gbc.gridx = 1;
        panel.add(remarksField, gbc);

        // Save button
        JButton saveButton = new JButton("Save Changes");
        saveButton.addActionListener(e -> {
            int managerId = Integer.parseInt(managerIdField.getText());
            String name = managerNameField.getText();
            String email = emailField.getText();
            double salary = 0;
            int experience = 0;
            String address = addressField.getText();
            String remarks = remarksField.getText();

            try {
                salary = Double.parseDouble(salaryField.getText());
                experience = Integer.parseInt(experienceField.getText());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Please enter valid salary and experience.");
                return;
            }

            // Validate email format
            if (!isValidEmail(email)) {
                JOptionPane.showMessageDialog(this, "Please enter a valid email.");
                return;
            }

            if (managerModel.updateManager(managerId, name, email, salary, experience, address, remarks)) {
                JOptionPane.showMessageDialog(this, "Manager details updated successfully.");
                loadManagerDataIntoTable(); // Reload the table
                updatePanel.setVisible(false); // Hide the update panel after saving
            } else {
                JOptionPane.showMessageDialog(this, "Error updating manager.");
            }
        });

        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.gridwidth = 2;
        panel.add(saveButton, gbc);

        return panel;
    }

    // Renderer for Update and Delete buttons
    private class ButtonRenderer extends JButton implements TableCellRenderer {
        private String label;

        public ButtonRenderer(String label) {
            this.label = label;
        }

        public Component getTableCellRendererComponent(JTable table, Object value,
                                                       boolean isSelected, boolean hasFocus, int row, int column) {
            setText(label); // Use the provided label for button text
            return this;
        }
    }

    private class ButtonEditor extends DefaultCellEditor {
        private JButton button;
        private String label;
        private boolean isUpdate;

        public ButtonEditor(JCheckBox checkBox, boolean isUpdate) {
            super(checkBox);
            this.isUpdate = isUpdate;
            button = new JButton();
            button.addActionListener(e -> handleButtonClick());
        }

        public Component getTableCellEditorComponent(JTable table, Object value,
                                                     boolean isSelected, int row, int column) {
            label = (value == null) ? "" : value.toString();
            button.setText(label);
            return button;
        }

        private void handleButtonClick() {
            int row = managerTable.getSelectedRow();
            if (isUpdate) {
                // Handle update button click
                int managerId = (Integer) managerTable.getValueAt(row, 0);
                String name = (String) managerTable.getValueAt(row, 2);
                String email = (String) managerTable.getValueAt(row, 3);
                String address = (String) managerTable.getValueAt(row, 4);
                double salary = (Double) managerTable.getValueAt(row, 5);
                int experience = (Integer) managerTable.getValueAt(row, 6);
                String remarks = (String) managerTable.getValueAt(row, 7);

                updatePanel.setVisible(true);
            } else {
                int confirmation = JOptionPane.showConfirmDialog(UpdateAndDeleteManager.this,
                        "Are you sure you want to delete this manager?", "Delete Confirmation",
                        JOptionPane.YES_NO_OPTION);

                if (confirmation == JOptionPane.YES_OPTION) {
                    int managerId = (Integer) managerTable.getValueAt(row, 0);
                    managerModel.deleteManager(managerId);
                    loadManagerDataIntoTable();
                }
            }
        }
    }

    // Simple email validation
    private boolean isValidEmail(String email) {
        String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
