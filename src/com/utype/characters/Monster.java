package com.utype.characters;

import com.sun.javafx.beans.annotations.NonNull;

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
}
