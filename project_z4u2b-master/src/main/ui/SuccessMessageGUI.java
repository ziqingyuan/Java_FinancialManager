package ui;

import model.AccountBook;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class SuccessMessageGUI extends JFrame implements ActionListener {
    protected JLabel label;
    protected AccountBook accountBook;
    protected String message;


    public SuccessMessageGUI(AccountBook accountBook, String message) {
        super("Success!");
        this.accountBook = accountBook;
        this.message = message;
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(400, 200));
        ((JPanel) getContentPane()).setBorder(new EmptyBorder(13, 13, 13, 13));
        setLayout(null);

        JButton btn = new JButton("OK");
        btn.setActionCommand("OK");
        btn.addActionListener(this); //sets "this" class as an action listener for btn.
        //that means that when the btn is clicked,
        //this.actionPerformed(ActionEvent e) will be called.
        //You could also set a different class, if you wanted
        //to capture the response behaviour elsewhere
        label = new JLabel(message);
        label.setBounds(75, 60, 300, 30);
        btn.setBounds(180, 120, 40, 35);

        add(btn);
        add(label);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("OK")) {
            this.setVisible(false);
            new MainFrameGUI(this.accountBook);
        }
    }
}