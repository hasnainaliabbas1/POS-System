
package test;

import controller.BackButtonHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class BackButtonHandlerTest {

    private JButton backButton;
    private JFrame currentFrame;
    private JFrame parentFrame;
    private BackButtonHandler backButtonHandler;

    @BeforeEach
    void setUp() {
        backButton = new JButton();
        currentFrame = new JFrame("Current Frame");
        parentFrame = new JFrame("Parent Frame");
        backButtonHandler = new BackButtonHandler(backButton, currentFrame, parentFrame);

        // Simulate frame properties
        currentFrame.setSize(400, 300);
        parentFrame.setSize(400, 300);
        currentFrame.setVisible(true);
        parentFrame.setVisible(false);
    }

    @Test
    void testBackButtonAction_PerformsCorrectly() {
        // Simulate clicking the back button
        backButton.doClick();

        // Assert that the current frame is disposed and the parent frame is visible
        assertFalse(currentFrame.isVisible(), "Current frame should be closed.");
        assertTrue(parentFrame.isVisible(), "Parent frame should be visible.");
    }

    @Test
    void testCustomizeBackButton() {
        Color expectedColor = Color.BLUE;

        backButtonHandler.customizeBackButton("Go Back", expectedColor);

        assertEquals("Back", backButton.getText(), "Button text should be 'Back'.");
        assertEquals(expectedColor, backButton.getBackground(), "Button background should be set correctly.");
        assertEquals(Color.WHITE, backButton.getForeground(), "Button text color should be white.");
    }

    @Test
    void testBackButtonInitialization() {
        assertNotNull(backButton, "Back button should be initialized.");
        assertNotNull(currentFrame, "Current frame should be initialized.");
        assertNotNull(parentFrame, "Parent frame should be initialized.");
    }
}
