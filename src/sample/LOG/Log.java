package sample.LOG;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import sample.*;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Log implements Initializable {

    public Label LogName;
    public Label LogSurname;
    public Label LogPosition;
    public Label Name;
    public Label Surname;
    public Label Mail;
    public Label accountIDField;
    public Label accNumField;
    public Label amountField;
    public Label newPass;

    public CheckBox blockCheck;

    public Button logout;

    ArrayList<Client> clientList;
    ArrayList<Account> accList;
    ArrayList<Card> cardList;

    @FXML
    ComboBox combobox;
    @FXML
    ComboBox comboboxAcc;
    @FXML
    ComboBox comboboxCards;


    public void Afterlog(Employee person, String position) {

        String name = person.getFirstname();
        String surname = person.getLastName();

        LogName.setText(name);
        LogSurname.setText(surname);
        LogPosition.setText(position);

    }

    public void logout(ActionEvent actionEvent) {
        Stage stage = (Stage) logout.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        fillDropdownClients();
    }

    public void fillDropdownClients(){
        clientList = Globals.db.selectClientsToList();
        ObservableList<String> oblist = FXCollections.observableArrayList();

        for(int i=0; i<clientList.size();i++) {
            oblist.add(clientList.get(i).getFirstName() + " " + clientList.get(i).getLastName());
        }

        combobox.setItems(oblist);
        System.out.println(combobox.getItems().size());
    }


    public void fillDropdownAccounts(){
        accList = Globals.db.selectAccountsToList(getIDClient());
        ObservableList<String> oblist = FXCollections.observableArrayList();

        for(int i=0; i<accList.size();i++) {
            oblist.add(accList.get(i).getIdAcc() + " " + accList.get(i).getAccNum());
        }

        comboboxAcc.setItems(oblist);
    }

    public void fillDropdownCards(){
        cardList = Globals.db.clientCards(getIDAccount());
        ObservableList<String> oblist = FXCollections.observableArrayList();

        for(int i=0; i<cardList.size();i++) {
            oblist.add(cardList.get(i).getId() + " " + cardList.get(i).getIda() + " yy/mm " +cardList.get(i).getExpireM() +"/"+ cardList.get(i).getExpireY());
        }

        comboboxCards.setItems(oblist);
        System.out.println(comboboxCards.getItems().size());
    }

    public void clientInfo(){

        Client selectedUser=Globals.db.selectClientInfo(getIDClient());

        Name.setText(selectedUser.getFirstName());
        Surname.setText(selectedUser.getLastName());
        Mail.setText(String.valueOf(selectedUser.getEmail()));

        fillDropdownAccounts();

    }

    public void AccInfo(){

        Account acc = Globals.db.selectAccInfo(getIDAccount());
        accountIDField.setText(String.valueOf(acc.getIdAcc()));
        accNumField.setText(acc.getAccNum());
        amountField.setText(String.valueOf(acc.getMoney()));

        fillDropdownCards();
    }

    public int getIDClient() {

        return clientList.get(combobox.getSelectionModel().getSelectedIndex()).getId();
    }

    public int getIDAccount() {

        return accList.get(comboboxAcc.getSelectionModel().getSelectedIndex()).getIdAcc();
    }

    public void unblockBtnAction(ActionEvent actionEvent) {

        Globals.db.unblockCard(getIDClient(),getIDAccount());
    }

    public void blockBtnAction(ActionEvent actionEvent) {

        Globals.db.blockCard(getIDClient(),getIDAccount());
    }


    public void blockCheckAction(ActionEvent actionEvent) {

        if(blockCheck.isSelected()) {
            Globals.db.blockUserLogin(getIDClient());
        }else {
            Globals.db.unblockUserLogin(getIDClient());
        }

    }
}