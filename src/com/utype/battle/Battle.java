package com.utype.battle;

import com.utype.InputManager;
import com.utype.Logger;
import com.utype.characters.Character;
import com.utype.characters.Monster;
import com.utype.ui.UIManager;

/**
 * U-type
 * <p/>
 * Created by Roman Laitarenko on 2/10/16.
 */
public class Battle {

    private Character player;
    private Monster monster;
    private boolean isGoing;

    public Battle(Character player, Monster monster) {
        this.player = player;
        this.monster = monster;
    }

    public boolean isGoing() {
        return isGoing;
    }

    public void start() {

        isGoing = true;

        UIManager.setAuxiliaryVisible(true);
        InputManager.getInstance().setDirectModeEnabled(true);

        Logger.logln(String.format("Battle between %s and %s has started", player.getName(), monster.getName()));
    }

    public void finish() {

        UIManager.setAuxiliaryVisible(false);
        InputManager.getInstance().setDirectModeEnabled(false);

        isGoing = false;
    }

    public void processInput(String input) {

        Logger.clearAuxiliaryTextComponent();
        Logger.loglnToAuxiliaryTextComponent("You hit monster very hard " + input);
    }
}
