package sample.MainPage;

import javafx.fxml.FXMLLoader;

public class account {
    private int id;
    private int idc;
    private String AcountNumber;
    private double amount;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdc() {
        return idc;
    }

    public void setIdc(int idc) {
        this.idc = idc;
    }

    public String getAcountNumber() {
        return AcountNumber;
    }

    public void setAcountNumber(String acountNumber) {
        AcountNumber = acountNumber;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public account(int id, int idc, String acountNumber, double amount) {
        this.id = id;
        this.idc = idc;
        AcountNumber = acountNumber;
        this.amount = amount;
    }

    @Override
    public String toString() {
        return AcountNumber;
    }

    public account(int idc, String acountNumber, double amount) {
        this.idc = idc;
        this.AcountNumber = acountNumber;
        this.amount = amount;
    }

    public void MainWindowLoader(FXMLLoader userData) {
    }
}
