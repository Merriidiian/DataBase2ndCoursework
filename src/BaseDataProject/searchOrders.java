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

public class searchOrders {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button exitButton;

    @FXML
    private Button searchButton;

    @FXML
    private AnchorPane searchCookWindow;

    @FXML
    private TextField searchOrders;

    @FXML
    private TextArea searchOrdersText;

    @FXML
    void initialize() {
        searchButton.setOnAction(event -> {
            try {
                searchOrdersMethod();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });
        exitButton.setOnAction(event -> {
            exitToAdmin();
        });
    }
    void searchOrdersMethod () throws SQLException {
        String textOutInOrder = "";
        typeProjects = connection.prepareStatement("SELECT * FROM rest.list_orders WHERE (sum > '"+ Integer.parseInt(searchOrders.getText()) +"');");
        tp = typeProjects.executeQuery();
        sendingCommands = connection.createStatement();
        while (tp.next()){
            textOutInOrder = textOutInOrder + "\n" + "Order №" + tp.getString("id") + " was made in " + tp.getString("time_begin") + "-" + tp.getString("time_end") + ". Sum order = " + tp.getString("sum") + "$";
        }
        searchOrdersText.setText(textOutInOrder);
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

