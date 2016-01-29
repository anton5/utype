package com.utype.ui;

import com.utype.Logger;

import javax.swing.*;
import javax.swing.text.DefaultCaret;
import java.awt.*;
import java.awt.event.*;

public class MainDialog extends JDialog {
    private JPanel contentPane;
    private JTextField inputTextField;
    private JTextPane mainTextPane;
    private JTextPane auxiliaryTextPane;
    private JPanel auxiliaryPanel;

    public JTextPane getMainTextPane() {
        return mainTextPane;
    }

    public JTextPane getAuxiliaryTextPane() {
        return auxiliaryTextPane;
    }

    public JTextField getInputTextField() {
        return inputTextField;
    }

    public MainDialog() {

        setContentPane(contentPane);
        setModal(true);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onClose();
            }
        });

        ((DefaultCaret) mainTextPane.getCaret()).setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);

        setPreferredSize(new Dimension(800, 600));
        setFocusable(true);
    }

    private void onClose() {
        dispose();
    }
}
