package controller;

import model.ManagerModel;
import view.AdminManager.AdminManagerPanel;
import view.AdminManager.DetailAdminManager;

import javax.swing.*;
import java.awt.*;

public class AdminManagerController {
    private AdminManagerPanel adminManagerPanel;
    private ManagerModel managerModel;
    private DetailAdminManager detailAdminManager;

    public AdminManagerController() {
        managerModel = new ManagerModel();
        adminManagerPanel = new AdminManagerPanel();
        detailAdminManager = new DetailAdminManager();
        initController();
    }

    private void initController() {
        adminManagerPanel.getLoginButton().addActionListener(e -> {
            String username = adminManagerPanel.getUsernameField().getText();
            String password = new String(adminManagerPanel.getPasswordField().getPassword());
            handleLogin(username, password);
        });

        adminManagerPanel.getChangePasswordButton().addActionListener(e -> {
            String username = adminManagerPanel.getUsernameField().getText();
            String password = new String(adminManagerPanel.getPasswordField().getPassword());

            // Validate credentials before proceeding to Change Password panel
            if (username.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(adminManagerPanel,
                        "Please enter your username and password before changing the password.",
                        "Missing Information",
                        JOptionPane.WARNING_MESSAGE);
            } else if (managerModel.validateManager(username, password)) {
                // Credentials are valid, proceed to Change Password panel
                adminManagerPanel.showChangePasswordPanel();
            } else {
                // Credentials are invalid
                JOptionPane.showMessageDialog(adminManagerPanel,
                        "Invalid username or password. Please try again.",
                        "Authentication Failed",
                        JOptionPane.ERROR_MESSAGE);
            }
        });

        adminManagerPanel.getSaveButton().addActionListener(e -> {
            String username = adminManagerPanel.getUsernameField().getText();
            String newPassword = new String(adminManagerPanel.getNewPasswordField().getPassword());
            handleChangePassword(username, newPassword);
        });

        adminManagerPanel.getBackButton().addActionListener(e -> adminManagerPanel.showLoginPanel());
    }

    public void show() {
        JFrame frame = new JFrame("Admin Manager");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setSize(screenSize);
        frame.setContentPane(adminManagerPanel);
        frame.setVisible(true);
    }

    private void handleLogin(String username, String password) {
        if (managerModel.validateManager(username, password)) {
            JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(adminManagerPanel);
            frame.setContentPane(detailAdminManager);
            frame.revalidate();
            frame.repaint();
        } else {
            JOptionPane.showMessageDialog(adminManagerPanel,
                    "Invalid username or password.", "Login Failed", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void handleChangePassword(String username, String newPassword) {
        if (username.isEmpty() || newPassword.isEmpty()) {
            JOptionPane.showMessageDialog(adminManagerPanel,
                    "Username and new password cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (managerModel.isManagerExists(username)) {
            if (managerModel.updatePassword(username, newPassword)) {
                JOptionPane.showMessageDialog(adminManagerPanel,
                        "Password updated successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                adminManagerPanel.showLoginPanel();
            } else {
                JOptionPane.showMessageDialog(adminManagerPanel,
                        "Failed to update password.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(adminManagerPanel,
                    "Manager with this username does not exist.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
