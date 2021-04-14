package ui;

import model.AccountBook;

import static ui.Main.ACCOUNTS_FILE;

public class LoadSuccessGUI extends SuccessMessageGUI {
    public LoadSuccessGUI(AccountBook accountBook) {
        super(accountBook, "Successfully load your account book from" + ACCOUNTS_FILE);
    }
}
