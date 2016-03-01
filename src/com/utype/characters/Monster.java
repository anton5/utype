package com.utype.characters;

import com.sun.javafx.beans.annotations.NonNull;
import com.utype.Logger;
import com.utype.locations.Location;

import java.util.Random;
import java.util.UUID;

/**
 * U-type
 * <p/>
 * Created by Roman Laitarenko on 2/9/16.
 */
public class Monster extends Character {

    public Monster(@NonNull String name, int health) {
        super(name, health);
    }

    public static Monster getRandomMonster() {
        String[] monsterNames = {
                "tall green robot",
                "humanoid with one eye",
                "robot with a long manipulator",
                "R2D2-like robot",
                "caterpillar-like robot"
        };
        return new Monster(monsterNames[new Random().nextInt(monsterNames.length)], 50);
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

    @Override
    public void onDamageMade(Character enemy, int damage) {
        Logger.logln(String.format("You got hit by the %s. Damage is %d points.", getName(), damage));
    }
}
