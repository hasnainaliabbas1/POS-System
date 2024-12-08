package view;

import javax.swing.*;
import controller.VendorController;
import controller.ProductController;
/*
public class DashboardView {
    public DashboardView() {
        JFrame frame = new JFrame("Dashboard");
        JPanel panel = new JPanel();

        JButton vendorButton = new JButton("Manage Vendors");
        JButton productButton = new JButton("Manage Products");
        JButton logoutButton = new JButton("Logout");

        panel.add(vendorButton);
        panel.add(productButton);
        panel.add(logoutButton);

        vendorButton.addActionListener(e -> new VendorView());
        productButton.addActionListener(e -> new ProductView());
        logoutButton.addActionListener(e -> {
            frame.dispose();
            new LoginView();
        });

        frame.add(panel);
        frame.setSize(400, 200);
        frame.setVisible(true);
    }
}


*/




































import javax.swing.*;
import java.awt.*;
import controller.VendorController;
import controller.ProductController;

public class DashboardView {
    public DashboardView() {
        JFrame frame = new JFrame("Dashboard");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(70, 130, 180));
        JLabel titleLabel = new JLabel("Dashboard");
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 36));
        headerPanel.add(titleLabel);
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(3, 1, 20, 20));
        buttonPanel.setBackground(new Color(240, 248, 255));
        JButton vendorButton = createButton("Manage Vendors", new Color(60, 179, 113));
        JButton productButton = createButton("Manage Products", new Color(30, 144, 255));
        JButton logoutButton = createButton("Logout", new Color(255, 69, 0));

        buttonPanel.add(vendorButton);
        buttonPanel.add(productButton);
        buttonPanel.add(logoutButton);
        frame.add(headerPanel, BorderLayout.NORTH);
        frame.add(buttonPanel, BorderLayout.CENTER);
        vendorButton.addActionListener(e -> new VendorView());
        productButton.addActionListener(e -> new ProductView());
        logoutButton.addActionListener(e -> {
            frame.dispose();
            new LoginView();
        });
        frame.setSize( 1000,  800);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
    }
    private JButton createButton(String text, Color bgColor) {
        JButton button = new JButton(text);
        button.setBackground(bgColor);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setFont(new Font("Arial", Font.PLAIN, 24));
        button.setPreferredSize(new Dimension(200, 50));
        return button;
    }
}