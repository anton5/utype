package com.utype;

import com.sun.deploy.util.StringUtils;
import com.sun.javafx.beans.annotations.NonNull;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * U-type
 * <p/>
 * Created by Roman Laitarenko on 1/22/16.
 */
public class Logger {
    private static final String CARET_INDICATOR = "> ";

    private static JTextArea mainTextArea;

    public static void initialize(JTextArea textArea) {
        mainTextArea = textArea;
    }

    public static  void logln(@NonNull String  string) {
        log(string + "\n");
    }

    public 

    public static void log(@NonNull String string) {

        if (string.equals("\n")) {

            String[] rows = mainTextArea.getText().split("\n");
            String lastRow = rows[rows.length - 1];

            lastRow = lastRow.replace(CARET_INDICATOR, "");

            rows[rows.length - 1] = lastRow;

            String newText = StringUtils.join(Arrays.asList(rows), "\n");

            mainTextArea.setText(newText);
            mainTextArea.append(string);

//            if (!string.endsWith("\n")) {
                mainTextArea.append(CARET_INDICATOR);
//            }

            return;
        }

        mainTextArea.append(string);
    }
}
