package ui;

import model.AccountBook;
import persistence.Reader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.IOException;

import static ui.Main.ACCOUNTS_FILE;

public class MainFrameGUI extends JFrame implements ActionListener, WindowListener {

    private JLabel label1;
    private JLabel label2;
    DeleteTableGUI deletePanel;
    JPanel panelTable;
    AccountBook accountBook;
    JFrame frame;

    public MainFrameGUI(AccountBook accountBook) {
        super("Financial Manager");
        this.accountBook = accountBook;
        deletePanel = new DeleteTableGUI(this.accountBook);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setPreferredSize(new Dimension(500, 400));
        getContentPane().setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));

        add(titlePanel());

        JPanel button = buttonPanel();
        add(button);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setAlignmentY(Component.CENTER_ALIGNMENT);

        deletePanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        deletePanel.setAlignmentY(Component.CENTER_ALIGNMENT);
        add(deletePanel);
        deletePanel.setLayout(new GridLayout(0,1,1,3));

        addWindowListener(this);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("record")) {
            this.setVisible(false);
            new AddPurchaseGUI(this.accountBook);
        }
        if (e.getActionCommand().equals("load")) {
            this.setVisible(false);
            loadAccountBook();
            new LoadSuccessGUI(accountBook);
        }
        if (e.getActionCommand().equals("save")) {
            AddPurchaseGUI.saveAccountBook(accountBook);
            new SavedSuccessGUI(accountBook);
        }
        validate();
        repaint();
    }

    //EFFECTS: create a Jpanel in the main frame include: welcome line, total monet spend
    public JPanel titlePanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        label1 = new JLabel("Welcome to your financial manager");
        label1.setFont(new Font("Serif", Font.BOLD, 16));
        label1.setBorder(BorderFactory.createEmptyBorder(13,1,1,1));

        label1.setAlignmentX(Component.CENTER_ALIGNMENT);
        label1.setAlignmentY(Component.CENTER_ALIGNMENT);
        JLabel line = new JLabel("------------------------------------");
        line.setBorder(BorderFactory.createEmptyBorder(1, 1,13,1));
        line.setAlignmentX(Component.CENTER_ALIGNMENT);

        label2 = totalSpent();
        label2.setAlignmentX(Component.CENTER_ALIGNMENT);
        label2.setAlignmentY(Component.CENTER_ALIGNMENT);


        panel.add(label1);
        panel.add(label2);
        panel.add(line);

        return panel;
    }

    //EFFECTS: create a button Jpanel include three button: load, save and add purchase
    public JPanel buttonPanel() {
        JPanel button = new JPanel();
        button.setPreferredSize(new Dimension(100, 50));
        button.setMaximumSize(new Dimension(500, 50));

        JButton btn1 = new JButton("record a new purchase");
        btn1.setActionCommand("record");
        btn1.addActionListener(this);

        JButton btn2 = new JButton("load");
        btn2.setActionCommand("load");
        btn2.addActionListener(this);

        JButton btn3 = new JButton("save");
        btn3.setActionCommand("save");
        btn3.addActionListener(this);

        button.add(btn2);
        button.add(btn1);
        button.add(btn3);
        //button.setBorder(BorderFactory.createLineBorder(Color.lightGray));

        return button;
    }



    //EFFECTS: print the total money spent in the account book
    public JLabel totalSpent() {
        String totalCost = "Total money spent:  $" + accountBook.moneySpent();
        JLabel label = new JLabel(totalCost);
        return label;
    }


    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {
        if (JOptionPane.showConfirmDialog(frame,
                "Are you sure you want to close this window?", "Close Window?",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
            //AddPurchaseGUI.saveAccountBook(accountBook);
            System.exit(0);
        }
    }

    @Override
    public void windowClosed(WindowEvent e) {

    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

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
