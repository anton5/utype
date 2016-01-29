package com.utype;

import com.sun.deploy.util.StringUtils;
import com.sun.istack.internal.Nullable;
import com.sun.javafx.beans.annotations.NonNull;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.util.Arrays;

/**
 * U-type
 * <p/>
 * Created by Roman Laitarenko on 1/22/16.
 */
public class Logger {
    private static final String CARET_INDICATOR = "> ";

    private static JTextPane mainTextComponent;
    private static JTextComponent auxiliaryTextComponent;
    private static JTextComponent inputTextComponent;

    public static void initialize(JTextPane mainTextComponent,
                                  JTextComponent auxiliaryTextComponent,
                                  JTextComponent inputTextComponent) {
        Logger.mainTextComponent = mainTextComponent;
        Logger.auxiliaryTextComponent = auxiliaryTextComponent;
        Logger.inputTextComponent = inputTextComponent;

        logToInputField(CARET_INDICATOR);
    }

    public static  void logln(@NonNull String  string) {
        log(string + "\n");
    }


    public static void log(@NonNull String string) {

        if (string.equals("\n")) {

            String[] rows = mainTextComponent.getText().split("\n");
            String lastRow = rows[rows.length - 1];

            lastRow = lastRow.replace(CARET_INDICATOR, "");

            rows[rows.length - 1] = lastRow;

            String newText = StringUtils.join(Arrays.asList(rows), "\n");

            mainTextComponent.setText(newText + string);

            return;
        }

        mainTextComponent.setText(mainTextComponent.getText() + string);
    }

    public static void logToInputField(@Nullable String input) {

        inputTextComponent.setText(input);
    }
}
