package com.utype.locations;

import com.utype.Logger;
import com.utype.characters.Character;

public class LightRedRoom extends Location {

    public LightRedRoom(String name) {
        super(name);
    }

    @Override
    public void onCharacterDidEnter(Character character){
        Logger.logln("You are in a small room with open doorways to the “east” and to the “south”. There is a small chamber on the west side of the room.\n" +
                "YOU: Hm, I wonder, should I try that chamber?\n");

    }
}
