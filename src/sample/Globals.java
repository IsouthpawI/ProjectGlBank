package sample;

import sample.database.Database;

public class Globals {

    public static String username= "root";
    public static String password= "";
    public static final String url = "jdbc:mysql://localhost:3306/glbank";
    public static final Database db = Database.getInstance();

}
