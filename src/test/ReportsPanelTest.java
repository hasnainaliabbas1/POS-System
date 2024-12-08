
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import javax.swing.*;

public class ReportsPanelTest {

    @Test
    public void testReportsPanelComponents() {
        // Create an instance of ReportsPanel
        ReportsPanel reportsPanel = new ReportsPanel();

        // Check the panel properties
        assertNotNull(reportsPanel, "ReportsPanel should be created.");

        // Verify dropdown existence
        JComboBox<String> branchCodeComboBox = reportsPanel.branchCodeComboBox;
        JComboBox<String> reportTypeComboBox = reportsPanel.reportTypeComboBox;
        JComboBox<String> timeRangeComboBox = reportsPanel.timeRangeComboBox;

        assertNotNull(branchCodeComboBox, "Branch code combo box should be created.");
        assertNotNull(reportTypeComboBox, "Report type combo box should be created.");
        assertNotNull(timeRangeComboBox, "Time range combo box should be created.");

        // Verify dropdown initial values
        assertEquals("Branch 001", branchCodeComboBox.getItemAt(0), "First branch code should be 'Branch 001'.");
        assertEquals("Sales", reportTypeComboBox.getItemAt(0), "First report type should be 'Sales'.");
        assertEquals("Today", timeRangeComboBox.getItemAt(0), "First time range should be 'Today'.");

        // Check buttons existence
        JButton generateReportButton = reportsPanel.generateReportButton;
        JButton generateGraphButton = reportsPanel.generateGraphButton;

        assertNotNull(generateReportButton, "Generate Report button should be created.");
        assertNotNull(generateGraphButton, "Generate Graph button should be created.");

        // Verify button properties
        assertEquals("Generate Report", generateReportButton.getText(), "Generate Report button text should be 'Generate Report'.");
        assertEquals("Generate Graph", generateGraphButton.getText(), "Generate Graph button text should be 'Generate Graph'.");

        // Check report area existence
        JTextArea reportArea = reportsPanel.reportArea;
        assertNotNull(reportArea, "Report area should be created.");
        assertFalse(reportArea.isEditable(), "Report area should be non-editable.");
    }
}
