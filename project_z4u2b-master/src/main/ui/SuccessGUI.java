package ui;

import model.AccountBook;

public class SuccessGUI extends SuccessMessageGUI {

    public SuccessGUI(AccountBook accountBook) {
        super(accountBook, "Your Task has been added successfully.");
        AddPurchaseGUI.playSound("success");
    }
}
