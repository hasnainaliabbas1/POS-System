package test;


import controller.AppController;
import model.DatabaseManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import view.DataEntryOperator.LoginView;
import view.LoginScreenView;
import view.SplashScreenView;

import static org.junit.jupiter.api.Assertions.*;

class AppControllerTest {

    private AppController appController;
    private DatabaseManager databaseManager;
    private SplashScreenView splashScreenView;
    private LoginScreenView loginScreenView;

    @BeforeEach
    void setUp() {
        appController = new AppController();
        databaseManager = new DatabaseManager();
        splashScreenView = new SplashScreenView();
        loginScreenView = new LoginScreenView();
    }

    @Test
    void testInitializeApp_DatabaseInitialized() {
        assertDoesNotThrow(() -> {
            appController.initializeApp();
        }, "App initialization should not throw any exception.");
    }

    @Test
    void testSplashScreenAnimation() {
        assertDoesNotThrow(() -> {
            splashScreenView.animateText();
        }, "Splash screen animation should run without errors.");
    }

    @Test
    void testDatabaseInitialization() {
        assertDoesNotThrow(() -> {
            databaseManager.initializeDatabase();
        }, "Database initialization should run without errors.");
    }

    @Test
    void testLoginScreenInitialization() {
        assertNotNull(loginScreenView, "Login screen should be initialized.");
    }
}