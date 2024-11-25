package view.AdminManager;

import javax.swing.*;
import java.awt.*;

public class AddDataEntryOperatorPanel extends JPanel {

    public AddDataEntryOperatorPanel() {
        setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Add Data Entry Operator", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
        add(titleLabel, BorderLayout.NORTH);

        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(6, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        formPanel.add(new JLabel("Name:"));
        JTextField nameField = new JTextField();
        formPanel.add(nameField);

        formPanel.add(new JLabel("Employee Number:"));
        JTextField empNoField = new JTextField();
        formPanel.add(empNoField);

        formPanel.add(new JLabel("Branch Code:"));
        JTextField branchCodeField = new JTextField();
        formPanel.add(branchCodeField);

        formPanel.add(new JLabel("Password:"));
        JTextField passwordField = new JTextField("Password_123");
        formPanel.add(passwordField);

        formPanel.add(new JLabel("Salary:"));
        JTextField salaryField = new JTextField();
        formPanel.add(salaryField);

        JButton saveButton = new JButton("Save");
        JButton clearButton = new JButton("Clear");

        formPanel.add(saveButton);
        formPanel.add(clearButton);

        add(formPanel, BorderLayout.CENTER);
    }
}