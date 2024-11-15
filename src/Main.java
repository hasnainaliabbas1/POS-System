//import Screens.LoginsScreen;
//import Screens.SplashScreen;
//
//import javax.swing.*;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.Statement;
//
////TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
//// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
//public class Main {
//    private static final String DB_URL = "jdbc:mysql://localhost:3306/husnain";
//    private static final String DB_USER = "root";
//    private static final String DB_PASSWORD = "Captainali";
//
//    public static void main(String[] args) {
//        SplashScreen splash = new SplashScreen();
//        splash.animateText();
//
//        initializeDatabase();
//
//
//            LoginsScreen loginScreen = new LoginsScreen();
//            loginScreen.setVisible(true);
//
//    }
//
//    private static void initializeDatabase() {
//        try (Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
//            String createTableQuery = "CREATE TABLE IF NOT EXISTS users (" +
//                    "id INT NOT NULL PRIMARY KEY AUTO_INCREMENT, " +
//                    "name VARCHAR(255) UNIQUE, " +
//                    "password VARCHAR(255))";
//            Statement stmt = con.createStatement();
//            stmt.executeUpdate(createTableQuery);
//
//            String insertAdminQuery = "INSERT IGNORE INTO users (name, password) VALUES ('boss', '1234')";
//            stmt.executeUpdate(insertAdminQuery);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//}
import controller.AppController;
import controller.LoginScreenController;
import controller.SuperAdminLoginController;


public class Main {
    public static void main(String[] args) {
        AppController appController = new AppController();
        appController.initializeApp();
        new LoginScreenController();
    }
}