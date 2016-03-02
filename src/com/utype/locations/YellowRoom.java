package com.utype.locations;

import com.sun.javafx.beans.annotations.NonNull;
import com.utype.Logger;
import com.utype.characters.Character;
import com.utype.characters.Player;
import com.utype.ui.UIManager;

import java.util.concurrent.TimeUnit;

public class YellowRoom extends Location {

    private static final String numbers = "9 4 2 5";
    private static final int ANSWER = 2796;

    private boolean isFinished;

    public YellowRoom(@NonNull String name) {
        super(name);
    }

    @Override
    protected void startPuzzleIfNeeded() {
        super.startPuzzleIfNeeded();

        if (isFinished) {
            return;
        }

        captureInput();

        UIManager.setAuxiliaryVisible(true);

        printClue();

        /*Logger.logln("This room has many sides. On each side there is a mural depicting a constellation of each zodiac sign,");
        Logger.logln("all except cancer. A giant crab is painted on the floor.");
        Logger.logln("the ship has its origins from a star in that constellation? In each corner there are small pillars representing obelisks.");
        Logger.logln("The ceiling has a painting of something that looks like the goddess of justice represented in a deck of tarot cards.");
        Logger.logln("In the centre of the room there appears to be another console and a screen. There are open doorways to the “north”, “east” and “west”.");
        Logger.logln("A locked door on the “south side”.");
        Logger.logln("YOU: I get a sequence of numbers which then disappears.");
        Logger.logln("CM: It seems to ask for a sequence of numbers in return. Are there any clues in the room?");
        Logger.logln("YOU: There are. I thought of a number which could be the key");
        //if(player has found the clues then enter this line:){
        //Logger.logln("YOU: All are in sets of eleven");
        //Logger.logln("CM: I would guess then that eleven is the key. But how?");
        //}
        Logger.logln("YOU: Let me try it again.");*/
    }

    @Override
    public boolean processInput(String input) {

        if (super.processInput(input)) {
            return true;
        }

        if (isFinished && !input.isEmpty()) {
            return false;
        }

        if (isFinished && input.isEmpty()) { // enter pressed

            Logger.clearAuxiliaryTextComponent();
            Logger.logln("You have successfully finished eleven puzzle");
            UIManager.setAuxiliaryVisible(false);

            releaseInput();
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
        if (guess != ANSWER) {
            Logger.logln("YOU: That doesn't seem to be the correct sequence, I'll try again.");
            printClue();

        }

        if (guess == ANSWER) {

            Logger.loglnToAuxiliaryTextComponent("YOU: OK! I got it, every number on the sequence shown plus every number in the entered sequence should add up to 11");
            Logger.logToAuxiliaryTextComponent("Press 'Enter' to continue");

            setLockInDirection(Direction.SOUTH, false);

            return true;
        }

        return false;
    }

    public void printClue() {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                int i = 0;
                for (String num : numbers.split(" ")) {
                    Logger.clearAuxiliaryTextComponent();

                    String spaces = "";
                    for (int k = 0; k < i; k++) {
                        spaces += " ";
                    }
                    i++;
                    Logger.loglnToAuxiliaryTextComponent(spaces + num);

                    try {
                        TimeUnit.MILLISECONDS.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                Logger.clearAuxiliaryTextComponent();
                Logger.loglnToAuxiliaryTextComponent("Enter sequence");
            }
        });
        t.start();
    }
}
