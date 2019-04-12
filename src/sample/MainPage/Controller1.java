package sample.MainPage;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

import com.sun.javafx.charts.Legend;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.Database.Globals;

public class Controller1 {

    @FXML
    private TextField whoLog;

    @FXML
    private TextField textLogin;

    Stage dialogStage = new Stage();
    Scene scene;

    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;



    public Controller1() {

        connection = Globals.connectdb();
    }

    public void logoutAction(ActionEvent actionEvent) {

        Node node = (Node) actionEvent.getSource();
        dialogStage = (Stage) node.getScene().getWindow();
        dialogStage.setScene(scene);
        dialogStage.close();

    }

}
