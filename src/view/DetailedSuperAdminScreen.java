package view;

import javax.swing.*;
import java.awt.*;

public class DetailedSuperAdminScreen extends JFrame {
    private CardLayout cardLayout;
    private JPanel mainPanel;
    private JButton ManageBranchButton;
    private JButton ManageBranchManager;
        private JButton ViewReportsButton;

    public DetailedSuperAdminScreen() {
        setTitle("Super Admin Dashboard");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel sidebar = createSidebar();
        add(sidebar, BorderLayout.WEST);

        mainPanel = new JPanel(new CardLayout());
        mainPanel.add(new ManageBranchPanel(), "Manage Branch");
        mainPanel.add(new BranchManagerPanel(), "Manage Branch Manager");
        mainPanel.add(new ReportsPanel(), "View Reports");
        add(mainPanel, BorderLayout.CENTER);

        cardLayout = (CardLayout) mainPanel.getLayout();
    }

    private JPanel createSidebar() {
        JPanel sidebar = new JPanel(new GridLayout(5, 1));
        sidebar.setBackground(new Color(240, 240, 240));

        ManageBranchButton = createSidebarButton("Manage Branch", "icons/br.png");
        ManageBranchButton.addActionListener(e -> switchPanel("Manage Branch"));

        ManageBranchManager = createSidebarButton("Manage Branch Manager", "icons/manager.png");
        ManageBranchManager.addActionListener(e -> switchPanel("Manage Branch Manager"));

        ViewReportsButton = createSidebarButton("View Reports", "icons/reports.png");
        ViewReportsButton.addActionListener(e -> switchPanel("View Reports"));

        sidebar.add(ManageBranchButton);
        sidebar.add(ManageBranchManager);
        sidebar.add(ViewReportsButton);

        return sidebar;
    }

    private JButton createSidebarButton(String text, String iconPath) {
        JButton button = new JButton(text);
        button.setFont(new Font("Tahoma", Font.BOLD, 20));
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
