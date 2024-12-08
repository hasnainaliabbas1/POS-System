
package test;

import controller.LoginController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LoginControllerTest {

    private LoginController loginController;

    @BeforeEach
    void setUp() {
        loginController = new LoginController();
    }

    @Test
    void testAuthenticate_ValidCredentials() {
        boolean result = loginController.authenticate("validUser", "validPassword");
        assertTrue(result, "Authentication should succeed for valid credentials.");
    }

    @Test
    void testAuthenticate_InvalidCredentials() {
        boolean result = loginController.authenticate("invalidUser", "invalidPassword");
        assertFalse(result, "Authentication should fail for invalid credentials.");
    }

    @Test
    void testIsFirstLogin_DefaultPassword() {
        boolean result = loginController.isFirstLogin("firstLoginUser");
        assertTrue(result, "User should be identified as first-time login if using the default password.");
    }

    @Test
    void testIsFirstLogin_NonDefaultPassword() {
        boolean result = loginController.isFirstLogin("nonFirstLoginUser");
        assertFalse(result, "User should not be identified as first-time login if using a different password.");
    }

    @Test
    void testChangePassword_ValidCredentials() {
        boolean result = loginController.changePassword("validUser", "currentPassword", "newPassword123");
        assertTrue(result, "Password should be changed successfully for valid credentials.");
    }

    @Test
    void testChangePassword_InvalidCurrentPassword() {
        boolean result = loginController.changePassword("validUser", "wrongPassword", "newPassword123");
        assertFalse(result, "Password change should fail for an incorrect current password.");
    }

    @Test
    void testGetUserRole_ExistingUser() {
        String role = loginController.getUserRole("validUser");
        assertNotNull(role, "User role should be retrieved for an existing user.");
    }

    @Test
    void testGetUserRole_NonExistingUser() {
        String role = loginController.getUserRole("nonExistingUser");
        assertNull(role, "User role should be null for a non-existing user.");
    }
}
