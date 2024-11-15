package view;

import javax.swing.*;
import java.awt.*;

public class BranchManagerPanel extends JPanel {
    public BranchManagerPanel() {
        setLayout(new BorderLayout());
        add(new JLabel("Manage Branch Manager", SwingConstants.CENTER), BorderLayout.CENTER);
    }
}
