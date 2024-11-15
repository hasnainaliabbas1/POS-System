package view;

import javax.swing.*;
import java.awt.*;

public class ReportsPanel extends JPanel {
    public ReportsPanel() {
        setLayout(new BorderLayout());
        add(new JLabel("View Reports", SwingConstants.CENTER), BorderLayout.CENTER);
    }
}
