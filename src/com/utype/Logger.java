package com.utype;

import com.sun.istack.internal.Nullable;
import com.sun.javafx.beans.annotations.NonNull;

import javax.swing.*;
import javax.swing.text.JTextComponent;

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
        log((string == null ? "" : string) + "\n");
    }


    public static void log(@NonNull String string) {

        mainTextComponent.setText(mainTextComponent.getText() + string);
    }

    public static void loglnToAuxiliaryTextComponent(@Nullable String input) {

        logToAuxiliaryTextComponent((input == null ? "" : input) + "\n");
    }

    public static void clearAuxiliaryTextComponent() {

        auxiliaryTextComponent.setText(null);
    }

    public static void logToAuxiliaryTextComponent(@Nullable String input) {

        auxiliaryTextComponent.setText(auxiliaryTextComponent.getText() + input);
    }

    public static void logToInputField(@Nullable String input) {

        inputTextComponent.setText(input);
    }
}
