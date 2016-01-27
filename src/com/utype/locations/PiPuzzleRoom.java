package com.utype.locations;

import com.sun.javafx.beans.annotations.NonNull;
import com.utype.Logger;
import com.utype.Player;

/**
 * U-type
 * <p/>
 * Created by Roman Laitarenko on 1/27/16.
 */
public class PiPuzzleRoom extends Location {
    private static final int CLUE = 14159265;
    private static final int ANSWER = 35;

    private boolean isFinished;

    public PiPuzzleRoom(@NonNull String name) {
        super(name, null, null, null, null);
    }

    @Override
    public void onPlayerEntered(Player player) {

        isFinished = false;

        Logger.logln(CLUE + "_ _");
        Logger.logln("YOU: Can you read this Ollie?");
        Logger.logln("CM: I can see a sequence of numbers on the screen followed by two blank spaces.");
        Logger.logln("It would seem that to proceed, the two digits missing should be entered");
    }

    @Override
    public boolean processInput(String input) {

        if (isFinished) {
            return false;
        }

        Integer guess;

        try {
            guess = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            Logger.logln("Please enter a number!");
            return true;
        }

        isFinished = check(guess);

        return true;
    }

    public boolean check(int guess) {

        if (guess > 99 || guess < 10) {
            Logger.logln("Only enter TWO digits");
        }

        if (guess != ANSWER) {
            Logger.logln("That is not the correct guess, try again");
        }

        if (guess == ANSWER) {

            Logger.logln("Yes! That's the number, 3 and 5 are the numbers that follow the sequence in Pi after the decimal");
            //the door is opened
            return true;
        }

        return false;
    }
}
