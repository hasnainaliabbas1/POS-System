package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ReportsPanel extends JPanel {

    private JComboBox<String> branchCodeComboBox;
    private JComboBox<String> reportTypeComboBox;
    private JComboBox<String> timeRangeComboBox;
    private JButton generateReportButton;
    private JButton generateGraphButton;
    private JTextArea reportArea;

    public ReportsPanel() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        initializeComponents();
    }

    private void initializeComponents() {
        // Top Panel for Controls
        JPanel controlPanel = new JPanel(new GridBagLayout());
        controlPanel.setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Branch Code Dropdown
        JLabel branchLabel = new JLabel("Select Branch Code:");
        branchCodeComboBox = new JComboBox<>(new String[]{"Branch 001", "Branch 002", "Branch 003"}); // Sample data
        branchCodeComboBox.setPreferredSize(new Dimension(200, 30));
        addToPanel(controlPanel, branchLabel, branchCodeComboBox, gbc, 0);

        // Report Type Dropdown
        JLabel reportTypeLabel = new JLabel("Select Report Type:");
        reportTypeComboBox = new JComboBox<>(new String[]{"Sales", "Remaining Stock", "Profit"});
        reportTypeComboBox.setPreferredSize(new Dimension(200, 30));
        addToPanel(controlPanel, reportTypeLabel, reportTypeComboBox, gbc, 1);

        // Time Range Dropdown
        JLabel timeRangeLabel = new JLabel("Select Time Range:");
        timeRangeComboBox = new JComboBox<>(new String[]{"Today", "Weekly", "Monthly", "Yearly", "Specify Range"});
        timeRangeComboBox.setPreferredSize(new Dimension(200, 30));
        addToPanel(controlPanel, timeRangeLabel, timeRangeComboBox, gbc, 2);

        // Generate Report Button
        generateReportButton = new JButton("Generate Report");
        generateReportButton.setBackground(new Color(0, 102, 204));
        generateReportButton.setForeground(Color.WHITE);
        generateReportButton.setPreferredSize(new Dimension(180, 40));
        generateReportButton.addActionListener(new GenerateReportListener());

        // Generate Graph Button
        generateGraphButton = new JButton("Generate Graph");
        generateGraphButton.setBackground(new Color(0, 153, 51));
        generateGraphButton.setForeground(Color.WHITE);
        generateGraphButton.setPreferredSize(new Dimension(180, 40));
        generateGraphButton.addActionListener(new GenerateGraphListener());

        // Adding buttons to control panel
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.add(generateReportButton);
        buttonPanel.add(generateGraphButton);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        controlPanel.add(buttonPanel, gbc);

        // Text Area for displaying reports
        reportArea = new JTextArea(20, 50);
        reportArea.setEditable(false);
        reportArea.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
        reportArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        JScrollPane scrollPane = new JScrollPane(reportArea);

        // Adding Panels
        add(controlPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
    }

    private void addToPanel(JPanel panel, JLabel label, JComponent component, GridBagConstraints gbc, int row) {
        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.anchor = GridBagConstraints.EAST;
        panel.add(label, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(component, gbc);
    }

    private class GenerateReportListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String branchCode = (String) branchCodeComboBox.getSelectedItem();
            String reportType = (String) reportTypeComboBox.getSelectedItem();
            String timeRange = (String) timeRangeComboBox.getSelectedItem();

            // Simulate report generation logic
            String report = "Report for " + reportType + " at " + branchCode + "\n"
                    + "Time Range: " + timeRange + "\n\n"
                    + "Sample Data:\n"
                    + "--------------------------------------\n"
                    + "Entry 1: Data for " + reportType + "\n"
                    + "Entry 2: More data\n"
                    + "--------------------------------------";

            reportArea.setText(report);
        }
    }

    private class GenerateGraphListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String reportType = (String) reportTypeComboBox.getSelectedItem();
            String timeRange = (String) timeRangeComboBox.getSelectedItem();

            // Simulate graph generation
            JOptionPane.showMessageDialog(
                    ReportsPanel.this,
                    "Generating graph for " + reportType + " over " + timeRange,
                    "Graph Generation",
                    JOptionPane.INFORMATION_MESSAGE
            );
        }
    }
}
