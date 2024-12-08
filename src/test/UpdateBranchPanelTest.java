import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import javax.swing.*;

public class UpdateBranchPanelTest {

    @Test
    public void testSaveBranchDetails() {
        UpdateBranchPanel panel = new UpdateBranchPanel();
        
        // Simulate user input
        panel.branchNameField.setText("Test Branch");
        panel.cityField.setText("Test City");
        panel.addressField.setText("123 Test St.");
        panel.phoneField.setText("123456789");
        panel.isActiveCheckBox.setSelected(true);
        
        // Call save method
        panel.saveButton.doClick();
        
        // Verify success message (as no actual DB operation is tested, we assume success based on UI)
        assertTrue(panel.branchNameField.getText().isEmpty(), "Branch name field should be cleared after save.");
        assertTrue(panel.cityField.getText().isEmpty(), "City field should be cleared after save.");
        assertTrue(panel.addressField.getText().isEmpty(), "Address field should be cleared after save.");
        assertTrue(panel.phoneField.getText().isEmpty(), "Phone field should be cleared after save.");
        assertFalse(panel.isActiveCheckBox.isSelected(), "Active checkbox should be cleared after save.");
    }

    @Test
    public void testClearFields() {
        UpdateBranchPanel panel = new UpdateBranchPanel();
        
        // Simulate user input
        panel.branchNameField.setText("Test Branch");
        panel.cityField.setText("Test City");
        panel.addressField.setText("123 Test St.");
        panel.phoneField.setText("123456789");
        panel.isActiveCheckBox.setSelected(true);
        
        // Call clear method
        panel.clearButton.doClick();
        
        // Verify fields are cleared
        assertTrue(panel.branchNameField.getText().isEmpty(), "Branch name field should be cleared.");
        assertTrue(panel.cityField.getText().isEmpty(), "City field should be cleared.");
        assertTrue(panel.addressField.getText().isEmpty(), "Address field should be cleared.");
        assertTrue(panel.phoneField.getText().isEmpty(), "Phone field should be cleared.");
        assertFalse(panel.isActiveCheckBox.isSelected(), "Active checkbox should be cleared.");
    }
}
