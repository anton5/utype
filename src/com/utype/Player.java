package com.utype;

import com.sun.javafx.beans.annotations.NonNull;

/**
 * U-type
 *
 * Created by Roman Laitarenko on 1/22/16.
 */
public class Player {

    private Location currentLocation;
    private String name;

    public Player(@NonNull String name) {
        this.name = name;
    }

    @NonNull
    public String getName() {
        return name;
    }

    @NonNull
    public Location getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(@NonNull Location currentLocation) {
        this.currentLocation = currentLocation;
    }

    public boolean move(@NonNull Location.Direction direction) {

        Location destination = getCurrentLocation().getLocationInDirection(direction);

        if (destination == null) {
            return false;
        }

        setCurrentLocation(destination);

        return true;
    }
}