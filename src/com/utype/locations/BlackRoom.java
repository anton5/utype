package com.utype.locations;

import com.utype.Logger;
import com.utype.characters.Character;

public class BlackRoom extends Location {
    public BlackRoom(String name) {
        super(name);
    }

    @Override
    public void onCharacterDidEnter(Character character) {
        super.onCharacterDidEnter(character);
        Logger.logln("There is a doorway to the “west” and two locked doors on the “northern” and “eastern” side. There are cryogenic chambers seemingly for humans, most of them empty. The three final chambers are closed. You open one of them and a humanoid oversized embryo is waking up. You try to fight it off but it gets a bite off of your forearm. The wound festers quickly and your arm starts to bloat and hurt like hell but you manage to fend off the creature and kill it. The light starts hurting your eyes. You must find a way to get into the final chamber on the “eastern” side.");
    }
}
