package ui;

import exception.InvalidNumberEntered;
import model.AccountBook;
import model.Purchase;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Vector;

import javax.swing.*;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;

public class DeleteTableGUI extends JPanel implements ActionListener, PopupMenuListener {
    AccountBook accountBook;
    JTable table;
    JPopupMenu popupMenu;
    JFrame frame;


    public DeleteTableGUI(AccountBook accountBook) {
        this.accountBook = accountBook;
        Vector<String> columns = new Vector<String>(Arrays.asList("#", "Type", "Amount($)", "Commend"));
        Vector<Vector<String>> data = new Vector<Vector<String>>();
        LinkedList<Purchase> purchases = accountBook.getPurchaseList();
        int i = 0;

        for (Purchase p: purchases) {
            Vector<String> row = new Vector<String>();
            i = i + 1;
            row.add(Integer.toString(i));
            row.add(p.getType());
            double amountInDollar = (double) p.getMoneyPurchased() / 100;
            row.add(Double.toString(amountInDollar));
            row.add(p.getCommand());
            data.add(row);
        }

        table = new JTable(data, columns);
        popupMenu = new JPopupMenu();


        JMenuItem deleteItem = new JMenuItem("Delete");
        deleteItem.addActionListener(this);
        popupMenu.add(deleteItem);
        popupMenu.addPopupMenuListener(this);
        table.setComponentPopupMenu(popupMenu);
        add(new JScrollPane(table), BorderLayout.CENTER);
    }




    @Override
    public void actionPerformed(ActionEvent e) {
        deletePurchase();
        this.setVisible(false);
        new DeleteTableGUI(accountBook);
    }

    @Override
    public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                int rowAtPoint = table.rowAtPoint(SwingUtilities.convertPoint(popupMenu, new Point(0, 0), table));
                if (rowAtPoint > -1) {
                    table.setRowSelectionInterval(rowAtPoint, rowAtPoint);
                }
            }
        });
    }

    @Override
    public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
    }

    @Override
    public void popupMenuCanceled(PopupMenuEvent e) {
    }

    //EFFECTS: delete a select purchase
    public void deletePurchase() {
        if (accountBook.numberOfPurchases() == 0) {
            JOptionPane.showMessageDialog(frame, "Your accountbook is already empty!");
        } else {
            int deleteNum = table.getSelectedRow();
            try {
                this.accountBook.delete(deleteNum);
                //this.setVisible(false);
                new DeleteSuccessGUI(this.accountBook);
            } catch (InvalidNumberEntered invalidNumberEntered) {
                JOptionPane.showMessageDialog(frame, "Sorry, unable to delete");
            }
        }

    }
}