package com.utype.ui;

public class UIManager {
    public static MainFrame dialog;

    public static boolean isAuxiliaryVisible;

    public static void initialize(MainFrame mainFrame) {
        dialog = mainFrame;
    }

    public static boolean isAuxiliaryVisible() {
        return isAuxiliaryVisible;
    }

    public static void setIsAuxiliaryVisible(boolean isAuxiliaryVisible) {
        UIManager.isAuxiliaryVisible = isAuxiliaryVisible;

        dialog.getAuxiliaryPanel().setVisible(isAuxiliaryVisible);
        dialog.getMainTextPane().setEnabled(!isAuxiliaryVisible);
    }

}
