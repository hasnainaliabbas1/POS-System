
package test;

import controller.AdminManagerController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import view.AdminManager.AdminManagerPanel;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.*;

class AdminManagerControllerTest {

    private AdminManagerController controller;
    private AdminManagerPanel adminManagerPanel;

    @BeforeEach
    void setUp() {
        controller = new AdminManagerController();
        adminManagerPanel = new AdminManagerPanel();
    }

    @Test
    void testHandleLogin_ValidCredentials() {
        adminManagerPanel.getUsernameField().setText("admin");
        adminManagerPanel.getPasswordField().setText("admin123");

        SwingUtilities.invokeLater(() -> adminManagerPanel.getLoginButton().doClick());

        assertEquals("admin", adminManagerPanel.getUsernameField().getText(), "Username should match input.");
        assertEquals("admin123", new String(adminManagerPanel.getPasswordField().getPassword()), "Password should match input.");
    }

    @Test
    void testHandleLogin_InvalidCredentials() {
        adminManagerPanel.getUsernameField().setText("wrongUser");
        adminManagerPanel.getPasswordField().setText("wrongPass");

        SwingUtilities.invokeLater(() -> adminManagerPanel.getLoginButton().doClick());

        assertEquals("wrongUser", adminManagerPanel.getUsernameField().getText(), "Invalid username should be 'wrongUser'.");
        assertEquals("wrongPass", new String(adminManagerPanel.getPasswordField().getPassword()), "Invalid password should be 'wrongPass'.");
    }

    @Test
    void testChangePassword_EmptyFields() {
        adminManagerPanel.getUsernameField().setText("");
        adminManagerPanel.getPasswordField().setText("");
        adminManagerPanel.getNewPasswordField().setText("");

        SwingUtilities.invokeLater(() -> adminManagerPanel.getSaveButton().doClick());

        assertEquals("", adminManagerPanel.getUsernameField().getText(), "Username should be empty.");
        assertEquals("", new String(adminManagerPanel.getPasswordField().getPassword()), "Password should be empty.");
        assertEquals("", new String(adminManagerPanel.getNewPasswordField().getPassword()), "New password should be empty.");
    }

    @Test
    void testChangePassword_Valid() {
        adminManagerPanel.getUsernameField().setText("admin");
        adminManagerPanel.getPasswordField().setText("admin123");
        adminManagerPanel.getNewPasswordField().setText("newPass123");

        SwingUtilities.invokeLater(() -> {
            adminManagerPanel.getLoginButton().doClick();
            adminManagerPanel.getSaveButton().doClick();
        });

        assertEquals("newPass123", new String(adminManagerPanel.getNewPasswordField().getPassword()), "New password should be set.");
    }

    @Test
    void testShowLoginPanel() {
        SwingUtilities.invokeLater(() -> {
            adminManagerPanel.showChangePasswordPanel();
            adminManagerPanel.showLoginPanel();
        });

        assertTrue(adminManagerPanel.getLoginButton().isVisible(), "Login button should be visible after switching back.");
    }
}
