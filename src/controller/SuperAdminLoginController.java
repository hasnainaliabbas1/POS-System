package controller;

import view.DetailedSuperAdminScreen;
import model.SuperAdminModel;
import view.SuperAdminLoginScreen;

import javax.swing.*;

public class SuperAdminLoginController {
    private SuperAdminLoginScreen view;
    private SuperAdminModel model;
    public SuperAdminLoginController() {
        this.view = new SuperAdminLoginScreen();
        this.model = new SuperAdminModel();

        this.view.getLoginButton().addActionListener(e -> handleLogin());
        this.view.setVisible(true);
    }

    private void handleLogin() {
        String username = view.getUsernameField().getText();
        String password = new String(view.getPasswordField().getPassword());
        if (model.validateUser(username, password)) {
            new DetailedSuperAdminScreen().setVisible(true);
            view.dispose();
        } else {
            JOptionPane.showMessageDialog(view, "Invalid credentials, please try again.");
        }
    }

}
