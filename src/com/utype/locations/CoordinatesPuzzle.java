package com.utype.locations;

import com.sun.javafx.beans.annotations.NonNull;
import com.utype.Logger;
import com.utype.characters.Player;

/**
 * Created by Sakis on 29/01/2016.
 */
public class CoordinatesPuzzle extends Location {

    private static final int CLUE = 20;
    private static final int ANSWER = 42;

    private boolean isFinished;

    public CoordinatesPuzzle(@NonNull String name) {
        super(name, null, null, null, null);
    }

    @Override
    public void onPlayerEntered(Player player) {

        isFinished = false;

        Logger.logln("YOU: Ok, I need to enter the coordinates to proceed to the rendezvous point");
        Logger.logln("YOU: Ollie, can you help me out a little?");
        Logger.logln("CM: Yeah, you gotta enter 'x' in order to achieve 'y=20'. According to my calculations the number is somewhere in the range of 30 to 50");
        Logger.logln("SYSTEM: Enter x:");
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

        if (guess > 50 || guess < 30) {
            Logger.logln("Only enter a number in the range of 30 to 50");
        }

        if (guess != ANSWER) {
            Logger.logln("SYSTEM: F(" + guess + ") = " + calc(guess)); //"SYSTEM:f("+guess+ ") = " + calc(guess)
            Logger.logln("YOU:That is not correct. F(x) must be equal to 20. This seems like normal distribution... A bell graph...");
        }

        if (guess == ANSWER) {

            Logger.logln("SYSTEM: F(" + guess + ") = " + calc(guess));
            Logger.logln("YOU: Ok Ollie, I've got the coordinates, I'm moving on towards the asteroid");
            //the door is opened
            return true;
        }

        return false;
    }


    public double calc(int guess){

        double exp = (Math.pow((-(guess-42)), 2))/2;
        return (Math.pow(1.5,exp))/0.05;

    }

}
        /*double exp = -(Math.pow((guess-42), 2))/2;
        return (Math.pow(1.5,exp))/0.05;*/

        //(1.5^((-(x-42)^2)/2))/0.05


