package com.utype.characters;

import com.sun.javafx.beans.annotations.NonNull;
import com.sun.webpane.platform.ThemeClient;
import com.utype.Loot;
import com.utype.locations.Location;

import java.util.HashMap;

/**
 * U-type
 *
 * Created by Roman Laitarenko on 1/22/16.
 */
public class Player extends Character {

    private HashMap<String, Loot> loot;

    public Player(@NonNull String name, int health) {
        super(name, health);
    }

    @Override
    public int getBaseDamage() {
        return PLAYER_BASE_DAMAGE;
    }

    public boolean move(@NonNull Location.Direction direction) {

        boolean result = super.move(direction);

        if (!result) {
            return false;
        }

        getCurrentLocation().onPlayerEntered(this);

        return true;
    }
}
