package ui;

import exception.InvalidSpent;
import model.AccountBook;
import persistence.Writer;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.LinkedList;

import static ui.Main.ACCOUNTS_FILE;

public class AddPurchaseGUI extends JFrame implements ActionListener, ItemListener, WindowListener {
    AccountBook accountBook;
    static JComboBox<String> comboBox;
    JFrame frame;
    private JTextField type;
    private JTextField amount;
    private JTextField commend;
    private JComboBox<String> types;
    private JLabel aaa;
    private JLabel bbb;
    private JLabel ccc;
    private JLabel newType;
    private JPanel panel1;
    private JPanel panel2;
    private JButton button;

    public AddPurchaseGUI(AccountBook accountBook) {
        super("Add Purchase");
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setPreferredSize(new Dimension(800, 150));
        ((JPanel) getContentPane()).setBorder(new EmptyBorder(13, 13, 13, 13));
        setLayout(new FlowLayout());
        this.accountBook = accountBook;

        makePanel(accountBook);

        add(panel1);
        add(panel2);
        add(bbb);
        add(amount);
        add(ccc);
        add(commend);


        add(button);
        addWindowListener(this);
        panel2.setVisible(false);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);

    }

    //EFFECTS: make some edit to the elements in the panel
    private void makePanel(AccountBook accountBook) {
        aaa = new JLabel("Types :");
        bbb = new JLabel("Amount :");
        ccc = new JLabel("Commend :");
        newType = new JLabel("new purchase type :");

        panel1 = new JPanel();
        panel2 = new JPanel();

        types = new JComboBox<String>();
        fillTypeCombo(accountBook);
        panel1.add(aaa);
        panel1.add(types);
        types.addItemListener(this);

        type = new JTextField(15);
        amount = new JTextField(10);
        commend = new JTextField(15);

        type.addActionListener(this);
        amount.addActionListener(this);
        commend.addActionListener(this);

        panel2.add(newType);
        panel2.add(type);

        button = new JButton("submit");
        button.setActionCommand("submit");
        button.addActionListener(this);
        button.setBounds(160, 280, 80, 30);
    }

    //EFFECTS: loop throw the types in the accountBook and fill in tp Jcombo
    private void fillTypeCombo(AccountBook accountBook) {
        LinkedList<String> types = accountBook.getTypeList();
        for (String c : types) {
            this.types.addItem(c);
        }
        this.types.addItem("add new type");
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            double numAmount = Double.parseDouble(amount.getText());
            String strNewType = type.getText();
            String strType = types.getSelectedItem().toString();
            String strCommend = commend.getText();

            if (e.getActionCommand().equals("submit")) {
                switchAdd(numAmount, strNewType, strType, strCommend);
            }
        } catch (NumberFormatException exception) {
            playSound("error");
            JOptionPane.showMessageDialog(frame, "Invalid amount money input!");
        }
    }

    private void switchAdd(double numAmount, String strNewType, String strType, String strCommend) {
        if (types.getSelectedItem() == "add new type") {
            try {

                accountBook.addPurchase(numAmount, strNewType, strCommend);
                //saveAccountBook(this.accountBook);
                new SuccessGUI(accountBook);
                this.setVisible(false);
            } catch (InvalidSpent exception) {
                playSound("error");
                JOptionPane.showMessageDialog(frame, "Invalid amount money input!");
            }
        } else {
            try {
                accountBook.addPurchase(numAmount, strType, strCommend);
                //saveAccountBook(this.accountBook);
                new SuccessGUI(accountBook);
                this.setVisible(false);
            } catch (InvalidSpent exception) {
                playSound("error");
                JOptionPane.showMessageDialog(frame, "Invalid amount money input!");
            }
        }
    }

    //EFFECTS: play music in the root data
    public static void playSound(String s) {
        try {
            String pathname;
            if (s == "error") {
                pathname = "/Users/yuanziqing/Desktop/CPSC210/project_z4u2b/data/Alarm.wav";
            } else {
                pathname = "/Users/yuanziqing/Desktop/CPSC210/project_z4u2b/data/money.wav";
            }
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(pathname));
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getSource() == types && e.getItem() == "add new type") {
            panel2.setVisible(true);
        }
    }

    //EFFECTS: save accountbook to file
    public static void saveAccountBook(AccountBook accountBook) {
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

    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {
        new MainFrameGUI(accountBook);
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
}
