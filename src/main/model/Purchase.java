package model;

//represent a single purchase with its type and the amount money it spent
public class Purchase {
    int moneyPurchased;
    String type;
    String command;


    public Purchase(int i, String type, String command) {
        this.moneyPurchased = i;
        this.type = type;
        this.command = command;
    }

    //EFFECTS: return the type of this purchase
    public String getType() {
        return this.type;
    }

    //EFFECTS: return the amount of money in this purchase
    public int getMoneyPurchased() {
        return this.moneyPurchased;
    }

    public String getCommand() {
        return this.command;
    }

}
