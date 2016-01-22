package com.utype;

import com.sun.javafx.beans.annotations.NonNull;

/**
 * U-type
 *
 * Created by Roman Laitarenko on 1/22/16.
 */
public class Parser {

    public enum Command {
        SHOW_LOCATION
    }

    public static Object parse(@NonNull String string) {

        if (string.equals("n")) {
            return Location.Direction.NORTH;
        }

        if (string.equals("w")) {
            return Location.Direction.WEST;
        }

        if (string.equals("s")) {
            return Location.Direction.SOUTH;
        }

        if (string.equals("e")) {
            return Location.Direction.EAST;
        }

        if (string.equals("c")) {
            return Command.SHOW_LOCATION;
        }

        return null;
    }
}
