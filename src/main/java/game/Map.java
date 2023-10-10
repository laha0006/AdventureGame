package game;

import item.Consumable;
import item.Item;
import item.MeleeWeapon;
import item.RangedWeapon;

public class Map {
    private Room startingRoom;

    public Map() {
    }

    public void buildWorld() {
        Room room1 = new Room("Spawn", "The start of your adventures begins here. Two doors are visible to the east and south.");
        room1.addItem(new Item("Dead adventurer's [backpack]", "backpack"));
        room1.addItem(new Consumable("Full [wine] bottle", "wine", -1, 2));
        room1.addEnemy(new Enemy("[Lars]","[Lars]", 30, new MeleeWeapon("[mace]", "mace", 5,5,5)));
        //test
        room1.addItem(new MeleeWeapon("Ancient [Gloves] of Testing","gloves",10,10,10,
                new Audio("punch.wav")));
        room1.setAmbientSound(new Audio("vibe.wav"));

        Room room2 = new Room("game.Room 2", " The floor tiles are burning hot, with a smell of cooked flesh around the room.");
        room2.addItem(new MeleeWeapon("A big strange [bone]", "Bone", 2, 5, 0));
        //test
        room2.setAmbientSound(new Audio("vibe2.wav"));

        Room room3 = new Room("game.Room 3", "Troll market - here you can purchase cheese and hobbit-sandwiches.");
        room3.addItem(new Consumable("Freshly made hobbit-[sandwich]", "sandwich", 10));
        //test
        room3.setAmbientSound(new Audio("vibe3.wav"));


        Room room4 = new Room("game.Room 4", "A lush environment, the sound of running water echoes.");
        room4.addItem((new MeleeWeapon("Short[sword]", "Sword", 5, 10, 3)));
        room4.addItem(new Consumable("Clean [water]", "water", 5));
        room4.addItem(new MeleeWeapon("Old man's fishing [rod]", "rod", 4, 1, 1));
        room4.addEnemy(new Enemy("[Gorlock] the Destroyer", "[Gorlock]", 35, new MeleeWeapon("Big rusty [axe]", "axe", 5, 5, 8)));
        //test enemy
        room4.addEnemy(new Enemy("Lars",20,new MeleeWeapon("White Support [Cane]","cane",10,10,10)));

        Room treasureRoom = new Room("Treasure game.Room", "Piles of gemstones, gold, and relics are stacked as far as the eye can see");
        treasureRoom.addItem(new Item("The [Treasure] of game.Adventure Game", "Treasure"));

        Room room6 = new Room("game.Room 6", "Old tracks for minecarts rusted on the ground, the stench of metal and rust lingers.");
        room6.addItem(new Item("A rusty and dusty [key]", "Key"));
        room6.addItem(new Consumable("Bag of [Doritos]", "Doritos", 2));
        room6.addItem(new RangedWeapon("A big heavy [crossbow]", "crossbow", 5, 10));
        room6.addEnemy(new Enemy("Lord [Lars]", "Lars",20,new MeleeWeapon("White Support [Cane]","cane",10,10,10)));

        Room room7 = new Room("Fountain Room", "In the middle of the room an old water fountain is placed. It is empty and noise emits from below it..");
        room7.addEnemy(new Enemy("[Rat]", "rat", 1, new MeleeWeapon("[Claws]", "claws", 1, 1, 1)));
        room7.addEnemy(new Enemy("Cock[roach]", "roach", 1, new MeleeWeapon("[Poison]", "poison", 1, 1, 1)));

        Room underTheFountain = new Room("Under the Fountain", "While dropping down into the fountain, music appears. " +
                "You see ten goblins fistpumping and bootyshaking to the beat of russian music producer DJ Blyatman. Fast and very intense.");
        underTheFountain.addItem(new Consumable("Strange [powder]", "Powder", -5, 5));
        underTheFountain.addItem(new Consumable("Strange [powder]", "Powder", -5, 5));
        underTheFountain.addItem(new Consumable("Strange [powder]", "Powder", -5, 5));
        underTheFountain.addItem(new Item("Wet Adidas track[suit]", "suit"));
        underTheFountain.addItem(new MeleeWeapon("Boxing [gloves]", "gloves", 10, 10, 4));

        Room lairOfTyson = new Room("House of Tyson", "Below the goblin rave party, you find a gold-plated mansion. \n" +
                "It is guarded by statues of tigers and from inside the mansion a legendary lisp is heard. \n" +
                "Mike Tyson appears in front of you - gloves on, shirt off - ready to thuck you up.");

        Room room8 = new Room("Dark Room", "A completely dark room. Nothing is visible, but skittering noises roam the corners of the room.");

        Room room9 = new Room("Goblin Rave Party", "This is strange. Upon entering the room, repetetive loud noises are heard. This seems to be a goblin rave party.");
        room9.addEnemy(new Enemy("[Goblin] Raver #1", "goblin", 15, new MeleeWeapon("Dagger","dagger",5,5,5)));
        room9.addEnemy(new Enemy("[Goblin] Raver #2", "goblin", 15, new MeleeWeapon("Dagger","dagger",5,5,5)));
        room9.addEnemy(new Enemy("[Goblin] Raver #3", "goblin", 15, new MeleeWeapon("Dagger","dagger",5,5,5)));
        room9.addItem(new Consumable("Strange [powder]", "powder", -5, 5));
        room9.addItem(new MeleeWeapon("Faintly glowing [sticks]", "sticks", 1, 1, 1));

        //set up connections
        room1.setEast(room2);
        room1.setSouth(room4);
        room2.setEast(room3);
        room3.setSouth(room6);
        room4.setSouth(room7);
        treasureRoom.setSouth(room8);
        room6.setSouth(room9);
        room7.setEast(room8);
        room7.setDown(underTheFountain);
        underTheFountain.setDown(lairOfTyson);
        room8.setEast(room9);
        startingRoom = room1;
    }

    public void setStartingRoom(Room room) {
        startingRoom = room;
    }

    public Room getStartingRoom() {
        return startingRoom;
    }

    public void startSound() {
        if(startingRoom.getAmbientSound() != null) {
            startingRoom.playAmbient();
        }
    }
}
