package BaseDataProject;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;;

public class Admin {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane adminWindow;

    @FXML
    private Button searchCook;

    @FXML
    private Button searchFood;

    @FXML
    private Button searchOrder;

    @FXML
    private Button searchWriter;

    @FXML
    void initialize() {
        // Кнопка перехода на поиск официанта по времени обсжуживания
        searchWriter.setOnAction(event -> {
            searchWriterButton();
        });
        searchCook.setOnAction(event -> {
            searchCookButton();
        });
        searchOrder.setOnAction(event -> {
            searchOrderButton();
        });
    }
    public void searchWriterButton(){
        //Метод перехода на сцену поиска официанта по времени обслуживания
        searchWriter.getScene().getWindow().hide();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/BaseDataProject/searchWriter.fxml"));
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Поиск официанта");
        stage.show();
    }
    public void searchCookButton(){
        //Метод перехода на сцену поиска официанта по времени обслуживания
        searchCook.getScene().getWindow().hide();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/BaseDataProject/searchCook.fxml"));
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Поиск повара");
        stage.show();
    }
    public void searchOrderButton() {
        //Метод перехода на сцену поиска заказов по минимальной цене
        searchCook.getScene().getWindow().hide();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/BaseDataProject/searchOrders.fxml"));
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Поиск заказов");
        stage.show();
    }

}
