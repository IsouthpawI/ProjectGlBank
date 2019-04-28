package sample;

public class Account {

    private int IdAcc;
    private String AccNum;
    private double money;

    public Account(int IdAcc, String accNum, double money) {
        this.IdAcc = IdAcc;
        this.AccNum = accNum;
        this.money = money;
    }

    public int getIdAcc() {

        return IdAcc;
    }

    public void setIDacc(int IDacc) {

        this.IdAcc = IDacc;
    }

    public String getAccNum() {

        return AccNum;
    }

    public void setAccNum(String accNum) {

        AccNum = accNum;
    }

    public double getMoney() {

        return money;
    }

    public void setMoney(double money) {

        this.money = money;
    }
}
