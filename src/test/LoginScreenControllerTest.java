
package test;

import controller.LoginScreenController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import view.LoginScreenView;

import static org.junit.jupiter.api.Assertions.*;

class LoginScreenControllerTest {

    private LoginScreenController loginScreenController;
    private LoginScreenView loginScreenView;

    @BeforeEach
    void setUp() {
        loginScreenController = new LoginScreenController();
        loginScreenView = new LoginScreenView();
    }

    @Test
    void testSuperAdminButtonAction() {
        assertDoesNotThrow(() -> loginScreenView.superAdminButton.doClick(),
                "Clicking the Super Admin button should not throw an exception.");
    }

    @Test
    void testAdminButtonAction() {
        assertDoesNotThrow(() -> loginScreenView.adminButton.doClick(),
                "Clicking the Admin button should not throw an exception.");
    }

    @Test
    void testCashierButtonAction() {
        assertDoesNotThrow(() -> loginScreenView.cashierButton.doClick(),
                "Clicking the Cashier button should not throw an exception.");
    }

    @Test
    void testDataEntryButtonAction() {
        assertDoesNotThrow(() -> loginScreenView.dataEntryButton.doClick(),
                "Clicking the Data Entry button should not throw an exception.");
    }

    @Test
    void testViewIsVisible() {
        assertTrue(loginScreenView.isVisible(), "Login screen should be visible after initialization.");
    }
}
