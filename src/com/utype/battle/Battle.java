package com.utype.battle;

import com.utype.InputManager;
import com.utype.Logger;
import com.utype.characters.Character;
import com.utype.characters.Monster;
import com.utype.characters.Player;
import com.utype.ui.UIManager;

/**
 * U-type
 * <p/>
 * Created by Roman Laitarenko on 2/10/16.
 */
public class Battle implements CatchLettersMinigame.EventListener {

    private Character player;
    private Monster monster;

    private CatchLettersMinigame minigame = new CatchLettersMinigame();
    private boolean isGoing;

    {
        minigame.setListener(this);
    }

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

        minigame.start();
    }

    public void finish() {

        UIManager.setAuxiliaryVisible(false);
        InputManager.getInstance().setDirectModeEnabled(false);

        isGoing = false;
        minigame.stop();

        Logger.logln(String.format("Battle between %s and %s has ended", player.getName(), monster.getName()));

        if (!player.isDead()) {
            player.increaseSkill();
        }
    }

    public void processInput(String input) {

        minigame.processInput(input);
    }

    @Override
    public void onLetterCaught() {

        player.hit(monster);
    }

    @Override
    public void onLetterMissed() {

        monster.hit(player);
    }
}
