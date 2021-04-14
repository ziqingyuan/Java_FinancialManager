package model;

import exception.InvalidNumberEntered;
import exception.InvalidSpent;
import persistence.Saveable;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

//Record a list of purchase which is the main body of the account book
public class AccountBook implements Saveable {
    LinkedList<Purchase> purchaseList;
    PurchaseTypes types;


    public AccountBook() {
        purchaseList = new LinkedList<Purchase>();
        types = new PurchaseTypes();
    }

    //MODIFIED: this
    //EFFECTS: add a single purchase(in dollar) to the account book, add type to type list of origin doesn't contain
    public Boolean addPurchase(Double amount, String type, String command) throws InvalidSpent {
        int intAmount = (int)(amount * 100);
        Purchase purchase = new Purchase(intAmount, type, command);
        if (!this.types.ifContain(type)) {
            addPurchaseType(type);
        }

        if (amount <= 0) {
            throw new InvalidSpent();
        }

        purchaseList.add(purchase);
        return true;
    }

    //MODIFIED: this
    //EFFECTS: add a single purchase(in dollar) to the account book, add type to type list of origin doesn't contain
    public Boolean addPurchase(int amount, String type, String command) throws InvalidSpent {
        int centAmount = amount * 100;
        Purchase purchase = new Purchase(centAmount, type, command);
        if (!this.types.ifContain(type)) {
            addPurchaseType(type);
        }

        if (amount <= 0) {
            throw new InvalidSpent();
        }

        purchaseList.add(purchase);
        return true;
    }

    //MODIFIED: this
    //EFFECTS: add a single purchase to the account book, add type to type list of origin doesn't contain
    public void addPurchase(Purchase p) {
        if (!this.types.ifContain(p.getType())) {
            addPurchaseType(p.getType());
        }
        this.purchaseList.add(p);
    }

    //MODIFIED: this
    //EFFECTS: add a new type to the type list
    public void addPurchaseType(String s) {
        types.addPurchaseType(s);
    }

    //EFFECT: get the total amount of purchase recorded
    public int numberOfPurchases() {
        return purchaseList.size();
    }

    //EFFECTS: get the total money that has been spent in dollar.
    public double moneySpent() {
        double  m = 0;

        for (Purchase p: purchaseList) {
            double moneySpent = p.getMoneyPurchased();
            moneySpent = moneySpent / 100;
            m = m + moneySpent;
        }

        String amountMoney = String.format("%.2f", m);
        return Double.parseDouble(amountMoney);
    }

    //EFFECTS: return the all the types in the account book
    public LinkedList<String> getTypeList() {
        return types.getTypes();
    }

    //EFFECTS: return all the purchases in the account book
    public LinkedList<Purchase> getPurchaseList() {
        return purchaseList;
    }

    public void delete(int i) throws InvalidNumberEntered {
        if (i >= 0 && i <= this.purchaseList.size()) {
            this.purchaseList.remove(i);
        } else {
            throw new InvalidNumberEntered();
        }
    }

    @Override
    public void save(PrintWriter printWriter) {
        List<String> lines = new ArrayList<>();
        for (Purchase a : getPurchaseList()) {
            lines.add(a.getType() + "3&3@98" + a.getMoneyPurchased() + "3&3@98" + a.getCommand());
        }
        for (String l : lines) {
            printWriter.println(l);
        }
    }
}
