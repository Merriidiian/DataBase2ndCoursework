package BaseDataProject;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.*;
import java.util.Properties;

public class Main extends Application {
    public static Connection connection;
    public static ResultSet tp;
    public static PreparedStatement typeProjects;
    public static Statement sendingCommands;
    public static String dateDishList;

    @Override
    public void start(Stage primaryStage) throws Exception {
            Parent root = FXMLLoader.load(getClass().getResource("Auth.fxml"));
        primaryStage.setTitle("Restaurant");
        primaryStage.setScene(new Scene(root, 351, 470));
        primaryStage.setResizable(false);
        primaryStage.show();
    }


    public static void main(String[] args) throws SQLException {
        String user = "root";
        String password = "12345";
        //Создание объекта класса для передачи значений логина и пароля в MySQL.
        Properties props = new Properties();
        props.setProperty("user",user);
        props.setProperty("password",password);
        //Подключение к БД.
        connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/test?serverTimezone=UTC",props);
        //Вывод окна приложения
        launch(args); }
}
