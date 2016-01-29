package com.utype.locations;

import com.sun.javafx.beans.annotations.NonNull;
import com.utype.Logger;
import com.utype.Player;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * U-type
 * <p/>
 * Created by Roman Laitarenko on 1/27/16.
 */
public class MatrixRoom extends Location implements Runnable {

    private Thread thread;

    public MatrixRoom(@NonNull String name) {
        super(name, null, null, null, null);
    }

    @Override
    public void onPlayerEntered(Player player) {

        thread = new Thread(this);

        thread.start();
    }

    @Override
    public boolean processInput(String input) {
        return true;
    }

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[1;31m";
    public static final String ANSI_GREEN = "\u001B[1;32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    static final int width = 80;        // Width of terminal window - 1
    static final int flipsPerLine = 5;    // No. of columns changed per line
    static final int millisecondsOfSleep = 200; // Delay between lines in millisecond

    @Override
    public void run() {

        Random rand = new Random(System.nanoTime());


        boolean[] switches = new boolean[width];
        for (int i = 0; i < switches.length; i++) {
            switches[i] = true;
        }

        String garbage = "1234567890/*-+.,./;[]\\=_~`!@#$%^&*()あたアカサザジズゼゾシスセソキクケコイウエオジャな";
//        String garbage = "ｱｲｳｴｵｶｷｸｹｺｻｼｽｾｿﾀﾁﾂﾃﾄﾅﾆﾇﾈﾉﾊﾋﾌﾍﾎﾏﾐﾑﾒﾓﾔﾕﾖﾗﾘﾙﾚﾛﾜﾝ";
//        String garbage = "0123456789";
        String value = "CODE";
        int codeIndex = 0;
//        final String garbage = "あたアカサザジズゼゾシスセソキクケコイウエオジャな";

        int glen = garbage.length();
        while (true) {        // Waiting for Ctrl-C

            for (int i = 0; i != width; ++i) {

                if (switches[i]) {

                    if (rand.nextInt(10000) > 9970 && codeIndex < value.length()) {
                        Logger.log(Character.toString(value.charAt(codeIndex)));
                        codeIndex += 1;
                    } else {
                        Logger.log(String.valueOf(garbage.charAt(rand.nextInt(glen))));
                    }

                } else {

                    Logger.log(" ");
                }
            }

            Logger.logln("");

            for (int i = 0; i != flipsPerLine; ++i) {

                int x = rand.nextInt(width);

                switches[x] = !switches[x];

            }
            try {
                TimeUnit.MILLISECONDS.sleep(millisecondsOfSleep);
            } catch (InterruptedException ie) {
                System.out.println("sleep interrupted");
            }
        }
    }

}
