package sample.Login;

public class Employee {

    public String fname;
    public String lname;
    public int id;
    public int position;

    public Employee(int id,String fname,String lname,int position){
        this.fname=fname;
        this.lname=lname;
        this.position=position;
        this.id=id;
    }

    public String getFname() {
        return fname;
    }

    public String getLname() {
        return lname;
    }

}
