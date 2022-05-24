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
import static BaseDataProject.Main.connection;

public class searchCook {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button exitButton;

    @FXML
    private Button searchButton;

    @FXML
    private TextArea searchCookInDish;

    @FXML
    private AnchorPane searchCookWindow;

    @FXML
    private TextField searchDish;

    @FXML
    void initialize() {
        searchButton.setOnAction(event -> {
            try {
                searchDishToCook();
            } catch (SQLException throwables) {
                searchCookInDish.setText("Dish non search!");
                throwables.printStackTrace();
            }
        });
        exitButton.setOnAction(event -> {
            exitToAdmin();
        });
    }
    public void searchDishToCook() throws SQLException {
        String dishForSearch = searchDish.getText();
        typeProjects = connection.prepareStatement("SELECT * FROM rest.list_orders WHERE (orders = '"+ dishForSearch +"');");
        tp = typeProjects.executeQuery();
        sendingCommands = connection.createStatement();
        String fullText = "";
        while (tp.next()){
            fullText = fullText + "\n" + tp.getString("name_cook") + " cooking " + searchDish.getText() + " for " + tp.getString("table_number") + " table!";
        }
        searchCookInDish.setText(fullText);
    }
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
        stage.setTitle("Админ");
        stage.show();
    }

}
