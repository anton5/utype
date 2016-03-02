package com.utype.battle;

import com.utype.Logger;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * U-type
 * <p/>
 * Created by Roman Laitarenko on 2/17/16.
 */
public class CatchLettersMinigame implements Runnable {

    private static final int ROWS = 12;
    private static final int COLUMNS = 47;

    private final Map<Integer, String> lettersPool = new HashMap<>();
    private String currentLetter;
    private int currentLetterRow;
    private int currentLetterColumn;

    private Random random = new Random();
    private boolean stopRequested;

    private EventListener listener;

    {
        lettersPool.put(0, "qaz");
        lettersPool.put(1, "wsx");
        lettersPool.put(2, "edc");
        lettersPool.put(3, "rfv");
        lettersPool.put(4, "tgb");
        lettersPool.put(5, "yhn");
        lettersPool.put(6, "ujm");
        lettersPool.put(7, "ikl");

        rollNextLetter();
    }

    public EventListener getListener() {
        return listener;
    }

    public void setListener(EventListener listener) {
        this.listener = listener;
    }

    public void start() {

        new Thread(this).start();
    }

    public void stop() {

        stopRequested = true;
    }

    public void processInput(String input) {

        // ignore enter presses
        if (input.equals("\n")) {
            return;
        }

        if (currentLetter.equals(input)) {

            rollNextLetter();
            notifyListenerWithLetterCaught();

            return;
        }

        notifyListenerWithLetterMissed();
    }

    private void rollNextLetter() {

        int letterColumnIndex = random.nextInt(lettersPool.size());
        String row = lettersPool.get(letterColumnIndex);
        int letterRowIndex = random.nextInt(row.length());

        currentLetter = Character.toString(row.charAt(letterRowIndex));
        currentLetterRow = 0;
        currentLetterColumn = (letterRowIndex * 5 + 5) / 2 + 1;
        currentLetterColumn = (COLUMNS / lettersPool.size()) * letterColumnIndex;
    }

    @Override
    public void run() {

        while (true) {

            if (stopRequested) {
                break;
            }

            String buffer = "";
            for (int i = 0; i < ROWS; i++) {

                StringBuilder template = new StringBuilder(new String(new char[COLUMNS]).replace("\0", "_ "));

                if (i == currentLetterRow) {
                    template.setCharAt(currentLetterColumn, currentLetter.toCharArray()[0]);
                }

                buffer = buffer + template.toString() + "\n";
            }

            Logger.clearAuxiliaryTextComponent();
            Logger.logToAuxiliaryTextComponent(buffer);

            currentLetterRow += 1;

            if (currentLetterRow > ROWS) {

                rollNextLetter();
                notifyListenerWithLetterMissed();

                continue;
            }

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void notifyListenerWithLetterCaught() {

        if (getListener() != null) {
            getListener().onLetterCaught();
        }
    }

    public void notifyListenerWithLetterMissed() {

        if (getListener() != null) {
            getListener().onLetterMissed();
        }
    }

    public interface EventListener {

        void onLetterCaught();

        void onLetterMissed();
    }
}
