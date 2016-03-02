package com.utype.locations;

import com.utype.Logger;
import com.utype.characters.Character;

public class LightBlueRoom extends Location {

    public LightBlueRoom(String name) {
        super(name);
    }

    @Override
    public void onCharacterDidEnter(Character character){
        Logger.logln("There are doorways to the “north” and to the “east”. Several cabinets in the room. The first one contains turning levers and valves. You don’t want to turn any of these lest you cause any more disturbances. The second one contains a key. You decide to take the key in order to use it in the future.");
    }
}
