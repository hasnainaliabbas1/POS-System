package view;

import javax.swing.*;
import java.awt.*;
import controller.LoginController;

public class LoginView {
    public LoginView() {
        JFrame frame = new JFrame("Login");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(240, 240, 240));

        GridBagConstraints constraints = new GridBagConstraints();

        JLabel titleLabel = new JLabel("Login System");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(new Color(51, 51, 51));
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        constraints.insets = new Insets(20, 20, 10, 20);
        panel.add(titleLabel, constraints);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setForeground(new Color(51, 51, 51));
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.insets = new Insets(10, 20, 5, 5);
        panel.add(emailLabel, constraints);

        JTextField emailField = new JTextField(30);
        constraints.gridx = 1;
        constraints.gridy = 1;
        constraints.insets = new Insets(10, 5, 5, 20);
        panel.add(emailField, constraints);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setForeground(new Color(51, 51, 51));
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.insets = new Insets(5, 20, 5, 5);
        panel.add(passwordLabel, constraints);

        JPasswordField passwordField = new JPasswordField(30);
        constraints.gridx = 1;
        constraints.gridy = 2;
        constraints.insets = new Insets(5, 5, 5, 20);
        panel.add(passwordField, constraints);

        JButton loginButton = new JButton("Login");
        loginButton.setBackground(new Color(46, 139, 87));
        loginButton.setForeground(Color.WHITE);
        loginButton.setFont(new Font("Arial", Font.BOLD, 18));
        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.gridwidth = 2;
        constraints.insets = new Insets(10, 20, 20, 20);
        panel.add(loginButton, constraints);

        JButton changePasswordButton = new JButton("Change Password");
        changePasswordButton.setBackground(new Color(46, 139, 87));
        changePasswordButton.setForeground(Color.WHITE);
        changePasswordButton.setFont(new Font("Arial", Font.BOLD, 18));
        constraints.gridx = 0;
        constraints.gridy = 4;
        constraints.gridwidth = 2;
        panel.add(changePasswordButton, constraints);

        frame.add(panel, BorderLayout.CENTER);
        frame.setSize(800, 500);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        // Login Button Action
        loginButton.addActionListener(e -> {
            String email = emailField.getText();
            String password = new String(passwordField.getPassword());
            LoginController controller = new LoginController();
            if (controller.authenticate(email, password)) {
                JOptionPane.showMessageDialog(frame, "Login Successful!");
                new DashboardView();
                // Proceed to dashboard
            } else {
                JOptionPane.showMessageDialog(frame, "Invalid Credentials");
            }
        });

        // Change Password Button Action
        changePasswordButton.addActionListener(e -> {
            String email = emailField.getText();
            if (email.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Please enter your email to change the password!");
                return;
            }

            // Call the Change Password Dialog
            changePasswordDialog(frame, email);
        });
    }

    private void changePasswordDialog(JFrame parentFrame, String email) {
        JDialog dialog = new JDialog(parentFrame, "Change Password", true);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.setLayout(new BorderLayout());

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(240, 240, 240));

        GridBagConstraints constraints = new GridBagConstraints();

        JLabel currentPasswordLabel = new JLabel("Current Password:");
        currentPasswordLabel.setForeground(new Color(51, 51, 51));
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.insets = new Insets(10, 10, 5, 5);
        panel.add(currentPasswordLabel, constraints);

        JPasswordField currentPasswordField = new JPasswordField(30);
        constraints.gridx = 1;
        constraints.gridy = 0;
        constraints.insets = new Insets(10, 5, 5, 10);
        panel.add(currentPasswordField, constraints);

        JLabel newPasswordLabel = new JLabel("New Password:");
        newPasswordLabel.setForeground(new Color(51, 51, 51));
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.insets = new Insets(5, 10, 5, 5);
        panel.add(newPasswordLabel, constraints);

        JPasswordField newPasswordField = new JPasswordField(30);
        constraints.gridx = 1;
        constraints.gridy = 1;
        constraints.insets = new Insets(5, 5, 5, 10);
        panel.add(newPasswordField, constraints);

        JLabel confirmNewPasswordLabel = new JLabel("Confirm New Password:");
        confirmNewPasswordLabel.setForeground(new Color(51, 51, 51));
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.insets = new Insets(5, 10, 10, 5);
        panel.add(confirmNewPasswordLabel, constraints);

        JPasswordField confirmNewPasswordField = new JPasswordField(30);
        constraints.gridx = 1;
        constraints.gridy = 2;
        constraints.insets = new Insets(5, 5, 10, 10);
        panel.add(confirmNewPasswordField, constraints);

        JButton saveButton = new JButton("Save");
        saveButton.setBackground(new Color(46, 139, 87));
        saveButton.setForeground(Color.WHITE);
        saveButton.setFont(new Font("Arial", Font.BOLD, 18));
        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.gridwidth = 2;
        constraints.insets = new Insets(10, 10, 10, 10);
        panel.add(saveButton, constraints);

        dialog.add(panel, BorderLayout.CENTER);
        dialog.pack();
        dialog.setLocationRelativeTo(parentFrame);
        dialog.setVisible(true);

        saveButton.addActionListener(e -> {
            String currentPassword = new String(currentPasswordField.getPassword());
            String newPassword = new String(newPasswordField.getPassword());
            String confirmNewPassword = new String(confirmNewPasswordField.getPassword());

            if (newPassword.equals(confirmNewPassword)) {
                LoginController controller = new LoginController();
                boolean isPasswordChanged = controller.changePassword(email, currentPassword, newPassword);
                if (isPasswordChanged) {
                    JOptionPane.showMessageDialog(dialog, "Password changed successfully!");
                    dialog.dispose();
                } else {
                    JOptionPane.showMessageDialog(dialog, "Failed to change password. Please check your current password.");
                }
            } else {
                JOptionPane.showMessageDialog(dialog, "New password and confirmation do not match.");
            }
        });
    }
}