package com.utype.locations;

import com.sun.javafx.beans.annotations.NonNull;
import com.utype.Logger;
import com.utype.characters.Character;
import com.utype.characters.Player;
import com.utype.ui.UIManager;

public class BlueRoom extends Location {
    private static final int CLUE = 14159265;
    private static final int ANSWER = 35;

    private boolean isFinished;

    public BlueRoom(@NonNull String name) {
        super(name);
    }

    @Override
    protected void startPuzzleIfNeeded() {


        if (isFinished) {
            return;
        }

        UIManager.setAuxiliaryVisible(true);

        captureInput();

        Logger.loglnToAuxiliaryTextComponent(CLUE + "_ _");
        Logger.loglnToAuxiliaryTextComponent("YOU: Can you read this Ollie?");
        Logger.loglnToAuxiliaryTextComponent("CM: I can see a sequence of numbers on the screen followed by two blank spaces.");
        Logger.loglnToAuxiliaryTextComponent("It would seem that to proceed, the two digits missing should be entered");
        Logger.loglnToAuxiliaryTextComponent("It is weird that the numbers can be understood by us. Maybe the sequence also should make some sense to us or the whole intergalactic universe even");
        Logger.loglnToAuxiliaryTextComponent("YOU: That could be true. I have some sequences in mind that could fit the bill. I'll give it a try");
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
            Logger.logln("You have successfully finished Pi puzzle");
            UIManager.setAuxiliaryVisible(false);
            releaseInput();

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

            setLockInDirection(Direction.EAST, false);
            setLockInDirection(Direction.SOUTH, false);

            return true;
        }

        return false;
    }
}
