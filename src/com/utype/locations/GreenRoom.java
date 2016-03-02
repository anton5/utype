package com.utype.locations;

import com.utype.Logger;
import com.utype.characters.Character;

public class GreenRoom extends Location {

    public GreenRoom(String name) {
        super(name);
    }

    @Override
    public void onCharacterDidEnter(Character character){
        Logger.logln("There is a doorway on the “north”, “south”, “east”, and “west” sides of the room. Cryogenic chambers containing what appears to be food.\n" +
                "YOU: Take a look at this Ollie. It’s an alien future-buffet.\n" +
                "CM:I wonder what the price per person is around these parts. \n" +
                "YOU: Joking aside, whatever inhabits or inhabited this place must be quite similar to us. They breathe oxygen and eat the same food as us. They must be drinking water as well…\n" +
                "CM: Well if I were you I wouldn’t trust the entirety of that logic… I wouldn’t touch any of that…”food”… \n");
    }
}
