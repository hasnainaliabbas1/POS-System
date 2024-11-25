package controller;

import view.AdminManager.AdminManagerPanel;
import view.LoginScreenView;

public class LoginScreenController {
    private LoginScreenView loginsScreenView;

    public LoginScreenController() {
        loginsScreenView = new LoginScreenView();
        initController();
    }

    private void initController() {
        loginsScreenView.superAdminButton.addActionListener(e -> openSuperAdminLogin());
        loginsScreenView.adminButton.addActionListener(e -> openAdminLogin());
        loginsScreenView.cashierButton.addActionListener(e -> openCashierLogin());
        loginsScreenView.dataEntryButton.addActionListener(e -> openDataEntryLogin());

        loginsScreenView.setVisible(true);
    }

    private void openSuperAdminLogin() {
        new SuperAdminLoginController();
    }

    private void openAdminLogin() {   AdminManagerController adminManagerController = new AdminManagerController();
        adminManagerController.show();}

    private void openCashierLogin() {

        System.out.println("Cashier Login Screen");
    }

    private void openDataEntryLogin() {
        // Data Entry login logic
        System.out.println("Data Entry Operator Login Screen");
    }
}
