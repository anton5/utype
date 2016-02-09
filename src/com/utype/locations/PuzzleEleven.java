package com.utype.locations;

import com.sun.javafx.beans.annotations.NonNull;
import com.utype.Logger;
import com.utype.Player;
import com.utype.locations.Location;
import com.utype.ui.UIManager;

import java.util.concurrent.TimeUnit;

public class PuzzleEleven extends Location {

    private static final String numbers = "9 4 2 5 5 3 6 7";
    private static final int ANSWER = 27966854;

    private boolean isFinished;

    public PuzzleEleven(@NonNull String name) {
        super(name, null, null, null, null);
    }

    @Override
    public void onPlayerEntered(Player player) {
        isFinished = false;
        UIManager.setAuxiliaryVisible(true);


        printClue();
        Logger.logln("YOU: I get a sequence of numbers which then disappears.");
        Logger.logln("CM: It seems to ask for a sequence of numbers in return. Are there any clues in the room?");
        Logger.logln("YOU: There are many artifacts in here which appear to be in sets.");
        //if(player has found the clues then enter this line:){
        Logger.logln("YOU: All are in sets of eleven");
        Logger.logln("CM: I would guess then that eleven is the key. But how?");
        //}
        Logger.logln("YOU: Let me try it again.");

    }

    @Override
    public boolean processInput(String input) {

        if (isFinished && !input.isEmpty()) {
            return false;
        }

        if (isFinished && input.isEmpty()) { // enter pressed

            Logger.clearAuxiliaryTextComponent();
            Logger.logln("You have successfully finished eleven puzzle");
            UIManager.setAuxiliaryVisible(false);
            return true;
        }

        Integer guess;

        try {
            guess = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            Logger.loglnToAuxiliaryTextComponent("CM: Try entering only number sequences");
            return true;
        }

        isFinished = check(guess);

        return true;
    }

    public boolean check(int guess) {


        //printClue();


        if (guess != ANSWER) {
            Logger.logln("YOU: That doesn't seem to be the correct sequence, I'll try again.");
            printClue();

        }

        if (guess == ANSWER) {

            Logger.loglnToAuxiliaryTextComponent("YOU: OK! I got it, every number on the sequence shown plus every number in the entered sequence should add up to 11");
            Logger.logToAuxiliaryTextComponent("Press 'Enter' to continue");
            //the door is opened
            return true;
        }

        return false;
    }

    public void printClue() {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                for (String num : numbers.split(" ")) {
                    Logger.loglnToAuxiliaryTextComponent("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
                    Logger.loglnToAuxiliaryTextComponent(num);
                    try {
                        Thread.currentThread().sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }

                Logger.loglnToAuxiliaryTextComponent("SYSTEM: Enter sequence:");
            }
        });
        t.start();


    }
}
