
package view;

import javax.swing.*;
import java.awt.*;

public class SplashScreenView extends JFrame {

    private JLabel textLabel;
    private JProgressBar progressBar;

    public SplashScreenView() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(screenSize.width, screenSize.height);
        setLocationRelativeTo(null);
        setUndecorated(true);
        setLayout(new BorderLayout());
        getContentPane().setBackground(Color.WHITE);

        textLabel = new JLabel("Metro Cash and Carry", SwingConstants.CENTER);
        textLabel.setFont(new Font("Arial", Font.BOLD, 1));
        textLabel.setForeground(new Color(0, 102, 204));
        add(textLabel, BorderLayout.CENTER);

        progressBar = new JProgressBar();
        progressBar.setValue(0);
        progressBar.setStringPainted(true);
        progressBar.setForeground(new Color(0, 102, 204));
        progressBar.setPreferredSize(new Dimension(300, 30));
        JPanel progressPanel = new JPanel();
        progressPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        progressPanel.add(progressBar);
        progressPanel.setBackground(Color.WHITE);

        add(progressPanel, BorderLayout.SOUTH);
    }

    public void animateText() {
        setVisible(true);
        int maxSize = 100;
        for (int size = 1; size <= maxSize; size += 3) {
            textLabel.setFont(new Font("Arial", Font.BOLD, size));
            textLabel.repaint();
            int progress = (int) ((size / (float) maxSize) * 100);
            progressBar.setValue(progress);
            progressBar.setString("Loading... " + progress + "%");
            try {
                Thread.sleep(80);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        setVisible(false);
    }
}
