package view.AdminManager;

import javax.swing.*;
import java.awt.*;

public class DetailAdminManager extends JPanel {

    private CardLayout cardLayout;
    private JPanel mainPanel;
    private JButton addCashierButton;
    private JButton addDataEntryButton;
    private JButton manageReportsButton;

    public DetailAdminManager() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        // Title Label
        JLabel welcomeLabel = new JLabel("Welcome to Admin Manager Panel", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 18));
        welcomeLabel.setForeground(new Color(0, 102, 204));
        welcomeLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(welcomeLabel, BorderLayout.NORTH);

        // Sidebar Panel
        JPanel sidebarPanel = createSideBar();
        add(sidebarPanel, BorderLayout.WEST);

        // Main Content Panel
        mainPanel = new JPanel(new CardLayout());
        mainPanel.add(new AddCashierPanel(), "Add Cashier");
        mainPanel.add(new AddDataEntryOperatorPanel(), "Add Data Entry Operator");
     //   mainPanel.add(new ManageReportsPanel(), "Manage Reports");
        add(mainPanel, BorderLayout.CENTER);

        cardLayout = (CardLayout) mainPanel.getLayout();
    }

    private JPanel createSideBar() {
        JPanel sideBarPanel = new JPanel();
        sideBarPanel.setLayout(new BoxLayout(sideBarPanel, BoxLayout.Y_AXIS));
        sideBarPanel.setBackground(new Color(0, 102, 204));
        sideBarPanel.setPreferredSize(new Dimension(200, 0));

        addCashierButton = createSidebarButton("Add Cashier", "icons/cashier.png");
        addCashierButton.addActionListener(e -> switchPanel("Add Cashier"));
        sideBarPanel.add(addCashierButton);
        sideBarPanel.add(Box.createVerticalStrut(10));

        addDataEntryButton = createSidebarButton("Add Data Entry Operator", "icons/entry.png");
        addDataEntryButton.addActionListener(e -> switchPanel("Add Data Entry Operator"));
        sideBarPanel.add(addDataEntryButton);
        sideBarPanel.add(Box.createVerticalStrut(10));

        manageReportsButton = createSidebarButton("Manage Reports", "icons/reports.png");
        manageReportsButton.addActionListener(e -> switchPanel("Manage Reports"));
        sideBarPanel.add(manageReportsButton);

        return sideBarPanel;
    }

    private JButton createSidebarButton(String text, String iconPath) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setBackground(new Color(0, 102, 204));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setHorizontalAlignment(SwingConstants.LEFT);
        button.setPreferredSize(new Dimension(250, 60));

        ImageIcon icon = loadIcon(iconPath, 40, 40);
        if (icon != null) {
            button.setIcon(icon);
        }
        return button;
    }

    private ImageIcon loadIcon(String path, int width, int height) {
        try {
            ImageIcon icon = new ImageIcon(getClass().getClassLoader().getResource(path));
            Image img = icon.getImage();
            Image resizedImg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            return new ImageIcon(resizedImg);
        } catch (Exception e) {
            System.out.println("Icon not found: " + path);
            return null;
        }
    }

    private void switchPanel(String panelName) {
        cardLayout.show(mainPanel, panelName);
    }
}
