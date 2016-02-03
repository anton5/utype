package com.utype.locations;

import com.sun.javafx.beans.annotations.NonNull;
import com.utype.Logger;
import com.utype.Player;
import com.utype.ui.UIManager;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * U-type
 * <p/>
 * Created by Roman Laitarenko on 1/27/16.
 */
public class MatrixRoom extends Location implements Runnable {
    static final int WIDTH = 85;        // Width of terminal window - 1
    static final int FLIPS_PER_LINE = 5;    // No. of columns changed per line
    static final int MILLISECONDS_OF_SLEEP = 200; // Delay between lines in millisecond

    private Thread thread;

    public MatrixRoom(@NonNull String name) {
        super(name, null, null, null, null);
    }

    @Override
    public void onPlayerEntered(Player player) {

        UIManager.setAuxiliaryVisible(true);

        thread = new Thread(this);

        thread.start();
    }

    @Override
    public boolean processInput(String input) {
        return true;
    }

    @Override
    public void run() {

        Random rand = new Random(System.nanoTime());


        boolean[] switches = new boolean[WIDTH];
        for (int i = 0; i < switches.length; i++) {
            switches[i] = true;
        }

        String garbage = "1234567890/*-+.,./;[]\\=_~`!@#$%^&*()あたアカサザジズゼゾシスセソキクケコイウエオジャな";
        String value = "CODE";
        int codeIndex = 0;


        LinkedList<String> linesQueue = new LinkedList<String>() {

            @Override
            public void addFirst(String s) {

                if (size() >= 15) {
                    removeLast();
                }

                super.addFirst(s);
            }
        };

        int glen = garbage.length();
        while (true) {        // Waiting for Ctrl-C

            String line = "";

            for (int i = 0; i != WIDTH; ++i) {

                if (switches[i]) {

                    if (rand.nextInt(10000) > 9970 && codeIndex < value.length()) {
                        line += Character.toString(value.charAt(codeIndex));
                        codeIndex += 1;
                    } else {

                        line += String.valueOf(garbage.charAt(rand.nextInt(glen)));
                    }

                } else {

                    line += " ";
                }
            }

            linesQueue.addFirst("sfsdf sdfsd f sfd fs" + "@sdfsd@" + line + "@BBBBBF@");

            Logger.clearAuxiliaryTextComponent();
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

    static public String join(List<String> list, String conjunction)
    {
        StringBuilder sb = new StringBuilder();
        boolean first = true;
        for (String item : list)
        {
            if (first)
                first = false;
            else
                sb.append(conjunction);
            sb.append(item);
        }
        return sb.toString();
    }

}
