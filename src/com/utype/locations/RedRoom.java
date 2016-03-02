package com.utype.locations;

import com.utype.Logger;

public class RedRoom extends Location {

    public RedRoom(String name) {
        super(name);
    }

    @Override
    protected void startPuzzleIfNeeded() {
        Logger.logln("There are doorways to the “east” and “south”. Huge tanks labelled H2.");
        Logger.logln("YOU: Oh my god! Take a look at this Ollie!");
        Logger.logln("CM: Crap! All aboard the Hindenburg 2.0! Seriously aliens? Hydrogen on board of a space ship?");
        Logger.logln("YOU: Maybe H2 means something else. Maybe it isn’t hydrogen. Although that could potentially save them from water shortage. Anyway I think I’m just gonna move out of here. I don’t wanna risk triggering anything around this room.");
        Logger.logln("CM: Indeed. I wouldn’t advise you stay there… t’is a silly place…");

    }
}


