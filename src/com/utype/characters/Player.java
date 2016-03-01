package com.utype.characters;

import com.sun.javafx.beans.annotations.NonNull;
import com.utype.Logger;
import com.utype.Loot;
import com.utype.locations.Location;

import java.util.HashMap;

/**
 * U-type
 *
 * Created by Roman Laitarenko on 1/22/16.
 */
public class Player extends Character {

    private boolean dodgedCurrentMonster;
    private HashMap<String, Loot> loot;

    public Player(@NonNull String name, int health) {
        super(name, health);
    }

    @Override
    public int getBaseDamage() {
        return PLAYER_BASE_DAMAGE;
    }

    public boolean dodgedCurrentMonster() {
        return dodgedCurrentMonster;
    }

    public void setDodgedCurrentMonster(boolean dodgedCurrentMonster) {
        this.dodgedCurrentMonster = dodgedCurrentMonster;
    }

    @Override
    public void setCurrentLocation(@NonNull Location currentLocation) {
        super.setCurrentLocation(currentLocation);

        setDodgedCurrentMonster(false);
    }

    @Override
    public void die() {
        super.die();

        Logger.logln("You died. Game over.");
    }

    public boolean move(@NonNull Location.Direction direction) {

        Location destination = getCurrentLocation().getLocationInDirection(direction);

        if (destination == null) {
            return false;
        }

        setCurrentLocation(destination);

        return true;
    }

    @Override
    public void onDamageMade(Character enemy, int damage) {
        Logger.logln(String.format("You hit the %s. Damage is %d points.", enemy.getName(), damage));
    }
}
