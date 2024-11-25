package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginScreenView extends JFrame {
    public JButton superAdminButton;
    public JButton adminButton;
    public JButton cashierButton;
    public JButton dataEntryButton;

    public LoginScreenView() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(screenSize.width, screenSize.height);
        setLocationRelativeTo(null);
       // setUndecorated(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());
        getContentPane().setBackground(new Color(255, 255, 255)); // Background color


        Color superAdminColor = new Color(0, 102, 204);
        Color adminColor = new Color(0, 153, 76);
        Color cashierColor = new Color(204, 102, 0);
        Color dataEntryColor = new Color(153, 51, 153);

        superAdminButton = createRoleButton("Super Admin", superAdminColor);
        adminButton = createRoleButton("Branch Manager", adminColor);
        cashierButton = createRoleButton("Cashier", cashierColor);
        dataEntryButton = createRoleButton("Data Entry Operator", dataEntryColor);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 50, 10, 50);

        gbc.gridy = 0;
        add(superAdminButton, gbc);

        gbc.gridy = 1;
        add(adminButton, gbc);

        gbc.gridy = 2;
        add(cashierButton, gbc);

        gbc.gridy = 3;
        add(dataEntryButton, gbc);
    }

    private JButton createRoleButton(String roleName, Color color) {
        JButton button = new JButton(roleName);
        button.setFont(new Font("Arial", Font.BOLD, 24));
        button.setBackground(color);
        button.setForeground(Color.WHITE);
        button.setPreferredSize(new Dimension(300, 60)); // Width and height
        button.setFocusPainted(false);
        return button;
    }


}
