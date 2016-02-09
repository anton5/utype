package com.utype.characters;

import com.sun.javafx.beans.annotations.NonNull;
import com.utype.Logger;
import com.utype.locations.Location;

/**
 * U-type
 * <p/>
 * Created by Roman Laitarenko on 2/9/16.
 */
public class Monster extends Character {

    public Monster(@NonNull String name, int health) {
        super(name, health);
    }

    @Override
    public int getBaseDamage() {
        return MONSTER_BASE_DAMAGE;
    }

    @Override
    public boolean move(@NonNull Location.Direction direction) {

        Logger.logln(getName() + " Arrrrgh! I can't move, I'm chained.");

        return false;
    }
}
