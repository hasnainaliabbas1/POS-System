package view.AdminManager;
import javax.swing.*;
import java.awt.*;

public class AdminManagerScreen extends JFrame {
    private CardLayout cardLayout;
    private JPanel mainPanel;
    private JButton manageCashierButton;
    private JButton manageDataEntryOperatorButton;

    public AdminManagerScreen() {
        setTitle("Admin Manager Dashboard");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Sidebar creation
        JPanel sidebar = createSidebar();
        add(sidebar, BorderLayout.WEST);

        // Main panel with CardLayout
        mainPanel = new JPanel(new CardLayout());
        mainPanel.add(new AddCashierPanel(), "Add Cashier");
        mainPanel.add(new AddDataEntryOperatorPanel(), "Add Data Entry Operator");

        add(mainPanel, BorderLayout.CENTER);

        cardLayout = (CardLayout) mainPanel.getLayout();
    }

    private JPanel createSidebar() {
        JPanel sidebar = new JPanel(new GridLayout(4, 1));
        sidebar.setBackground(new Color(240, 240, 240));

        manageCashierButton = createSidebarButton("Manage Cashier", "icons/cashier.png");
        manageCashierButton.addActionListener(e -> switchPanel("Manage Cashier"));

        manageDataEntryOperatorButton = createSidebarButton("Manage Data Entry Operator", "icons/dataentry.png");
        manageDataEntryOperatorButton.addActionListener(e -> switchPanel("Manage Data Entry Operator"));

        sidebar.add(manageCashierButton);
        sidebar.add(manageDataEntryOperatorButton);

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


