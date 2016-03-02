package com.utype.locations;

import com.utype.Logger;
import com.utype.characters.Character;

public class EntryRoom extends Location {

    private static final int CLUE = 20;
    private static final int ANSWER = 42;

    private boolean isFinished;

    public EntryRoom(String name) {
        super(name);
    }

    @Override
    public void onCharacterDidEnter(Character character) {
        super.onCharacterDidEnter(character);

        //Logger.logln("Entering the first room. Oxygen levels appear to be a little lower than on earth according to the readings but still the place seems habitable. No harmful gases. You take your helmet off. The place is pitch black, except for a light coming out of your pocket. You search the pocket (inventory comes up) and find that your torchlight has been on and its light has started to wane. Regardless you take it out of the pocket, the place is lit up and our hero is in a small room with doorways to the “west” and the “south”. There also is a locked door to the “east”. There is a button on the far side of the room. You press it and the place illuminates.");
        //Logger.logln("You should turn off your torchlight before you proceed");
    }

    @Override
    public boolean processInput(String input) {

        if (super.processInput(input)) {
            return true;
        }

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

        if (isFinished) {
            releaseInput();
        }

        return true;
    }

    public boolean check(int guess) {

        if (guess > 50 || guess < 30) {
            Logger.logln("Only enter a number in the range of 30 to 50");
        }

        if (guess != ANSWER) {
            Logger.logln("SYSTEM: F(" + guess + ") = " + calc(guess)); //"SYSTEM:f("+guess+ ") = " + calc(guess)
            Logger.logln("YOU:That is not correct. F(x) must be equal to 20. This seems like normal distribution... A bell graph...");
        }

        if (guess == ANSWER) {

            isFinished = true;

            Logger.logln("SYSTEM: F(" + guess + ") = " + calc(guess));
            Logger.logln("YOU: Ok Ollie, I've got the coordinates, I'm moving on towards the asteroid");
            //the door is opened
            return true;
        }

        return false;
    }

    public double calc(int guess) {

        double exp = (Math.pow((-(guess - 42)), 2)) / 2;
        return (Math.pow(1.5, exp)) / 0.05;
    }

    @Override
    protected void startPuzzleIfNeeded() {

        if (isFinished) {
            return;
        }

        captureInput();

        Logger.logln("YOU: Ok, I need to enter the coordinates to proceed to the rendezvous point");
        Logger.logln("YOU: Ollie, can you help me out a little?");
        Logger.logln("CM: Yeah, you gotta enter 'x' in order to achieve 'y=20'. According to my calculations the number is somewhere in the range of 30 to 50");
        Logger.logln("SYSTEM: Enter x:");
    }
}
