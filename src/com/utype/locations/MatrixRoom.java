package com.utype.locations;

import com.sun.javafx.beans.annotations.NonNull;
import com.utype.Logger;
import com.utype.Player;
import com.utype.ui.UIManager;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class MatrixRoom extends Location implements Runnable {
    static final int WIDTH = 70;        // Width of terminal window - 1
    static final int FLIPS_PER_LINE = 5;    // No. of columns changed per line
    static final int MILLISECONDS_OF_SLEEP = 200; // Delay between lines in millisecond
    static final String CODE = "CODE";

    private boolean isRunning = false;
    private Thread thread;
    private Thread messageThread;

    public MatrixRoom(@NonNull String name) {
        super(name, null, null, null, null);
    }

    @Override
    public void onPlayerEntered(Player player) {
        UIManager.setAuxiliaryVisible(true);

        restartGame();
    }

    @Override
    public boolean processInput(String input) {
        if (!isRunning) {
            return false;
        }

        if (!input.equalsIgnoreCase(CODE)) {
            try {
                isRunning = false;
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            showDeniedMessage();
        } else  {
            try {
                isRunning = false;
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            showGrantedMessage();
        }
        return true;
    }

    private void restartGame() {
        thread = new Thread(this);

        isRunning = true;
        thread.start();
    }

    private void showDeniedMessage() {
        final String dividerLine = Logger.wrapStringInColor(new String(new char[WIDTH / 2 + 10]).replace("\0", "- ") + "\n",
                Logger.TextColor.RED);

        messageThread = new Thread(new Runnable() {
            @Override
            public void run() {
                int blinks = 2 * 2;

                while (blinks >= 0) {
                    blinks -= 1;

                    Logger.clearAuxiliaryTextComponent();

                    if (blinks % 2 == 0) {
                        try {
                            TimeUnit.MILLISECONDS.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        continue;
                    }
                    Logger.loglnToAuxiliaryTextComponent(null);
                    Logger.loglnToAuxiliaryTextComponent(null);
                    Logger.loglnToAuxiliaryTextComponent(null);
                    Logger.loglnToAuxiliaryTextComponent(null);

                    String output = "";

                    output += dividerLine;
                    output += dividerLine;
                    output += dividerLine;

                    output += Logger.wrapStringInColor(new String(new char[WIDTH / 2]).replace("\0", " ") + "Access denied",
                            Logger.TextColor.RED) + "\n";

                    output += dividerLine;
                    output += dividerLine;
                    output += dividerLine;

                    Logger.loglnToAuxiliaryTextComponent(output);

                    try {
                        TimeUnit.MILLISECONDS.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                restartGame();
            }
        });
        messageThread.start();
    }

    private void showGrantedMessage() {
        final String dividerLine = Logger.wrapStringInColor(new String(new char[WIDTH / 2 + 10]).replace("\0", "- ") + "\n",
                Logger.TextColor.GREEN);

        messageThread = new Thread(new Runnable() {
            @Override
            public void run() {
                int blinks = 2 * 2;

                while (blinks >= 0) {

                    blinks -= 1;

                    Logger.clearAuxiliaryTextComponent();

                    if (blinks % 2 == 0) {
                        try {
                            TimeUnit.MILLISECONDS.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        continue;
                    }
                    Logger.loglnToAuxiliaryTextComponent(null);
                    Logger.loglnToAuxiliaryTextComponent(null);
                    Logger.loglnToAuxiliaryTextComponent(null);
                    Logger.loglnToAuxiliaryTextComponent(null);

                    String output = "";

                    output += dividerLine;
                    output += dividerLine;
                    output += dividerLine;

                    output += Logger.wrapStringInColor(new String(new char[WIDTH / 2]).replace("\0", " ") + "Access granted",
                            Logger.TextColor.GREEN) + "\n";

                    output += dividerLine;
                    output += dividerLine;
                    output += dividerLine;

                    Logger.loglnToAuxiliaryTextComponent(output);

                    try {
                        TimeUnit.MILLISECONDS.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                Logger.clearAuxiliaryTextComponent();
                UIManager.setAuxiliaryVisible(false);
                Logger.logln("You've gained access to something");
            }
        });
        messageThread.start();
    }

    @Override
    public void run() {
        Random rand = new Random(System.nanoTime());

        boolean[] switches = new boolean[WIDTH];
        for (int i = 0; i < switches.length; i++) {
            switches[i] = true;
        }
        String garbage = "1234567890/*-+.,./;[]\\=_~`!$%^&*()あたアカサザジズゼゾシスセソキクケコイウエオジャな";
        int codeIndex = 0;
        int coolDown = 0;

        LinkedList<String> linesQueue = new LinkedList<String>() {
            @Override
            public void addFirst(String s) {
                if (size() >= 13) {
                    removeLast();
                }
                super.addFirst(s);
            }
        };

        int glen = garbage.length();

        while (isRunning) {        // Waiting for Ctrl-C
            String line = "";
            int codeCharIndex = -1;

            for (int i = 0; i != WIDTH; ++i) {
                if (switches[i]) {
                    if (rand.nextInt(10000) > 9970 && codeIndex < CODE.length() && codeCharIndex == -1 && coolDown <= 0) {
                        codeCharIndex = i;
                    }
                    line = line + String.valueOf(garbage.charAt(rand.nextInt(glen)));
                } else {
                    line += " ";
                }
            }
            coolDown -= 1;

            if (codeCharIndex != -1) {
                String firstPart = line.substring(0, codeCharIndex);
                String lastPart = line.substring(codeCharIndex);

                line = String.format("%s%s%s",
                        Logger.wrapStringInColor(firstPart, Logger.TextColor.GREEN),
                        Logger.wrapStringInColor(Character.toString(CODE.charAt(codeIndex)), Logger.TextColor.BLACK),
                        Logger.wrapStringInColor(lastPart, Logger.TextColor.GREEN)
                );
                codeIndex += 1;
            } else {
                line = Logger.wrapStringInColor(line, Logger.TextColor.GREEN);
            }
            linesQueue.addFirst(line);

            if (codeIndex >= CODE.length()) {
                coolDown = 10;
                codeIndex = 0;
            }

            if (coolDown == 5) {
                linesQueue.addFirst(Logger.wrapStringInColor(new String(new char[WIDTH / 2 + 10]).replace("\0", "- "),
                        Logger.TextColor.BLACK));
            }
            Logger.clearAuxiliaryTextComponent();

            Logger.loglnToAuxiliaryTextComponent("Try to figure out how to get access to this thing");
            Logger.loglnToAuxiliaryTextComponent("It seems like it requires some kind of password or key...");

            Logger.logToAuxiliaryTextComponent(join(linesQueue, "\n"));

            for (int i = 0; i != FLIPS_PER_LINE; ++i) {
                int x = rand.nextInt(WIDTH);

                switches[x] = !switches[x];
            }

            try {
                TimeUnit.MILLISECONDS.sleep(MILLISECONDS_OF_SLEEP);
            } catch (InterruptedException ie) {
                System.out.println("sleep interrupted");
            }
        }
    }

    static public String join(List<String> list, String conjunction) {
        StringBuilder sb = new StringBuilder();
        boolean first = true;
        for (String item : list) {
            if (first)
                first = false;
            else
                sb.append(conjunction);
            sb.append(item);
        }
        return sb.toString();
    }
}
