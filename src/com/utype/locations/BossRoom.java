package com.utype.locations;

import com.utype.Logger;
import com.utype.characters.Character;

public class BossRoom extends Location {

    public BossRoom(String name) {
        super(name);
    }

    @Override
    public void onCharacterDidEnter(Character character) {
        super.onCharacterDidEnter(character);

        Logger.logln("You've reached the boss room. You won!");
    }

    @Override
    protected void rollTheMonster() {
        // we don't have final fight currently
    }

    @Override
    public boolean processInput(String input) {

        return true;
    }
}
