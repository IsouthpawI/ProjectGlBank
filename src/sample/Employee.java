package sample;

public class Employee {

    private String Firstname;
    private String Lastname;
    private int position;

    public Employee(String firstname, String lastname, int position) {
        this.Firstname = firstname;
        this.Lastname = lastname;
        this.position = position;
    }

    public String getFirstname() {

        return Firstname;
    }

    public String getLastName() {

        return Lastname;
    }

    public int getPosition() {

        return position;
    }

}
