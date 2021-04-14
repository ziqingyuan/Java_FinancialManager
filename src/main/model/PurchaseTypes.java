package model;

import java.util.LinkedList;

//the list of purchase type in a account book
//!!!!!!may change to enumeration later (found it will be more simple to do so)
public class PurchaseTypes {
    LinkedList<String> types;
    //LinkedList<String> customizeTypes;

    public PurchaseTypes() {
        types = new LinkedList<String>();
        types.add("grocery");
        types.add("dining");
        types.add("clothing");
        types.add("accommodation");
        //customizeTypes = new LinkedList<String>();
    }

    //MODIFIED: this
    //EFFECTS: add a new type to the type list
    public void addPurchaseType(String s) {
        types.add(s);
    }


    //public boolean ifContain(String s) {
//        return this.types.contains(s);
//    }

    //EFFECTS: return the type of the types
    public LinkedList<String> getTypes() {
        return types;
    }

    //EFFECTS: return the size of the type
    public int getSize() {
        return types.size();
    }

    //EFFECT: return true if the type list contain the given type
    public boolean ifContain(String type) {
        return this.types.contains(type);
    }

}
