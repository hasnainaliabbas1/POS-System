package view;




import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SalesReport extends JFrame {

    private Connection connection;

    // SQL query to get total sales amount and total quantity sold for each product
    private final String queryCombinedData = "SELECT p.name, " +
            "SUM(sd.quantity * p.sale_price) AS total_sale, " +
            "SUM(sd.quantity) AS total_quantity " +
            "FROM salesdetails sd " +
            "JOIN products p ON sd.product_id = p.id " +
            "GROUP BY p.name";

    public SalesReport() {
        super("Combined Sales Report");

        // Establish database connection
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/new", "root", "");
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Database connection failed!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Set up the UI
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 800);
        setLayout(new BorderLayout());

        // Header
        JLabel header = new JLabel("Combined Sales Report (Amount & Quantity)", SwingConstants.CENTER);
        header.setFont(new Font("SansSerif", Font.BOLD, 24));
        header.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(header, BorderLayout.NORTH);

        // Chart panel
        JPanel chartPanel = new JPanel();
        chartPanel.setLayout(new BorderLayout());
        chartPanel.add(new GroupedBarChartPanel(fetchCombinedData()), BorderLayout.CENTER);
        chartPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        add(chartPanel, BorderLayout.CENTER);

        // Footer
        JLabel footer = new JLabel("Generated using Sales Data", SwingConstants.CENTER);
        footer.setFont(new Font("SansSerif", Font.ITALIC, 12));
        footer.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(footer, BorderLayout.SOUTH);

        setVisible(true);
    }

    // Fetch data for combined sales report
    private List<CombinedChartData> fetchCombinedData() {
        List<CombinedChartData> data = new ArrayList<>();

        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(queryCombinedData)) {
            while (rs.next()) {
                String productName = rs.getString("name");
                double totalSale = rs.getDouble("total_sale");
                int totalQuantity = rs.getInt("total_quantity");
                data.add(new CombinedChartData(productName, totalSale, totalQuantity));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return data;
    }

}

// Data class for combined chart data
class CombinedChartData {
    String productName;
    double totalSale;
    int totalQuantity;

    CombinedChartData(String productName, double totalSale, int totalQuantity) {
        this.productName = productName;
        this.totalSale = totalSale;
        this.totalQuantity = totalQuantity;
    }
}

// Panel to draw grouped bar chart
class GroupedBarChartPanel extends JPanel {
    private final List<CombinedChartData> data;

    public GroupedBarChartPanel(List<CombinedChartData> data) {
        this.data = data;
        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(800, 400));
    }


    @Override

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        int width = getWidth();
        int height = getHeight();
        int padding = 50;
        int chartWidth = width - 2 * padding;
        int chartHeight = height - 2 * padding;
        int groupWidth = chartWidth / data.size();
        int barWidth = groupWidth / 3;

        double maxSale = data.stream().mapToDouble(d -> d.totalSale).max().orElse(1);
        double maxQuantity = data.stream().mapToDouble(d -> d.totalQuantity).max().orElse(1);
        double maxValue = Math.max(maxSale, maxQuantity);

        // Adjusted title position
        g2d.setFont(new Font("SansSerif", Font.BOLD, 16));
       // g2d.drawString("Sales Report: Amount & Quantity by Product", padding, padding + 1);

        // Draw axes
        g2d.drawLine(padding, height - padding, width - padding, height - padding); // X-axis
        g2d.drawLine(padding, padding, padding, height - padding);                 // Y-axis

        // Draw bars and labels (unchanged)
        int x = padding;
        for (CombinedChartData entry : data) {
            int saleBarHeight = (int) ((entry.totalSale / maxValue) * chartHeight);
            int quantityBarHeight = (int) ((entry.totalQuantity / maxValue) * chartHeight);

            // Total Sale Bar
            g2d.setColor(new Color(100, 150, 255)); // Blue
            g2d.fillRect(x, height - padding - saleBarHeight, barWidth, saleBarHeight);
            g2d.setColor(Color.BLACK);
            g2d.drawRect(x, height - padding - saleBarHeight, barWidth, saleBarHeight);

            // Total Quantity Bar
            g2d.setColor(new Color(150, 255, 150)); // Green
            g2d.fillRect(x + barWidth + 5, height - padding - quantityBarHeight, barWidth, quantityBarHeight);
            g2d.setColor(Color.BLACK);
            g2d.drawRect(x + barWidth + 5, height - padding - quantityBarHeight, barWidth, quantityBarHeight);

            // Labels above bars
            g2d.setFont(new Font("SansSerif", Font.PLAIN, 12));
            g2d.setColor(Color.BLACK);
            g2d.drawString(String.format("%.2f", entry.totalSale), x, height - padding - saleBarHeight - 10);
            g2d.drawString(String.valueOf(entry.totalQuantity), x + barWidth + 5, height - padding - quantityBarHeight - 10);

            // Product name label
            g2d.drawString(entry.productName, x, height - padding + 20);

            x += groupWidth;
        }

        // Legend
        g2d.setFont(new Font("SansSerif", Font.PLAIN, 12));
        g2d.setColor(new Color(100, 150, 255));
        g2d.fillRect(width - padding - 150, padding + 30, 20, 20);
        g2d.setColor(Color.BLACK);
        g2d.drawString("Total Sales Amount", width - padding - 120, padding + 45);

        g2d.setColor(new Color(150, 255, 150));
        g2d.fillRect(width - padding - 150, padding + 60, 20, 20);
        g2d.setColor(Color.BLACK);
        g2d.drawString("Total Quantity Sold", width - padding - 120, padding + 75);
    }

}
