package com.utype.characters;

import com.sun.javafx.beans.annotations.NonNull;
import com.utype.locations.Location;

/**
 * U-type
 * <p/>
 * Created by Roman Laitarenko on 2/9/16.
 */
public abstract class Character {
    public static final int PLAYER_BASE_DAMAGE = 10;
    public static final int MONSTER_BASE_DAMAGE = 10;

    private String name;
    private int health;
    private int skill; // ???

    private Location currentLocation;

    public Character(@NonNull String name, int health) {
        this.name = name;
        this.health = health;
    }

    @NonNull
    public Location getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(@NonNull Location currentLocation) {
        this.currentLocation = currentLocation;
    }

    @NonNull
    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public abstract int getBaseDamage();

    public int getSkill() {
        return skill;
    }

    public void increaceSkill() {
        this.skill += 1;
    }

    public abstract boolean move(@NonNull Location.Direction direction);
}
