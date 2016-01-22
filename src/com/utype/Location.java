package com.utype;

import com.sun.istack.internal.Nullable;
import com.sun.javafx.beans.annotations.NonNull;

import java.util.HashMap;
import java.util.Map;

/**
 * U-type
 *
 * Created by Roman Laitarenko on 1/22/16.
 */
public class Location {

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
    }

    public enum Direction {
        NORTH,
        WEST,
        SOUTH,
        EAST
    }
}
