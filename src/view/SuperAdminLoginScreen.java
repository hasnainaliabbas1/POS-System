package view;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class SuperAdminLoginScreen extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;

    public SuperAdminLoginScreen() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(screenSize.width, screenSize.height);
        setUndecorated(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setBackground(Color.WHITE);

        JPanel borderedPanel = new JPanel();
        borderedPanel.setBorder(new LineBorder(new Color(0, 102, 204), 5));
        borderedPanel.setBackground(Color.WHITE);
        borderedPanel.setLayout(new GridBagLayout());

        Color buttonColor = new Color(0, 102, 204);

        JLabel usernameLabel = new JLabel("Username:");
        usernameField = new JTextField(15);
        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField(15);

        usernameLabel.setFont(new Font("Arial", Font.BOLD, 18));
        usernameLabel.setForeground(buttonColor);
        passwordLabel.setFont(new Font("Arial", Font.BOLD, 18));
        passwordLabel.setForeground(buttonColor);

        loginButton = new JButton("Login");
        loginButton.setFont(new Font("Arial", Font.BOLD, 20));
        loginButton.setBackground(buttonColor);
        loginButton.setForeground(Color.WHITE);
        loginButton.setFocusPainted(false);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        gbc.gridx = 0;
        gbc.gridy = 0;
        borderedPanel.add(usernameLabel, gbc);

        gbc.gridx = 1;
        borderedPanel.add(usernameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        borderedPanel.add(passwordLabel, gbc);

        gbc.gridx = 1;
        borderedPanel.add(passwordField, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.EAST;
        borderedPanel.add(loginButton, gbc);
        centerPanel.add(borderedPanel);
        add(centerPanel);
    }

    public JTextField getUsernameField() {
        return usernameField;
    }

    public JPasswordField getPasswordField() {
        return passwordField;
    }

    public JButton getLoginButton() {
        return loginButton;
    }
}
