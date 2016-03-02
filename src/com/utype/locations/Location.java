package com.utype.locations;

import com.sun.istack.internal.Nullable;
import com.sun.javafx.beans.annotations.NonNull;
import com.utype.Logger;
import com.utype.Parser;
import com.utype.battle.Battle;
import com.utype.characters.Character;
import com.utype.characters.Monster;
import com.utype.characters.Player;

import java.util.HashMap;
import java.util.Map;

public class Location implements Character.EventListener {
    private static final float MONSTER_ENCOUNTER_PROBABILITY = 0.85f;

    private Map<Direction, Location> locations = new HashMap<>();
    private Map<Direction, Boolean> locks = new HashMap<>();
    private Monster monster;
    private Player currentPlayer;
    private Battle battle;

    private String name;
    private boolean isHoldingInput;

    public Location(@NonNull String name) {
        this(name, null, null, null, null);
    }

    public Location(@NonNull String name,
                    Location north,
                    Location west,
                    Location south,
                    Location east) {

        this.name = name;

        locations.put(Direction.NORTH, north);
        locations.put(Direction.WEST, west);
        locations.put(Direction.SOUTH, south);
        locations.put(Direction.EAST, east);

        locks.put(Direction.NORTH, false);
        locks.put(Direction.WEST, false);
        locks.put(Direction.SOUTH, false);
        locks.put(Direction.EAST, false);
    }

    public void setLockInDirection(@NonNull Direction direction, boolean isLocked) {
        locks.put(direction, isLocked);
    }

    public String getName() {
        return name;
    }

    @Nullable
    public Location getLocationInDirection(@NonNull Direction direction) {
        if (locks.get(direction)) {
            return null;
        }
        return locations.get(direction);
    }

    public void setLocationInDirection(@NonNull Direction direction, @Nullable Location location) {
        locations.put(direction, location);
    }

    public boolean processInput(String input) {


        if (battle != null && battle.isGoing()) {

            battle.processInput(input);

            return true;
        }

        Parser.Command command = Parser.parse(input);


        if (command == Parser.Command.DODGE) {

            currentPlayer.setDodgedCurrentMonster(true);

            Logger.logln("You have decided to dodge the " + monster.getName() + ".");

            releaseInput();

            startPuzzleIfNeeded();

            return true;
        }

        if (command == Parser.Command.FIGHT) {

            startBattle();

            return true;
        }

        return false;
    }

    private void startBattle() {

        captureInput();

        battle = new Battle(currentPlayer, monster);

        battle.start();
    }

    protected void startPuzzleIfNeeded() {

    }

    public boolean holdsInput() {
        return isHoldingInput;
    }

    protected void captureInput() {
        isHoldingInput = true;
    }

    protected void releaseInput() {
        isHoldingInput = false;
    }

    private void rollTheMonster() {

        if (monster != null) {
            return;
        }

        if (!(this instanceof BossRoom) && Math.random() > MONSTER_ENCOUNTER_PROBABILITY) {

            monster = Monster.getRandomMonster();
            monster.setListener(this);
            return;
        }

        startPuzzleIfNeeded();
    }

    @Override
    public void onCharacterDidEnter(Character character) {

        currentPlayer = (Player) character;

        Logger.logln("You have entered " + getName() + ".");

        rollTheMonster();

        if (monster != null && !monster.isDead()) {

            Logger.logln("There is a " + monster.getName() + " right ahead of you.");
            Logger.logln("Do you want to fight(f) or dodge(d)?");
            Logger.logln("Keep in mind that if you dodge and try to go to a closed direction you will die.");

            captureInput();
        }
    }

    @Override
    public void onCharacterDidExit(Character character) {

        currentPlayer = null;
    }

    @Override
    public void onCharacterDidEnterBattle(Character character, Character enemy) {

    }

    @Override
    public void onCharacterDidExitBattle(Character character, Character enemy) {

    }

    @Override
    public void onCharacterDidDie(Character character) {

        releaseInput();

        if (battle != null) {
            battle.finish();
        }
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

        public static Direction directionFromCommand(Parser.Command command) {

            switch (command) {
                case GO_NORTH:
                    return NORTH;
                case GO_WEST:
                    return WEST;
                case GO_SOUTH:
                    return SOUTH;
                case GO_EAST:
                    return EAST;
            }

            return null;
        }
    }
}
