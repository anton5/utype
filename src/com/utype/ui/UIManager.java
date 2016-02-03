package com.utype.ui;

/**
 * U-type
 * <p/>
 * Created by Roman Laitarenko on 1/29/16.
 */
public class UIManager {

    public static MainFrame dialog;

    public static boolean isAuxiliaryTextComponentVisible;

    public static void initialize(MainFrame mainFrame) {
        dialog = mainFrame;
    }

    public static boolean isAuxiliaryTextComponentVisible() {
        return isAuxiliaryTextComponentVisible;
    }

    public static void setIsAuxiliaryTextComponentVisible(boolean isAuxiliaryTextComponentVisible) {
        UIManager.isAuxiliaryTextComponentVisible = isAuxiliaryTextComponentVisible;

        dialog.getAuxiliaryPanel().setVisible(isAuxiliaryTextComponentVisible);
        dialog.getMainTextPane().setEnabled(!isAuxiliaryTextComponentVisible);
    }

}
