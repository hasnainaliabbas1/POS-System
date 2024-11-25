package view.AdminManager;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class AdminManagerPanel extends JPanel {
    private CardLayout cardLayout;
    private JPanel loginPanel, changePasswordPanel;
    private JTextField usernameField;
    private JPasswordField passwordField, newPasswordField;
    private JButton loginButton, changePasswordButton, logoutButton, saveButton, backButton;
    private LoginListener loginListener;
    private ChangePasswordListener changePasswordListener;

    public AdminManagerPanel() {
        cardLayout = new CardLayout();
        setLayout(cardLayout);

        loginPanel = createLoginPanel();
        changePasswordPanel = createChangePasswordPanel();

        add(loginPanel, "Login");
        add(changePasswordPanel, "ChangePassword");

        cardLayout.show(this, "Login");
    }

    private JPanel createLoginPanel() {
        JPanel panel = createBlueBorderedBox();
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        usernameField = new JTextField(15);
        addField(panel, "Username:", usernameField, gbc, 0);

        passwordField = new JPasswordField(15);
        addField(panel, "Password:", passwordField, gbc, 1);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        buttonPanel.setBackground(Color.WHITE);

        changePasswordButton = new JButton("Change Password");
        styleChangePasswordButton(changePasswordButton);
        buttonPanel.add(changePasswordButton);

        loginButton = new JButton("Login");
        styleButton(loginButton);
        buttonPanel.add(loginButton);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(buttonPanel, gbc);

        return wrapInFullScreenPanel(panel);
    }

    private JPanel createChangePasswordPanel() {
        JPanel panel = createBlueBorderedBox();
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        newPasswordField = new JPasswordField(15);
        addField(panel, "New Password:", newPasswordField, gbc, 0);

        saveButton = new JButton("Save");
        styleButton(saveButton);

        backButton = new JButton("Back");
        styleBackButton(backButton);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.add(backButton);
        buttonPanel.add(saveButton);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(buttonPanel, gbc);

        return wrapInFullScreenPanel(panel);
    }

    private JPanel createBlueBorderedBox() {
        JPanel borderedPanel = new JPanel(new GridBagLayout());
        borderedPanel.setBorder(new LineBorder(new Color(0, 102, 204), 5));
        borderedPanel.setBackground(Color.WHITE);
        return borderedPanel;
    }

    private JPanel wrapInFullScreenPanel(JPanel innerPanel) {
        JPanel outerPanel = new JPanel(new GridBagLayout());
        outerPanel.setBackground(Color.WHITE);
        outerPanel.add(innerPanel);
        return outerPanel;
    }

    private void addField(JPanel panel, String label, JTextField field, GridBagConstraints gbc, int row) {
        JLabel jLabel = new JLabel(label);
        jLabel.setFont(new Font("Arial", Font.BOLD, 14));
        jLabel.setForeground(new Color(0, 102, 204));

        gbc.gridx = 0;
        gbc.gridy = row;
        panel.add(jLabel, gbc);

        gbc.gridx = 1;
        panel.add(field, gbc);
    }

    private void styleButton(JButton button) {
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setBackground(new Color(0, 102, 204));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
    }

    private void styleChangePasswordButton(JButton button) {
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setBackground(new Color(50, 205, 50)); // Green for "Change Password"
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
    }

    private void styleBackButton(JButton button) {
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setBackground(new Color(255, 69, 0)); // Orange for "Back" button
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
    }

    public void showChangePasswordPanel() {
        cardLayout.show(this, "ChangePassword");
    }

    public void showLoginPanel() {
        cardLayout.show(this, "Login");
    }

    public JTextField getUsernameField() {
        return usernameField;
    }

    public JPasswordField getPasswordField() {
        return passwordField;
    }

    public JPasswordField getNewPasswordField() {
        return newPasswordField;
    }

    public JButton getLoginButton() {
        return loginButton;
    }

    public JButton getChangePasswordButton() {
        return changePasswordButton;
    }

    public JButton getSaveButton() {
        return saveButton;
    }

    public JButton getBackButton() {
        return backButton;
    }

    public interface LoginListener {
       //void onLogin(String username, String password);
    }

    public interface ChangePasswordListener {
      //  void onChangePassword(String username, String newPassword);
    }

//    public void addLoginListener(LoginListener listener) {
//        this.loginListener = listener;
//    }
//
//    public void addChangePasswordListener(ChangePasswordListener listener) {
//        this.changePasswordListener = listener;
//    }


}
