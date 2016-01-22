package com.utype;

import java.util.Scanner;
import com.utype.AntonGame;

public class Main {
    public static void main(String[] args) {

        AntonGame g = new AntonGame();
        g.run();

        System.out.println("Hello world!");
        System.out.println("Hello again!");
        System.out.println("I love you, world!");

        Player player = new Player("John Smith");
        Scanner scanner = new Scanner(System.in);


        // region Locations

        Location room1 = new Location("Room 1");
        Location room2 = new Location("Room 2");
        Location room3 = new Location("Room 3");
        Location room4 = new Location("Room 4");
        Location hall = new Location("Main hall");

        hall.setLocationInDirection(Location.Direction.NORTH, room1);
        room1.setLocationInDirection(Location.Direction.NORTH, room2);
        room2.setLocationInDirection(Location.Direction.EAST, room3);
        room3.setLocationInDirection(Location.Direction.SOUTH, room4);
        room4.setLocationInDirection(Location.Direction.WEST, hall);

        // endregion

        player.setCurrentLocation(hall);
        Logger.log("You start at main hall, type 'n w e s' to navigate, 'c' to show current location");

        while (true) {

            System.out.print("> ");
            String input = scanner.nextLine();

            Object output = Parser.parse(input);

            if (output == null) {

                Logger.log("Cannot parse input!");
                continue;
            }

            if (output instanceof Location.Direction) {

                Location.Direction direction = (Location.Direction) output;

                if (!player.move(direction)) {

                    Logger.log("Cannot go there");
                    continue;
                }

                Logger.log(player.getName() + " now in " + player.getCurrentLocation().getName());
            }

            if (output instanceof Parser.Command) {

                Parser.Command command = (Parser.Command) output;

                switch (command) {
                    case SHOW_LOCATION:
                        Logger.log("You are at " + player.getCurrentLocation().getName());
                        break;

                }
            }

        }
    }
}
