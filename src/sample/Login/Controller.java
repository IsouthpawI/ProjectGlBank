package sample.Login;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.Globals;
import sample.LOG.Log;
import sample.Employee;

import java.io.IOException;


public class Controller {

    public TextField username;
    public TextField pass;
    public Button button;
    public Label errorLabel;

    public void login(ActionEvent actionEvent) {
        String name = username.getText();
        String password = pass.getText();
        Employee person = Globals.db.compareEmployee(name,password);

        if(person != null){
            System.out.println(person.getFirstname());
            System.out.println(person.getPosition());
            String position = Globals.db.getPosition(person);
            Stage stage = (Stage) button.getScene().getWindow();
            stage.close();

            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../LOG/Log.fxml"));
                Parent root1 = fxmlLoader.load();
                Stage stage2 = new Stage();
                stage2.setTitle("LOG");
                stage2.setScene(new Scene(root1));

                Log acc;
                acc = fxmlLoader.getController();
                acc.Afterlog(person,position);

                stage2.show();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if(person == null){
            System.out.println("Login Failed!");
            username.setText("");
            pass.setText("");
            errorLabel.setText("Incorrect username or password!");

        }

    }
}
