package com.utype;

import com.sun.istack.internal.Nullable;
import com.sun.javafx.beans.annotations.NonNull;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Logger {
    private static final String CARET_INDICATOR = "> ";
    private static final String COLOR_ESCAPE_STRING = "#@";

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
        ((AbstractDocument) auxiliaryTextComponent.getDocument()).setDocumentFilter(new CustomDocumentFilter());
    }

    public static void logln(@NonNull String string) {
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

    public static String wrapStringInColor(@NonNull String string, TextColor color) {

        return color.toString() + string + color.toString();
    }

    public enum TextColor {
        WHITE(Color.WHITE),
        RED(Color.RED),
        GREEN(Color.GREEN),
        BLUE(Color.BLUE),
        BLACK(Color.BLACK);

        private Color color;

        TextColor(Color color) {
            this.color = color;
        }

        public static TextColor randomColor() {

            return TextColor.values()[ (int )(Math.random() * TextColor.values().length)];
        }

        public Color getColor() {
            return color;
        }

        @Override
        public String toString() {
            return String.format("%s%d%s", COLOR_ESCAPE_STRING, hashCode(), COLOR_ESCAPE_STRING);
        }

        @Nullable
        public static TextColor getColorFromString(String string) {

            TextColor defaultColor = WHITE;
            string = string.replaceAll(COLOR_ESCAPE_STRING, "");

            int hash;

            try {
                hash = Integer.valueOf(string);
            } catch (NumberFormatException e) {
                return defaultColor;
            }

            TextColor[] colors = TextColor.values();

            for (int i = 0; i < colors.length; i++) {

                TextColor color = colors[i];

                if (color.hashCode() == hash) {
                    return color;
                }
            }

            return defaultColor;
        }
    }

    private final static class CustomDocumentFilter extends DocumentFilter {

        private final StyledDocument styledDocument = ((JTextPane) auxiliaryTextComponent).getStyledDocument();

        private HashMap<TextColor, AttributeSet> textColorAttributeSets = new HashMap<TextColor, AttributeSet>();
        private final StyleContext styleContext = StyleContext.getDefaultStyleContext();

        Pattern pattern = Pattern.compile(String.format("%s(.*?)%s", COLOR_ESCAPE_STRING, COLOR_ESCAPE_STRING));

        {
            TextColor[] colors = TextColor.values();

            for (int i = 0; i < colors.length; i++) {

                TextColor color = colors[i];

                Font font = new Font("monospaced", Font.PLAIN, 14);

                AttributeSet set = styleContext.addAttribute(styleContext.getEmptySet(),
                        StyleConstants.Foreground,
                        color.getColor());
                set = styleContext.addAttribute(set, StyleConstants.FontFamily, font.getFamily());
                set = styleContext.addAttribute(set, StyleConstants.FontSize, font.getSize());

                textColorAttributeSets.put(color, set);
            }
        }

        @Override
        public void insertString(FilterBypass fb, int offset, String text, AttributeSet attributeSet) throws BadLocationException {
            super.insertString(fb, offset, text, attributeSet);

            updateTextStyles();
        }

        private void updateTextStyles() {
            // Clear existing styles
            styledDocument.setCharacterAttributes(0,
                    auxiliaryTextComponent.getText().length(),
                    textColorAttributeSets.get(TextColor.BLACK),
                    false);

            // Look for tokens and highlight them
            Matcher matcher = pattern.matcher(auxiliaryTextComponent.getText());
            boolean startFound = false;
            int currentStart = 0;
            TextColor currentColor = null;

            while (matcher.find()) {

                if (!startFound) {
                    startFound = true;
                    currentStart = matcher.start();
                    currentColor = TextColor.getColorFromString(matcher.group());

                    try {
                        styledDocument.remove(matcher.start(), matcher.end() - matcher.start());
                    } catch (BadLocationException e) {
                        e.printStackTrace();
                    }

                    matcher = pattern.matcher(auxiliaryTextComponent.getText());

                    continue;
                }

                startFound = false;
                int currentEnd = matcher.start();

                try {
                    styledDocument.remove(matcher.start(), matcher.end() - matcher.start());
                } catch (BadLocationException e) {
                    e.printStackTrace();
                }

                if (currentColor != null) {

                    AttributeSet set = textColorAttributeSets.get(currentColor);
                    styledDocument.setCharacterAttributes(currentStart, currentEnd - currentStart, set, false);
                }

                matcher = pattern.matcher(auxiliaryTextComponent.getText());

            }
        }
    }

}
