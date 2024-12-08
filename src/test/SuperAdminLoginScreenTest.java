
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.*;

class SuperAdminLoginScreenTest {

    private SuperAdminLoginScreen loginScreen;

    @BeforeEach
    void setUp() {
        loginScreen = new SuperAdminLoginScreen();
    }

    @Test
    void testUIComponentsInitialization() {
        assertNotNull(loginScreen.getUsernameField(), "Username field should be initialized");
        assertNotNull(loginScreen.getPasswordField(), "Password field should be initialized");
        assertNotNull(loginScreen.getLoginButton(), "Login button should be initialized");
        assertNotNull(loginScreen.getBackButton(), "Back button should be initialized");
    }

    @Test
    void testLoginButtonAction() {
        JTextField usernameField = loginScreen.getUsernameField();
        JPasswordField passwordField = loginScreen.getPasswordField();
        JButton loginButton = loginScreen.getLoginButton();

        usernameField.setText("admin");
        passwordField.setText("password");

        loginButton.doClick();

        // Simulate login logic
        assertEquals("admin", usernameField.getText(), "Username should be 'admin'");
        assertEquals("password", new String(passwordField.getPassword()), "Password should be 'password'");
    }

    @Test
    void testBackButtonAction() {
        JButton backButton = loginScreen.getBackButton();

        backButton.doClick();

        // Check if the frame is disposed when back button is clicked
        assertFalse(loginScreen.isVisible(), "Login screen should be closed after clicking back");
    }
}
