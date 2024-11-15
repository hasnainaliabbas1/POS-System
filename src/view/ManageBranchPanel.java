package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ManageBranchPanel extends JPanel {

    private JButton addBranchButton;
    private JButton updateBranchButton;
    private JButton deactivateBranchButton;

    private JPanel cardPanel;
    private CardLayout cardLayout;

    public ManageBranchPanel() {
        setLayout(new BorderLayout());
        setBackground(new Color(58, 130, 248));
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        JPanel mainPanel = createMainPanel();

        cardPanel.add(mainPanel, "mainPanel");
        cardPanel.add(new AddBranchPanel(cardLayout, cardPanel), "addBranchPanel");
        cardPanel.add(new UpdateBranchPanel(), "updateBranchPanel");
      //  cardPanel.add(new DeactivateBranchPanel(), "deactivateBranchPanel");

        // Add card panel to ManageBranchPanel
        add(cardPanel, BorderLayout.CENTER);
        cardLayout.show(cardPanel, "mainPanel");
    }

    private JPanel createMainPanel() {
        // Create the main panel with a GridBagLayout
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground( Color.white);

        // Create buttons
        addBranchButton = createButton("Add Branch", "icons/add.png");
        updateBranchButton = createButton("Update Branch", "icons/update.png");
        deactivateBranchButton = createButton("Deactivate Branch", "icons/deactivate.png");

        // Attach action listeners to switch panels
        addBranchButton.addActionListener(e -> cardLayout.show(cardPanel, "addBranchPanel"));
        updateBranchButton.addActionListener(e -> cardLayout.show(cardPanel, "updateBranchPanel"));
        deactivateBranchButton.addActionListener(e -> cardLayout.show(cardPanel, "deactivateBranchPanel"));

        // Arrange buttons in a flow layout
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 40, 40));
        buttonPanel.setBackground(new Color(58, 130, 248));
        buttonPanel.add(addBranchButton);
        buttonPanel.add(updateBranchButton);
        buttonPanel.add(deactivateBranchButton);

        // Add button panel to main panel using GridBagConstraints
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        mainPanel.add(buttonPanel, gbc);

        return mainPanel;
    }

    private JButton createButton(String text, String iconPath) {
        JButton button = new JButton(text);
        button.setFont(new Font("Tahoma", Font.BOLD, 14));
        button.setForeground(Color.BLACK);
        button.setBackground(new Color(173, 216, 230));
        button.setFocusPainted(false);
        button.setHorizontalTextPosition(SwingConstants.CENTER);
        button.setVerticalTextPosition(SwingConstants.BOTTOM);

        button.setPreferredSize(new Dimension(180, 180));
        button.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));

        button.setIcon(loadIcon(iconPath, 40, 40));
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

    public void showMainPanel() {
        cardLayout.show(cardPanel, "mainPanel");
    }
}
