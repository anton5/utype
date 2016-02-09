package com.utype.locations;

import com.sun.istack.internal.Nullable;
import com.sun.javafx.beans.annotations.NonNull;
import com.utype.characters.Character;
import com.utype.characters.Player;

import java.util.HashMap;
import java.util.Map;

public class Location implements Character.EventListener {

    private Map<Direction, Location> locations = new HashMap<>();

    private String name;

    public Location(@NonNull String name) {
        this(name, null, null, null, null);
    }

    public Location(@NonNull String name, Location north, Location west, Location south, Location east) {
        this.name = name;

        locations.put(Direction.NORTH, north);
        locations.put(Direction.WEST, west);
        locations.put(Direction.SOUTH, south);
        locations.put(Direction.EAST, east);
    }

    public String getName() {
        return name;
    }

    @Nullable
    public Location getLocationInDirection(@NonNull Direction direction) {
        return locations.get(direction);
    }

    public void setLocationInDirection(@NonNull Direction direction, @Nullable Location location) {
        locations.put(direction, location);

        if (location.getLocationInDirection(direction.opposite()) != this) {
            location.setLocationInDirection(direction.opposite(), this);
        }
    }

    public boolean processInput(String input) {
        return false;
    }

    @Override
    public void onCharacterDidEnter(Character character) {

    }

    @Override
    public void onCharacterDidEnterBattle(Character character, Character enemy) {

    }

    @Override
    public void onCharacterDidExitBattle(Character character, Character enemy) {

    }

    @Override
    public void onCharacterDidDie(Character character) {

    }

    public enum Direction {
        NORTH,
        WEST,
        SOUTH,
        EAST;

        public Direction opposite() {
            switch (this) {
                case NORTH:
                    return SOUTH;
                case WEST:
                    return EAST;
                case SOUTH:
                    return NORTH;
                case EAST:
                    return WEST;
            }

            return null;
        }
    }
}
