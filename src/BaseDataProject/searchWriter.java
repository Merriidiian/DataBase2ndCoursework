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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import static BaseDataProject.Main.*;

public class searchWriter {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button searchButton;

    @FXML
    private TextArea searchWriterInTime;

    @FXML
    private AnchorPane searchWriterWindow;

    @FXML
    private TextField timeBegin;

    @FXML
    private Button exitButton;

    @FXML
    private TextField timeEnd;

    @FXML
    void initialize() {
        //Кнопка поиска
        searchButton.setOnAction(event -> {
            try {
                giveWriterToTime();
            } catch (SQLException throwables) {
                searchWriterInTime.setText("EGOR!");
                throwables.printStackTrace();
            }
        });
        // Кнопка выхода
        exitButton.setOnAction(event -> {
            exitToAdmin();
        });
    };
    //Метод формирования поиска по времени
    // 1. Перевод времени в число по формуле (час * 60 + мин)
    // 2. Ввод в БД данных чисел
    // 3. Сравнение чисел
    // 4. Вывод информации о заказах в данном диапозоне
    public void giveWriterToTime() throws SQLException {
       String[] timeBeginOrderInScene = timeBegin.getText().split(":");
       String[] timeEndOrderInScene = timeEnd.getText().split(":");
       int finalTimeBeginOrderInScene = Integer.parseInt(timeBeginOrderInScene[0]) * 60 + Integer.parseInt(timeBeginOrderInScene[1]);
       int finalTimeEndOrderInScene = Integer.parseInt(timeEndOrderInScene[0]) * 60 + Integer.parseInt(timeEndOrderInScene[1]);
        typeProjects = connection.prepareStatement("SELECT * FROM rest.list_orders;");
        tp = typeProjects.executeQuery();
        sendingCommands = connection.createStatement();
        while (tp.next()){
            String [] timeBeginSQL = tp.getString("time_begin").split(":");
            int timeBegin = Integer.parseInt(timeBeginSQL[0]) * 60 + Integer.parseInt(timeBeginSQL[1]);
            sendingCommands.executeUpdate("UPDATE `rest`.`list_orders` SET `time_begin_int` = '"+ timeBegin + "' WHERE (`id` = '"+ tp.getString("id")+"');");
            String [] timeEndSQL = tp.getString("time_end").split(":");
            int timeEnd = Integer.parseInt(timeEndSQL[0]) * 60 + Integer.parseInt(timeEndSQL[1]);
            sendingCommands.executeUpdate("UPDATE `rest`.`list_orders` SET `time_end_int` = '"+ timeEnd + "' WHERE (`id` = '"+ tp.getString("id")+"');");
        }
        typeProjects = connection.prepareStatement("SELECT * FROM rest.list_orders WHERE (time_begin_int >= '"+ finalTimeBeginOrderInScene +"' && time_end_int <= '"+ finalTimeEndOrderInScene+"');");
        tp = typeProjects.executeQuery();
        String outInAreaText = "";
        while (tp.next()){
            outInAreaText = outInAreaText +"\n" + tp.getString("name_writer") + " served " + tp.getString("table_number") + " table in " + tp.getString("time_begin") + " to " + tp.getString("time_end");
        }
        searchWriterInTime.setText(outInAreaText);
    }
    //Метод выхода в сцену "Админ".
    public void exitToAdmin() {
        exitButton.getScene().getWindow().hide();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/BaseDataProject/admin.fxml"));
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }
}
