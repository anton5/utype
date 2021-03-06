package com.utype.ui;

import javax.swing.*;
import javax.swing.text.DefaultCaret;
import java.awt.*;
import java.awt.event.*;

public class MainFrame extends JFrame {
    private JPanel contentPanel;
    private JPanel auxiliaryPanel;
    private JTextField inputTextField;
    private JTextPane mainTextPane;
    private JTextPane auxiliaryTextPane;

    public JTextPane getMainTextPane() {
        return mainTextPane;
    }

    public JTextPane getAuxiliaryTextPane() {
        return auxiliaryTextPane;
    }

    public JTextField getInputTextField() {
        return inputTextField;
    }

    public JPanel getAuxiliaryPanel() {
        return auxiliaryPanel;
    }

    public MainFrame() {
        setTitle("Utype");
        setContentPane(contentPanel);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onClose();
            }
        });

        Font font = new Font("monospaced", Font.PLAIN, 14);
        getMainTextPane().setFont(font);
        getInputTextField().setFont(font);

        ((DefaultCaret) getMainTextPane().getCaret()).setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
        ((DefaultCaret) getAuxiliaryTextPane().getCaret()).setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);

        if (System.getProperty("os.name").startsWith("Windows")) {
            setPreferredSize(new Dimension(800, 700));
        } else {
            setPreferredSize(new Dimension(800, 600));
        }

        Dimension d = getAuxiliaryTextPane().getSize();
        getAuxiliaryTextPane().setMaximumSize(d);
        getAuxiliaryTextPane().setMinimumSize(d);
        getAuxiliaryTextPane().setPreferredSize(d);

        d = getMainTextPane().getSize();
        getMainTextPane().setMaximumSize(d);
        getMainTextPane().setMinimumSize(d);
        getMainTextPane().setPreferredSize(d);

        getAuxiliaryTextPane().setFocusable(false);
        getMainTextPane().setFocusable(false);
        getInputTextField().setFocusable(false);
        setFocusable(true);
    }

    private void onClose() {
        dispose();
    }
}
