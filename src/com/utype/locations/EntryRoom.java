package com.utype.locations;

import com.utype.Logger;

public class EntryRoom extends Location {

    public EntryRoom(String name) {
        super(name);
    }

    @Override
    protected void startPuzzleIfNeeded() {
        //Logger.logln("Entering the first room. Oxygen levels appear to be a little lower than on earth according to the readings but still the place seems habitable. No harmful gases. You take your helmet off. The place is pitch black, except for a light coming out of your pocket. You search the pocket (inventory comes up) and find that your torchlight has been on and its light has started to wane. Regardless you take it out of the pocket, the place is lit up and our hero is in a small room with doorways to the “west” and the “south”. There also is a locked door to the “east”. There is a button on the far side of the room. You press it and the place illuminates.");
        //Logger.logln("You should turn off your torchlight before you proceed");
    }
}
