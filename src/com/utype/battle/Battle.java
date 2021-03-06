package com.utype.battle;

import com.utype.InputManager;
import com.utype.Logger;
import com.utype.characters.Character;
import com.utype.characters.Monster;
import com.utype.ui.UIManager;

public class Battle implements CatchLettersMinigame.EventListener {
    private Character player;
    private Monster monster;
    private CatchLettersMinigame minigame = new CatchLettersMinigame();
    private boolean isGoing;

    {
        minigame.setListener(this);
    }

    public Battle(Character player, Monster monster) {
        this.player =  player;
        this.monster = monster;
    }

    public boolean isGoing() {
        return isGoing;
    }

    public void start() {
        isGoing = true;

        UIManager.setAuxiliaryVisible(true);
        InputManager.getInstance().setDirectModeEnabled(true);

        Logger.logln(String.format("Battle between you and %s has started.", monster.getName()));

        minigame.start();
    }

    public void finish() {
        UIManager.setAuxiliaryVisible(false);
        InputManager.getInstance().setDirectModeEnabled(false);

        isGoing = false;
        minigame.stop();

        Logger.logln(String.format("Battle between you and %s has ended.", monster.getName()));

        if (!player.isDead()) {
            player.increaseSkill();
        }

        player.notifyListenerWithExitBattle(monster);
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
