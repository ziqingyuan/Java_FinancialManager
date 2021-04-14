package ui;

import exception.InvalidNumberEntered;
import exception.InvalidSpent;
import model.AccountBook;
import model.Purchase;
import persistence.Reader;
import persistence.Writer;


import java.io.*;
import java.util.*;

public class Main {
    static AccountBook accountBook;
    private Scanner input;
    public static final String ACCOUNTS_FILE = "./data/accountBook.txt";

    // EFFECTS: runs the financial application
    public static void main(String[] args) {
        //new Main();
        new MainFrameGUI(new AccountBook());
    }

    public Main() {
        loadAccountBook();
    }


    // MODIFIES: this
    // EFFECTS: processes user input
    private void runManager() {
        boolean keepGoing = true;
        String command;
        input = new Scanner(System.in);

        loadAccountBook();

        System.out.println("Welcome to your personal Financial Manager!");

        while (keepGoing) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }

        System.out.println("\nGoodbye!");
    }

    // MODIFIES: this
    // EFFECTS: initializes accounts
    private void init() {
        accountBook = new AccountBook();
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
        if (command.equals("r")) {
            doAddPurchase();
        } else if (command.equals("d")) {
            deletePurchase();
        } else if (command.equals("m")) {
            totalSpent();
        } else if (command.equals("n")) {
            numberPurchased();
        } else if (command.equals("u")) {
            printBook();
        } else if (command.equals("s")) {
            saveAccountBook();
        } else {
            System.out.println("Selection not valid...");
        }

    }


    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\tr -> record a new purchase");
        System.out.println("\td -> delete a purchase");
        System.out.println("\tm -> check total money spent");
        System.out.println("\tn -> check total number purchased");
        System.out.println("\tu -> show the list");
        System.out.println("\ts -> save your account book");
        System.out.println("\tq -> quit");
    }

    // EFFECTS: displays menu of options to user to choose type
    private void displayTypeMenu() {
        System.out.print("Choose Type of Purchase: ");
        System.out.println("\nSelect from:");
        int number = 0;

        for (String type: accountBook.getTypeList()) {
            number = number + 1;
            System.out.println("\t" + number + " -> " + type);
        }

        System.out.println("\t" + (number + 1)  + " -> " + "add new type");
    }

    // MODIFIES: this
    // EFFECTS: do a add to the account book
    private void doAddPurchase() {
        int command;
        String billType;
        int purchaseAmount;
        String purchaseCommand;

        displayTypeMenu();
        command = input.nextInt();

        if (command > this.accountBook.getTypeList().size()) {
            System.out.println("Enter your new purchase type:");
            billType = input.next();
        } else {
            billType = accountBook.getTypeList().get(command - 1);
        }

        System.out.println("Enter the dollar you spent: ");
        purchaseAmount = (int) (input.nextDouble() * 100);

        System.out.println("Enter your comment for this purchase");
        purchaseCommand = input.next();
        purchaseCommand += input.nextLine();

        try {
            this.accountBook.addPurchase(purchaseAmount, billType, purchaseCommand);
            System.out.println("Purchase successfully recorded!");
        } catch (InvalidSpent invalidSpent) {
            System.out.println("Amount entered invalid!!!!");
        }

    }

    public void deletePurchase() {
        printBook();
        if (accountBook.numberOfPurchases() == 0) {
            System.out.println("Your accountbook is already empty!");
        } else {
            System.out.println("Which purchase would you like to delete?");
            int deleteNum = input.nextInt();

            try {
                this.accountBook.delete(deleteNum);
                System.out.println("Delete successful!");
            } catch (InvalidNumberEntered invalidNumberEntered) {
                System.out.println("Sorry, unable to delete");
            }
        }

    }

    // EFFECTS: prints purchase of account book to the screen
    public void printBook() {
        System.out.println("Here if your Account Book:");

        String billList = "";
        int purchaseNum = 0;
        for (Purchase p: accountBook.getPurchaseList()) {
            purchaseNum = purchaseNum + 1;
            double moneySpent = p.getMoneyPurchased();
            moneySpent = moneySpent / 100;
            String amountMoney = String.format("%.2f", moneySpent);
            String name = p.getType();
            String command = p.getCommand();

            billList = billList + purchaseNum + ".  " + name + "  $" + amountMoney + " ---- " + command + "\n";
        }

        System.out.println(billList);
    }

    //EFFECTS: print the total money spent in the account book
    public void totalSpent() {
        System.out.println("the total money you spent is:  $" + accountBook.moneySpent());
    }

    //EFFECTS: print the the number of purchase recorded in the account book
    private void numberPurchased() {
        System.out.println("You have " + accountBook.numberOfPurchases() + " purchase has been recorded");
    }

    // REQUIRES:
    // MODIFIES:
    // EFFECTS: save the account book to the accountBook file.
    private void saveAccountBook() {
        try {
            Writer writer = new persistence.Writer(new File(ACCOUNTS_FILE));
            writer.write(accountBook);
            writer.close();
            System.out.println("Accounts saved to file " + ACCOUNTS_FILE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to save accounts to " + ACCOUNTS_FILE);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            // this is due to a programming error
        }
    }

    // EFFECTS: load the account book.
    private void loadAccountBook() {
        AccountBook accountBook;
        try {
            accountBook = Reader.readAccounts(new File(ACCOUNTS_FILE));
        } catch (IOException e) {
            accountBook = new AccountBook();
            System.out.println("new Account Book created");
        }
        this.accountBook = accountBook;
    }

}
