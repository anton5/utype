package com.utype.locations;

import com.utype.Logger;
import com.utype.characters.Character;

public class LightGreenRoom extends Location {

    public LightGreenRoom(String name) {
        super(name);
    }

    @Override
    public void onCharacterDidEnter(Character character){

        Logger.logln("There is a doorway to the south and to the east. The room is littered with rubble and seemingly useless junk. There is a robot squashing things making them smaller. Among other rubble it is now holding a key about to squash it as well and you decide to attack it before it does. \n" +
                "You defeat the robot and you claim the key\n" +
                "YOU: Hey Ollie, I think I understand the purpose of those robots now. They’re kinda like the “cleaning staff”. This one acted as a trash compactor.\n" +
                "CM: Heh, we should take one of those back home to the wife. Anyway, did you collect the key it was holding?\n" +
                "YOU: I did. But it looks like it’s cut in half right down in the middle lengthways. I’ll hold on to it now and maybe it’ll be useful later on.\n");

    }
}
