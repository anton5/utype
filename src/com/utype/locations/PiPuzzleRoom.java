package com.utype.locations;

import com.sun.javafx.beans.annotations.NonNull;
import com.utype.Logger;
import com.utype.Player;
import com.utype.ui.UIManager;

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

        UIManager.setIsAuxiliaryTextComponentVisible(true);

        Logger.loglnToAuxiliaryTextComponent(CLUE + "_ _");
        Logger.loglnToAuxiliaryTextComponent("YOU: Can you read this Ollie?");
        Logger.loglnToAuxiliaryTextComponent("CM: I can see a sequence of numbers on the screen followed by two blank spaces.");
        Logger.loglnToAuxiliaryTextComponent("It would seem that to proceed, the two digits missing should be entered");
    }

    @Override
    public boolean processInput(String input) {
        if (isFinished && !input.isEmpty()) {
            return false;
        }

        if (isFinished && input.isEmpty()) { // enter pressed

            Logger.clearAuxiliaryTextComponent();
            Logger.logln("You have successfully finished Pi puzzle");
            UIManager.setIsAuxiliaryTextComponentVisible(false);
            return true;
        }

        Integer guess;

        try {
            guess = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            Logger.loglnToAuxiliaryTextComponent("Please enter a number!");
            return true;
        }

        isFinished = check(guess);

        return true;
    }

    public boolean check(int guess) {
        if (guess > 99 || guess < 10) {
            Logger.loglnToAuxiliaryTextComponent("Only enter TWO digits");
        }

        if (guess != ANSWER) {
            Logger.loglnToAuxiliaryTextComponent("That is not the correct guess, try again");
        }

        if (guess == ANSWER) {
            Logger.loglnToAuxiliaryTextComponent("Yes! That's the number, 3 and 5 are the numbers that follow the sequence in Pi after the decimal");
            Logger.logToAuxiliaryTextComponent("Press 'Enter' to continue");
            //the door is opened
            return true;
        }

        return false;
    }
}
