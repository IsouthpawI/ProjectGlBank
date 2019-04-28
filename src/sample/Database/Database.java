package sample.database;

import sample.*;

import java.sql.*;
import java.util.ArrayList;

public class Database {

    public static final String Employee = "SELECT * FROM Employee INNER JOIN loginEmp ON Employee.ID = loginEmp.IDE WHERE login LIKE ? and password LIKE ?";
    public static final String Client = "SELECT * FROM Client";
    public static final String Account = "SELECT * FROM Account where IDC like ? ";
    public static final String ClientInfo = "SELECT * FROM Client where ID like ? ";
    public static final String AccountInfo = "SELECT * FROM Account where id like ? ";
    public static final String Cards = "SELECT * FROM card WHERE ida LIKE ?";
    public static final String UnblockCard = "UPDATE card SET Active = 1  WHERE id = ? and ida = ?";
    public static final String BlockCard = "UPDATE card SET Active = 0  WHERE id = ? and ida = ?";
    public static final String BlockUser = "INSERT INTO loginhistory(idl) VALUES (?)";
    public static final String UnblockUser = "INSERT INTO loginhistory(idl,success) VALUES(?,1)";

    private static Database db = new Database();

    private Database(){

    }

    public static Database getInstance() {
        return db;
    }

    public static Connection getConnection(){
        Connection connection;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("DriverLoaded");
            connection = DriverManager.getConnection(Globals.url, Globals.username, Globals.password);
            return connection;
        }
        catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        catch (SQLException e){
            e.printStackTrace();
        }

        return null;
    }

    public Employee compareEmployee(String name, String pass){

        Connection conn = getConnection();
        PreparedStatement pst = null;
        ResultSet rs;

        try {

            pst = conn.prepareStatement(Employee);
            pst.setString(1,name);
            pst.setString(2,pass);
            rs = pst.executeQuery();
            while (rs.next()) {
                System.out.println("OK");

                Employee person = new Employee(rs.getString("fname"), rs.getString("lname"),
                        rs.getInt("position"));
                return person;
            }
            conn.close();

        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public String getPosition(Employee emp){
        int position = emp.getPosition();
        String boss = "BOSS";
        String common = "COMMON";

        if (position == 1) {
            return common;
        }
        else
        {
            return boss;
        }
    }

    public ArrayList<Client> selectClientsToList(){
        Connection conn = getConnection();
        ArrayList <Client> clientList = new ArrayList<>();

        try {
            PreparedStatement pst = null;
            ResultSet rs = null;
            pst = conn.prepareStatement(Client);
            rs = pst.executeQuery();

            while (rs.next()) {

                Client client = new Client(rs.getString("fname"),rs.getString("lname"),rs.getString("email"),rs.getInt("ID"));
                clientList.add(client);
            }
            return clientList;

        }catch(SQLException e){
            e.printStackTrace();
        }
        return null;

    }

    public ArrayList<Account> selectAccountsToList(int id) {

        Connection conn = getConnection();

        ArrayList <Account> accounts = new ArrayList<>();
        try {

            PreparedStatement pst = null;
            ResultSet rs = null;
            pst = conn.prepareStatement(Account);
            pst.setInt(1,id);
            rs = pst.executeQuery();
            while (rs.next()) {

               Account acc = new Account(rs.getInt("ID"),rs.getString("AccNum"),rs.getDouble("amount"));
                System.out.println(acc.getAccNum());
                accounts.add(acc);
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        return accounts;
    }

    public Client selectClientInfo(int id){
        Connection conn = getConnection();
        Client client = null;

        try {
            PreparedStatement pst = null;
            ResultSet rs = null;
            pst = conn.prepareStatement(ClientInfo);
            pst.setInt(1,id);
            rs = pst.executeQuery();

            while (rs.next()) {

                client = new Client(rs.getString("fname"),rs.getString("lname"),rs.getString("email"),
                rs.getInt("ID"));

            }
            return client;

        }catch(SQLException e){
            e.printStackTrace();
        }

        return null;
    }

    public Account selectAccInfo(int id){
        Connection conn = getConnection();
        Account acc = null;

        try {
            PreparedStatement pst = null;
            ResultSet rs = null;
            pst = conn.prepareStatement(AccountInfo);
            pst.setInt(1,id);
            rs = pst.executeQuery();

            while (rs.next()) {

                acc = new Account(rs.getInt("id"),rs.getString("AccNum"),rs.getDouble("amount"));
            }
            return acc;

        }catch(SQLException e){
            e.printStackTrace();
        }

        return null;
    }

    public ArrayList<Card> clientCards(int idacc){
        Connection conn = getConnection();
        ArrayList <Card> cardList = new ArrayList<>();

        try {
            PreparedStatement pst = null;
            ResultSet rs = null;
            pst = conn.prepareStatement(Cards);
            pst.setInt(1,idacc);
            rs = pst.executeQuery();

            while (rs.next()) {

                Card card = new Card(rs.getInt("id"),rs.getInt("ida"),rs.getInt("ExpireM"),rs.getInt("ExpireY"));
                cardList.add(card);
            }
            return cardList;

        }catch(SQLException e){
            e.printStackTrace();
        }
        return null;

    }

    public int unblockCard(int id , int ida ){

        Connection conn = getConnection();

        try {
            PreparedStatement pst = null;
            ResultSet rs = null;
            pst = conn.prepareStatement(UnblockCard);
            pst.setInt(1,id);
            pst.setInt(2,ida);
            int rslt= pst.executeUpdate();

            System.out.println("Card is active");
            return rslt;


        }catch(SQLException e){
            e.printStackTrace();
        }

        return 0;

    }

    public int blockCard(int id , int ida ){

        Connection conn = getConnection();

        try {
            PreparedStatement pst = null;
            ResultSet rs = null;
            pst = conn.prepareStatement(BlockCard);
            pst.setInt(1,id);
            pst.setInt(2,ida);
            int rslt= pst.executeUpdate();

            System.out.println("Card is blocked");
            return rslt;


        }catch(SQLException e){
            e.printStackTrace();
        }

        return 0;

    }

    public void blockUserLogin(int idc){

        Connection con = getConnection();

        try {
            PreparedStatement stmnt = con.prepareStatement(BlockUser);
            stmnt.setInt(1,idc);
            stmnt.execute();
            con.close();
            System.out.println("User Blocked");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void unblockUserLogin(int idc){

        Connection con = getConnection();

        try {
            PreparedStatement stmnt = con.prepareStatement(UnblockUser);
            stmnt.setInt(1,idc);
            stmnt.execute();
            con.close();
            System.out.println("User Unblocked");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
