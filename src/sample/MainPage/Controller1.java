package sample.MainPage;

import java.io.IOException;
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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import sample.Database.Globals;
import sample.Login.Client;
import sample.Login.Employee;

public class Controller1 {

    @FXML
    private TextField whoLog;

    @FXML
    private TextField textLogin;

    public Label empname;

    private Employee employee;

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

    public void setEmployee(Employee employee) {
        this.employee = employee;
        empname.setText(employee.getFname() + " " + employee.getLname());


    }

    public void newAccount(ActionEvent actionEvent) {
        try {
            Stage stage = new Stage();
            stage.setTitle("NEW account");

            FXMLLoader loader = new FXMLLoader(getClass().getResource("account.fxml"));
            Parent root = loader.load();
            account NewAccount = loader.getController();

            Scene scene = ((Node) (actionEvent.getSource())).getScene();
            NewAccount.MainWindowLoader((FXMLLoader) scene.getUserData());
            stage.setScene(new Scene(root, 623, 545));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
