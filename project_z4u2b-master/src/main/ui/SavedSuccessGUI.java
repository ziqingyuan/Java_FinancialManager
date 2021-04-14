package ui;

import model.AccountBook;

import java.awt.event.ActionEvent;

import static ui.Main.ACCOUNTS_FILE;

public class SavedSuccessGUI extends SuccessMessageGUI {
    public SavedSuccessGUI(AccountBook accountBook) {
        super(accountBook, "Successfully save your account book to" + ACCOUNTS_FILE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("OK")) {
            this.setVisible(false);
        }
    }
}
