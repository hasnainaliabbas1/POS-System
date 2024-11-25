
import controller.AppController;
import controller.LoginScreenController;



public class Main {
    public static void main(String[] args) {
        AppController appController = new AppController();
        appController.initializeApp();
        new LoginScreenController();
    }
}