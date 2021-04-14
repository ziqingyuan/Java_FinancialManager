package ui;

import model.AccountBook;

public class DeleteSuccessGUI extends SuccessMessageGUI {

    public DeleteSuccessGUI(AccountBook accountBook) {
        super(accountBook, "this purchase has been deleted successfully.");
    }

}