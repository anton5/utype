package com.utype.ui;

public class UIManager {
    public static MainFrame frame;

    public static boolean isAuxiliaryVisible;

    public static void initialize(MainFrame mainFrame) {
        frame = mainFrame;
    }

    public static boolean isAuxiliaryVisible() {
        return isAuxiliaryVisible;
    }

    public static void setAuxiliaryVisible(boolean isAuxiliaryVisible) {
        UIManager.isAuxiliaryVisible = isAuxiliaryVisible;

        frame.getAuxiliaryPanel().setVisible(isAuxiliaryVisible);
    }

}
