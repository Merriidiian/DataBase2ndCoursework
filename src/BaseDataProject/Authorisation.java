package BaseDataProject;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import static BaseDataProject.Main.connection;
import static BaseDataProject.Main.tp;
import static BaseDataProject.Main.typeProjects;

public class Authorisation {
    String authFlag = "";

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button authButton;

    @FXML
    private TextField authLog;

    @FXML
    private PasswordField authPassword;

    @FXML
    private Text authWelcome;

    @FXML
    private AnchorPane authWindow;

    @FXML
    void initialize() {
        //Нажатие на кнопку "Войти"
        authButton.setOnAction(event -> {
            //Переменные для входа (логин, пароль и флаг для определения профиля входа(админ,повар или официант))
            String login = authLog.getText().trim();
            String password = authPassword.getText().trim();
            //Авторизация по логину и паролю
            if (!login.equals("") && !password.equals(""))
            {
                try {
                    loginAndPassword(login,password);
                } catch (SQLException throwables) {
                    System.out.println("Неверный логин или пароль!");
                    throwables.printStackTrace();
                }
                System.out.println(authFlag + " is your profile!");
                //Кейсы разных профилей
                switch (authFlag){
                    case ("admin"):
                    {
                        sceneChangeToAdmin();
                        break;
                    }
                }
            }
            else {
                System.out.println("Error!");
            }
        });
    }
    private void sceneChangeToAdmin () {
        // Переход с сцены на сцену
        authButton.getScene().getWindow().hide();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/BaseDataProject/admin.fxml"));
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Parent  root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Админ");
        stage.showAndWait();
        // Конец перехода
    }

    //Метод проверки пароля
    public void loginAndPassword(String login, String password) throws SQLException {
        //Запрос пароля с базы данных
        typeProjects = connection.prepareStatement("SELECT * FROM rest.list_password WHERE (login = '"+ login +"' && password = '"+ password+"');");
        tp = typeProjects.executeQuery();
        tp.next();
        //Исключение неверного ввода пароля
        try {
            authFlag = tp.getString("profession");
        } catch (SQLException errors){
            System.out.println("Error login or password!");
        }
    }
}
