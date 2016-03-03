package com.utype.locations;

import com.utype.Logger;
import com.utype.characters.Character;

public class EntryRoom extends Location {
    private static final int CLUE = 20;
    private static final int ANSWER = 42;

    private boolean isFinished;
    private boolean waitsForEnter;

    public EntryRoom(String name) {
        super(name);
    }

    @Override
    public void onCharacterDidEnter(Character character) {
        Logger.logln("  This game is called U-type. We thought it would be clever to have a sort of a double entendre for a title. You see, the one meaning of the title is that you, the player, decides what's going to happen to the story with the input you type. The second meaning is''unidendified type''. Asteroids are classified by type. For example there are C-type, S-type and M-type asteroids. The one appearing in this game is unidentified, and thus U-type.");
        Logger.logln("  This game is set in a solar system inhabited by humans. Asteroids are entering the solar system frequently at this point and generally are a common sight around these parts from the interplanetary space station.\n" +
                "  You are on a space-pod doing extra vehicular work on the space station. The space station is owned by one of many private companies specializing in destroying asteroids entering the solar system. The electromagnetic transmitter has stopped working and you are receiving instructions from your crewmate Oliver Kovacs on how to rectify the problem which occurred.\n" +
                "Communications have been cut down and the problem does not show on the computer. Before the problem is fixed an asteroid is spotted which is on collision course with the space station and a pod must be sent to deal with it immediately. It’s not a big deal though. Asteroids have been common these days in our solar system and dealing with them is part of almost every day routine.\n  The game starts with you trying to figure out how to set course towards the asteroid");
        Logger.logln("On the dialogs, CM represents your crewmate Oliver ''Ollie'' Kovacs and you navigate with the keys n,e,s,w which represent the four points of the compass: north, east, south, west");

        startPuzzleIfNeeded();
    }

    @Override
    public boolean processInput(String input) {
        if (super.processInput(input)) {
            return true;
        }

        if (waitsForEnter) {
            onEnterPressed();

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
            showNext();
        }

        return true;
    }

    private void showNext() {
        waitsForEnter = true;

        captureInput();

        Logger.logln("Press enter to continue");
    }

    private void onEnterPressed() {
        releaseInput();

        waitsForEnter = false;

        Logger.logln("Entering the first room. Oxygen levels appear to be a little lower than on earth according to the readings but still the place seems habitable. No harmful gases. You take your helmet off. The place is pitch black, except for a light coming out of your pocket. You search the pocket and find that your torchlight has been on and its light has started to wane. Regardless you take it out of the pocket, the place is lit up and our hero is in a small room with doorways to the “west” and the “south”. There also is a locked door to the “east”. There is a button on the far side of the room. You press it and the place illuminates. You should turn off your torchlight before you proceed.");
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
            Logger.logln("As the pod approaches the asteroid, its shape appears to be a disc, too perfect to be anything but artificial. As the pod closes in you try to contact your crewmate.\n" +
                    "YOU: Ollie can you copy? Can you see this?\n" +
                    "CM: Yeah! That’s a friggin flying saucer! Like the one from the old crappy movies!\n" +
                    "YOU: I’m gonna return to the station! We gotta fix the transmitter problem and report this to base…\n" +
                    "\n" +
                    "Damn! I’m pulled into it… it seems it’s got some kind of tractor beam!\n" +
                    "By now the pod is pulled with great force towards the unidentified ship. The ship seems to have a tractor beam of some sort which pulls the pod closer to it. At last the pod docks and you are faced with an airlock to enter the alien ship.\n\n");
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
