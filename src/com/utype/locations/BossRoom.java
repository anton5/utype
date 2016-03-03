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

        Logger.logln("There is a doorway to the “west” and two lock doors on the “northern” and “eastern” side. There are cryogenic chambers seemingly for humans, most of them empty. The three final chambers are closed. You open one of them and a humanoid oversized embryo is waking up. You try to fight it off but it gets a bite off of your forearm. The wound festers quickly and your arm starts to bloat and hurt like hell but you manage to fend off the creature and kill it. The light starts hurting your eyes. You must find a way to get into the final chamber on the “eastern” side.\n" +
                "Boss level: Door opens, the room is dark and at first you hear noises of some beast awakening. Typically you’ve lost your connection to your crewmate. You fumble in your pocket for your flashlight. Turning it on you see a big humanoid beast about 8 feet tall with massive disproportionate muscles on the right arm and an atrophic third arm growing from the left shoulder. Small tentacles coming from the stomach area. Its eyes widen and with a rapid movement moves away from the light and you can’t see it anymore. Those eyes! Something abhorrently familiar in them. Unsettling and eldritch, they bring chills to your spine and exacerbate the pain of your festering wound. You try to search for it around the room, with the corner of your left eye you spot movement and you turn quickly but whatever it was is not there now. You continue looking around when your eyes fall on a dagger in the corner of the room. You make a quick run for it but the moment you grab it a great force of impact hits you in your left flank and you go flying across the room. You lose your flashlight but you see the dagger a few feet away from you. Try to reach it quickly. You make it, you grab the dagger but you hear the monsters groans close behind you. It’s smelt blood and it’s going for the kill. You turn quickly around with the dagger upright and the monster jumps on you. You have stabbed it and though it’s still alive it’s clearly hurting. You battle it on the floor and in the end you emerge victorious. You try standing on your feet but the exhaustion and the pain from the wound makes you faint. Wake up later, you have lost all communication with your ship and you are groggy and still in great pain. You stumble around the room and you find a switch which turns up the lights in the room. You see a human body where the monster was and you go to inspect it. It looks absolutely human, those eyes haven’t changed at all. You find a book lying on the floor stained with dried blood. It’s caked and opening it without destroying pages is impossible. Only some parts of a small part of the pages are readable. It seems to be a log:\n" +
                "…September 9th 1998…humanity on the verge…nuclear war…tensions…Soviet-Chinese block and UNA(United nations of America) are…space race… virus outbreak on earth…humanity looks to space for survival…cannot afford to wait any longer…must flee…gateway to another dimension…virus…spread in ship…sleep it off in the cryogenic…everyone…dead…so lonely…only cleaner robots for company…food supplies contaminated and destroyed by cleaners…hungry…went to sleep never to wake up again…hungry…gotta have something to eat…never to reach my destination…opened up a cryogenic chamber…will never forgive myself…I will be hungry again…long time since I last wrote…most of the chambers are empty now…feel like I’m losing it…My right arm…bloated…lump on my left shoulder…lump is getting bigger…something growing…stomach pains…this is the cost of satisfying my hunger…either going totally crazy or dying…My last entry…cannot see well…bright lights hurt my eyes…whomever this log may reach…terribly sorry…what I had to…to survive…was so hungry…\n" +
                "After what you just have read you feel uneasy, the pain is getting sharper and you feel like you’re going blind. You throw up and you faint again. You wake up later in your own vomit though neither that nor the stench bothers you. There is a lump in the middle of your chest. You can’t see well… the light hurts your eyes… you don’t mind about either of those things. Your prevalent need right now is hunger… but you also have a sick, abominable way of satisfying it…\n");
    }

    @Override
    protected void rollTheMonster() {
    }

    @Override
    public boolean processInput(String input) {
        return true;
    }
}
