package controller;

import model.DatabaseManager;
import view.SplashScreenView;
import view.LoginScreenView;

public class AppController {
    private DatabaseManager databaseManager;
    private SplashScreenView splashScreenView;
    private LoginScreenView loginScreenView;

    public AppController() {
        databaseManager = new DatabaseManager();
        splashScreenView = new SplashScreenView();
        loginScreenView = new LoginScreenView();
    }

    public void initializeApp() {
        splashScreenView.animateText();
        databaseManager.initializeDatabase();

    }

}
