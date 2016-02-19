package com.utype.characters;

import com.sun.javafx.beans.annotations.NonNull;
import com.utype.Logger;
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
    private int skill = 1; // ???

    private EventListener listener;
    private Location currentLocation;

    public Character(@NonNull String name, int health) {
        this.name = name;
        this.health = health;
    }

    public EventListener getListener() {
        return listener;
    }

    public void setListener(EventListener listener) {
        this.listener = listener;
    }

    @NonNull
    public Location getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(@NonNull Location currentLocation) {

        if (this.currentLocation == currentLocation) {
            return;
        }

        if (this.currentLocation != null) {
            notifyListenerWithExitLocation();
        }

        this.currentLocation = currentLocation;

        setListener(currentLocation);
        notifyListenerWithEnteredLocation();
    }

    @NonNull
    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    private void setHealth(int health) {
        this.health = health;

        if (this.health < 0) {
            die();
        }
    }

    public abstract int getBaseDamage();

    public int getSkill() {
        return skill;
    }

    public void increaseSkill() {
        this.skill += 1;

        Logger.logln(String.format("%s just received skill upgrade: %d", getName(), skill));
    }

    public boolean isDead() {
        return health <= 0;
    }

    public void die() {
        health = 0;

        notifyListenerWithDeath();
    }

    public void hit(@NonNull Character character) {

        int damage = getBaseDamage() + getSkill();

        damage = (int) (damage * Math.max(Math.random(), 0.5));

        final int newHealth = character.getHealth() - damage;

        Logger.logln(String.format("%s got hit by %s. Damage %d points", character.getName(), getName(), damage));

        character.setHealth(newHealth);
    }

    public abstract boolean move(@NonNull Location.Direction direction);

    private void notifyListenerWithEnteredLocation() {
        if (getListener() != null) {
            getListener().onCharacterDidEnter(this);
        }
    }

    private void notifyListenerWithExitLocation() {
        if (getListener() != null) {
            getListener().onCharacterDidExit(this);
        }
    }

    private void notifyListenerWithEnteredBattle(Character enemy) {
        if (getListener() != null) {
            getListener().onCharacterDidEnterBattle(this, enemy);
        }
    }

    private void notifyListenerWithExitBattle(Character enemy) {
        if (getListener() != null) {
            getListener().onCharacterDidExitBattle(this, enemy);
        }
    }

    private void notifyListenerWithDeath() {
        if (getListener() != null) {
            getListener().onCharacterDidDie(this);
        }
    }

    public interface EventListener {
        void onCharacterDidEnter(Character character);

        void onCharacterDidExit(Character character);

        void onCharacterDidEnterBattle(Character character, Character enemy);

        void onCharacterDidExitBattle(Character character, Character enemy);

        void onCharacterDidDie(Character character);
    }
}
