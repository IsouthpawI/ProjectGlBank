package sample;

public class Card {

    private int id;
    private int ida;
    private int expireM;
    private int expireY;

    public Card(int id, int ida, int expireM, int expireY) {
        this.id = id;
        this.ida = ida;
        this.expireM = expireM;
        this.expireY = expireY;
    }

    public int getId() {

        return id;
    }

    public void setId(int id) {

        this.id = id;
    }

    public int getIda() {

        return ida;
    }

    public void setIda(int ida) {

        this.ida = ida;
    }

    public int getExpireM() {

        return expireM;
    }

    public void setExpireM(int expireM) {

        this.expireM = expireM;
    }

    public int getExpireY() {

        return expireY;
    }

    public void setExpireY(int expireY) {

        this.expireY = expireY;
    }
}
