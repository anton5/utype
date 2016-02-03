package com.utype;

import com.sun.javafx.beans.annotations.NonNull;
import com.utype.locations.Location;
import com.utype.locations.MatrixRoom;
import com.utype.locations.PiPuzzleRoom;
import com.utype.locations.CoordinatesPuzzle;

public class Game implements Runnable, InputManager.EventListener{
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
        Location room1 = new PiPuzzleRoom("Room 1");
        Location room2 = new CoordinatesPuzzle("Room 2");
        Location room3 = new MatrixRoom("Matrix room");
        Location room4 = new Location("Room 4");
        Location hall = new Location("Main hall");

        hall.setLocationInDirection(Location.Direction.NORTH, room1);
        room1.setLocationInDirection(Location.Direction.NORTH, room2);
        room2.setLocationInDirection(Location.Direction.EAST, room3);
        room3.setLocationInDirection(Location.Direction.SOUTH, room4);
        room4.setLocationInDirection(Location.Direction.WEST, hall);

        player.setCurrentLocation(hall);

        Logger.logln("You start at main hall, type 'n w e s' to navigate, 'c' to show current location");
    }

    @Override
    public void onReceiveUserInput(String input) {
        // give chance for the current location to process the input
        if (player.getCurrentLocation().processInput(input)) {
            return;
        }

        Object output = Parser.parse(input);

        if (output == null) {
            Logger.logln("Cannot parse input!");
            return;
        }

        if (output instanceof Location.Direction) {
            Location.Direction direction = (Location.Direction) output;

            if (!player.move(direction)) {
                Logger.logln("Cannot go there");
                return;
            }

            Logger.logln(player.getName() + " now in " + player.getCurrentLocation().getName());
        }

        if (output instanceof Parser.Command) {
            Parser.Command command = (Parser.Command) output;

            switch (command) {
                case SHOW_LOCATION:
                    Logger.logln("You are at " + player.getCurrentLocation().getName());
                    break;
            }
        }
    }
}
