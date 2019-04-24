package sample.Login;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.Database.Globals;

public class Controller implements Initializable {

    @FXML
    private TextField textLogin;

    @FXML
    private PasswordField textPassword;

    Stage dialogStage = new Stage();
    Scene scene;

    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    public Controller() {

        connection = Globals.connectdb();
    }

    public Employee loginAction(ActionEvent event){
        String login = textLogin.getText();
        String password = textPassword.getText();

        String sql = "SELECT * FROM LoginEmp WHERE login = ? and password = ?";

        try{
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            resultSet = preparedStatement.executeQuery();
            if(!resultSet.next()){
                infoBox("Incorrect Login and Password", null, "Failed");
            }else{
                infoBox("Login Successfull",null,"Success" );
                Node node = (Node)event.getSource();
                dialogStage = (Stage) node.getScene().getWindow();
                dialogStage.close();
                scene = new Scene(FXMLLoader.load(getClass().getResource("sample1.fxml")));
                dialogStage.setScene(scene);
                dialogStage.show();

            }

            ResultSet rs;

            try {
                PreparedStatement stmnt = connection.prepareStatement(sql);
                rs = stmnt.executeQuery();
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String fname = rs.getString("fname");
                    String lname = rs.getString("lname");
                    int position = rs.getInt("position");

                    Employee employee = new Employee(id, fname, lname, position);
                    return employee;
                }
                connection.close();


            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static void infoBox(String infoMessage, String headerText, String title){
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setContentText(infoMessage);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.showAndWait();
    }



    @Override
    public void initialize(URL url, ResourceBundle rb) {


    }
}
