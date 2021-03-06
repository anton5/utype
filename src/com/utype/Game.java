package com.utype;

import com.sun.javafx.beans.annotations.NonNull;
import com.utype.characters.Player;
import com.utype.locations.*;

public class Game implements Runnable, InputManager.EventListener {
    private Thread thread;
    private Player player;

    public Game(@NonNull Player player) {
        this.player = player;
    }

    public Thread getThread() {
        if (thread == null) {
            thread = new Thread(this);
        }

        return thread;
    }

    public void start() {
        getThread().start();
    }

    public void stop() {
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        Location entry = new EntryRoom("the entry room");
        Location lightRed = new LightRedRoom("the light red room");
        Location red = new RedRoom("the red room");
        Location control = new ControlRoom("the control room");
        Location green = new GreenRoom("the green room");
        Location lightGreen = new LightGreenRoom("the light green room");
        Location lightBlue = new LightBlueRoom("the light blue room");
        Location blue = new BlueRoom("the blue room");
        Location yellow = new YellowRoom("the yellow room");
        Location black = new BlackRoom("the black room");
        Location boss = new BossRoom("the boss room");

        entry.setLocationInDirection(Location.Direction.WEST, red);
        entry.setLocationInDirection(Location.Direction.SOUTH, control);

        lightRed.setLocationInDirection(Location.Direction.EAST, red);
        lightRed.setLocationInDirection(Location.Direction.SOUTH, lightBlue);

        red.setLocationInDirection(Location.Direction.NORTH, entry);
        red.setLocationInDirection(Location.Direction.EAST, control);
        red.setLocationInDirection(Location.Direction.SOUTH, blue);
        red.setLocationInDirection(Location.Direction.WEST, lightRed);

        control.setLocationInDirection(Location.Direction.NORTH, entry);
        control.setLocationInDirection(Location.Direction.EAST, green);
        control.setLocationInDirection(Location.Direction.WEST, red);
        control.setLockInDirection(Location.Direction.EAST, true);

        green.setLocationInDirection(Location.Direction.NORTH, entry);
        green.setLocationInDirection(Location.Direction.EAST, lightGreen);
        green.setLocationInDirection(Location.Direction.SOUTH, yellow);

        lightGreen.setLocationInDirection(Location.Direction.SOUTH, yellow);
        lightGreen.setLocationInDirection(Location.Direction.WEST, green);
        lightBlue.setLocationInDirection(Location.Direction.NORTH, lightRed);
        lightBlue.setLocationInDirection(Location.Direction.EAST, blue);

        blue.setLocationInDirection(Location.Direction.NORTH, red);
        blue.setLocationInDirection(Location.Direction.EAST, yellow);
        blue.setLocationInDirection(Location.Direction.SOUTH, black);
        blue.setLocationInDirection(Location.Direction.WEST, lightBlue);
        blue.setLockInDirection(Location.Direction.EAST, true);
        blue.setLockInDirection(Location.Direction.SOUTH, true);

        yellow.setLocationInDirection(Location.Direction.NORTH, green);
        yellow.setLocationInDirection(Location.Direction.EAST, lightGreen);
        yellow.setLocationInDirection(Location.Direction.SOUTH, black);
        yellow.setLockInDirection(Location.Direction.SOUTH, true);

        black.setLocationInDirection(Location.Direction.EAST, boss);
        black.setLocationInDirection(Location.Direction.WEST, blue);

        boss.setLocationInDirection(Location.Direction.WEST, black);

        player.setCurrentLocation(entry);
    }

    @Override
    public void onReceiveUserInput(String input) {
        if (player.isDead()) {

            Logger.logln("Game over, dude!");

            return;
        }

        Logger.logln(InputManager.CARET_INDICATOR + input);

        // give chance for the current location to process the input
        if (player.getCurrentLocation().holdsInput()) {

            player.getCurrentLocation().processInput(input);
            return;
        }

        Parser.Command command = Parser.parse(input);

        if (command == null) {
            Logger.logln("Cannot parse input!");
            return;
        }

        if (Parser.Command.DIRECTIONS.contains(command)) {
            Location.Direction direction = Location.Direction.directionFromCommand(command);

            if (!player.move(direction)) {

                Logger.logln("It seems there is no way in that direction.");

                if (player.dodgedCurrentMonster()) {
                    player.die();
                }

                return;
            }

            return;
        }

        switch (command) {
            case SHOW_LOCATION:
                Logger.logln("You are in " + player.getCurrentLocation().getName() + ".");
                break;
        }
    }
}
