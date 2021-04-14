package persistence;



import model.AccountBook;
import model.Purchase;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Reader {
    public static final String DELIMITER = "3&3@98";

    // EFFECTS: returns a list of accounts parsed from file; throws
    // IOException if an exception is raised when opening / reading from file
    public static AccountBook readAccounts(File file) throws IOException {
        List<String> fileContent = readFile(file);
        return parseContent(fileContent);
    }

    // EFFECTS: returns content of file as a list of strings, each string
    // containing the content of one row of the file
    private static List<String> readFile(File file) throws IOException {
        return Files.readAllLines(file.toPath());
    }

    // EFFECTS: returns a list of accounts parsed from list of strings
    // where each string contains data for one account
    private static AccountBook parseContent(List<String> fileContent) throws IOException {
        AccountBook accountBook = new AccountBook();

        for (String line : fileContent) {
            ArrayList<String> lineComponents = splitString(line);
     //       if (line.length() != 0) {
            accountBook.addPurchase(parseAccount(lineComponents));
     //       } else {
     //           throw new Error();
    //        }
        }

        return accountBook;
    }

    // EFFECTS: returns a list of strings obtained by splitting line on DELIMITER
    private static ArrayList<String> splitString(String line) throws IOException {
        //if (line.length() != 0) {
        String[] splits = line.split(DELIMITER);
        return new ArrayList<>(Arrays.asList(splits));
        //}
//        else {
//            throw new IOException();
//        }
    }

    // REQUIRES: components has size 4 where element 0 represents the
    // id of the next account to be constructed, element 1 represents
    // the id, elements 2 represents the name and element 3 represents
    // the balance of the account to be constructed
    // EFFECTS: returns an account constructed from components
    private static Purchase parseAccount(List<String> components) {
        int amountPaid = Integer.parseInt(components.get(1));
        String purchaseType = components.get(0);
        String purchaseCommand = components.get(2);
        return new Purchase(amountPaid, purchaseType, purchaseCommand);
    }
}
