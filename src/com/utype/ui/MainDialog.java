package com.utype.ui;

import com.utype.Logger;

import javax.swing.*;
import javax.swing.text.DefaultCaret;
import java.awt.*;
import java.awt.event.*;

public class MainDialog extends JDialog {
    private JPanel contentPane;
    private JTextArea textArea1;
    private JTextArea textArea2;

    public JTextArea getMainTextArea() {
        return textArea1;
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

        textArea1.setWrapStyleWord(true);
        textArea1.setLineWrap(true);
        ((DefaultCaret) textArea1.getCaret()).setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);

        setPreferredSize(new Dimension(800, 600));
        setFocusable(true);
    }

    private void onClose() {
        dispose();
    }
}
