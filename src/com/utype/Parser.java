package com.utype;

import com.sun.javafx.beans.annotations.NonNull;

import java.util.EnumSet;

public class Parser {
    public enum Command {
        // navigation
        GO_NORTH("n"),
        GO_WEST("w"),
        GO_SOUTH("s"),
        GO_EAST("e"),

        // pre-battle
        FIGHT("f"),
        DODGE("d"),

        // other
        SHOW_LOCATION("c");

        public static final EnumSet DIRECTIONS = EnumSet.of(GO_NORTH, GO_WEST, GO_SOUTH, GO_EAST);

        private String commandString;

        Command(@NonNull String commandString) {
            this.commandString = commandString;
        }

        @Override
        public String toString() {
            return commandString;
        }
    }

    public static Command parse(@NonNull String string) {
        for (int i = 0; i < Command.values().length; i++) {
            Command command = Command.values()[i];

            if (string.equals(command.toString())) {
                return command;
            }
        }

        return null;
    }
}
