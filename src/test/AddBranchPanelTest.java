package test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import view.AddBranchPanel;

import javax.swing.*;
import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class AddBranchPanelTest {

    private AddBranchPanel addBranchPanel;
    private JPanel cardPanel;
    private CardLayout cardLayout;

    @BeforeEach
    void setUp() {
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);
        addBranchPanel = new AddBranchPanel(cardLayout, cardPanel);
    }

    @Test
    void testFieldsInitialization() {
        assertNotNull(addBranchPanel, "AddBranchPanel should be initialized.");
    }

    @Test
    void testSaveButtonAction_EmptyFields() {
        JButton saveButton = findButton("Save Branch");

        // Simulate clicking the save button with empty fields
        saveButton.doClick();

        assertAll("Ensure fields are empty",
                () -> assertEquals("", getFieldText("Branch Code:"), "Branch Code should be empty."),
                () -> assertEquals("", getFieldText("Branch Name:"), "Branch Name should be empty."),
                () -> assertEquals("", getFieldText("City:"), "City should be empty."),
                () -> assertEquals("", getFieldText("Address:"), "Address should be empty."),
                () -> assertEquals("", getFieldText("Phone:"), "Phone should be empty.")
        );
    }

    @Test
    void testBackButtonAction() {
        JButton backButton = findButton("Back");
        cardPanel.add(addBranchPanel, "addBranchPanel");
        cardPanel.add(new JPanel(), "mainPanel");

        // Simulate clicking the back button
        cardLayout.show(cardPanel, "addBranchPanel");
        backButton.doClick();

        assertEquals("mainPanel", getVisibleCardName(), "Back button should show the main panel.");
    }

    @Test
    void testCreationDateFieldIsNotEditable() {
        JTextField creationDateField = findField("Date of Creation:");
        assertFalse(creationDateField.isEditable(), "Creation date field should not be editable.");
    }

    @Test
    void testActiveCheckBoxIsCheckedByDefault() {
        JCheckBox activeBox = findCheckBox("Active:");
        assertTrue(activeBox.isSelected(), "Active checkbox should be selected by default.");
    }

    // Helper Methods
    private String getVisibleCardName() {
        for (Component comp : cardPanel.getComponents()) {
            if (comp.isVisible()) {
                return ((JPanel) comp).getName();
            }
        }
        return null;
    }

    private JButton findButton(String text) {
        for (Component comp : addBranchPanel.getComponents()) {
            if (comp instanceof JButton && ((JButton) comp).getText().equals(text)) {
                return (JButton) comp;
            }
        }
        return null;
    }

    private JTextField findField(String labelText) {
        for (Component comp : addBranchPanel.getComponents()) {
            if (comp instanceof JTextField && ((JTextField) comp).getAccessibleContext().getAccessibleName() != null) {
                return (JTextField) comp;
            }
        }
        return null;
    }

    private JCheckBox findCheckBox(String labelText) {
        for (Component comp : addBranchPanel.getComponents()) {
            if (comp instanceof JCheckBox && ((JCheckBox) comp).getText().equals(labelText)) {
                return (JCheckBox) comp;
            }
        }
        return null;
    }

    private String getFieldText(String labelText) {
        JTextField field = findField(labelText);
        return field != null ? field.getText().trim() : "";
    }
}