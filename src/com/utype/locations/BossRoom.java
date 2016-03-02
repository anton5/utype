package com.utype.locations;
import com.utype.Logger;

public class BossRoom extends Location {

    public BossRoom(String name) {
        super(name);
    }
    @Override
    protected void startPuzzleIfNeeded() {
        Logger.logln("You've reached the boss room. You won!");
    }

    @Override
    public boolean processInput(String input) {

        return true;
    }
}
