package game;

import item.Consumable;
import item.Item;
import item.MeleeWeapon;
import item.RangedWeapon;
import messages.ItemRarity;

public class Map {
    private Room startingRoom;

    public Map() {
    }

    public void buildWorld() {
        Audio swordHit = new Audio("swordHit.wav");
        Audio punch = new Audio("punch.wav");

        Room room1 = new Room("Spawn", "The start of your adventures begins here. Two doors are visible to the east and south.");
        room1.addItem(new Item("Dead adventurer's [letter]", "letter", ItemRarity.COMMON));
        room1.addItem(new Consumable("Full [wine] bottle", "wine", -1, ItemRarity.RARE,2));
        room1.addItem(new Consumable("[Bread]", "bread", 10, ItemRarity.COMMON));
        room1.addItem(new MeleeWeapon("Iron {sword}","sword",15,10,10,ItemRarity.UNCOMMON,
                new Audio("swordHit.wav")));
        room1.setAmbientSound(new Audio("room1.wav"));

        Room room2 = new Room("Hell's Kitchen", " The floor tiles are burning hot, with a smell of cooked flesh around the room.");
        room2.addItem(new MeleeWeapon("A big strange {bone}", "Bone", 2, 5, 0,ItemRarity.COMMON,swordHit));
        room2.addEnemy(new Enemy("The big mutant [rat]", "rat", 10,
                new MeleeWeapon("Rat {claws}", "claws", 3, 5, 5,ItemRarity.COMMON,swordHit),
                new Consumable("Health [Potion]", "potion",20, ItemRarity.COMMON)));
        room2.addEnemy(new Enemy("The Angry Chef [Gordon]", "Gordon", 30,
                new MeleeWeapon("Kitchen {knife}", "knife",14 , 4, 3,ItemRarity.UNCOMMON,swordHit)));
        room2.setAmbientSound(new Audio("frying.wav"));


        Room room3 = new Room("Trolls 'n' Rolls", "Troll market - here you can purchase cheese and hobbit-sandwiches.");
        room3.addItem(new Consumable("Freshly made hobbit-[sandwich]", "sandwich", 20, ItemRarity.UNCOMMON));
        room3.addEnemy(new Enemy("Sandwich-making [troll]", "troll", 40,
                new MeleeWeapon("Hobbit 'n' Cheese {slicer}", "slicer",12 , 4, 3,ItemRarity.COMMON,swordHit)));
        room3.setAmbientSound(new Audio("trollMarket.wav"));


        Room room4 = new Room("Tranquil Oasis", "A lush environment, the sound of running water echoes.");
        room4.addItem((new MeleeWeapon("Two-handed[sword]", "sword", 10, 10, 4,ItemRarity.COMMON,swordHit)));
        room4.addItem(new Consumable("Clean [water]", "water", 10,ItemRarity.COMMON));
        room4.addEnemy(new Enemy("Old sleeping Fisher[man]","man",5,
                new MeleeWeapon("Old Fishing {pole}", "pole", 5, 1,0,ItemRarity.COMMON, punch),
                new Consumable("Big [Fish]", "fish",40, ItemRarity.EPIC)));
        room4.setAmbientSound(new Audio("oasis.wav"));

        Room treasureRoom = new Room("Treasure Room", "Piles of gemstones, gold, and relics are stacked as far as the eye can see");
        treasureRoom.addItem(new Item("The [Treasure] of Adventure Game", "Treasure",ItemRarity.LEGENDARY));
        treasureRoom.addEnemy(new Enemy("Treasure [hoarder]", "hoarder", 1,
                new MeleeWeapon("{Sparrow}, Golden scimitar of the Pirate King", "Sparrow", 40, 15, 5,ItemRarity.LEGENDARY,swordHit)));
        treasureRoom.setAmbientSound(new Audio("treasureRoom.wav"));

        Room room6 = new Room("The Mine", "Old tracks for minecarts rusted on the ground, the stench of metal and rust lingers.");
        room6.addItem(new Consumable("Bag of [Doritos]", "Doritos", 8,ItemRarity.COMMON));
        room6.addEnemy(new Enemy("Lord [Lars]", "Lars",20,
                new MeleeWeapon("White Support {Cane}","cane",10,10,10,ItemRarity.RARE,punch),
                new Consumable("Health [Potion]", "potion",20,ItemRarity.COMMON)));
        room6.setAmbientSound(new Audio("theMine.wav"));

        Room room7 = new Room("Fountain Room", "In the middle of the room an old water fountain is placed. It is empty and noise emits from below it..");
        room7.addEnemy(new Enemy("Green [Serpent]", "serpent", 30,
                new MeleeWeapon("Scaled {tail}", "tail", 3, 8, 5,ItemRarity.COMMON,punch)));
        room7.addEnemy(new Enemy("Jake the [Snake]", "snake", 30,
                new MeleeWeapon("Poisoned {fangs}", "fangs", 8, 6, 1,ItemRarity.COMMON,swordHit)));
        room7.setAmbientSound(new Audio("fountain.wav"));

        Room underTheFountain = new Room("Under the Fountain", "While dropping down into the fountain, music appears. " +
                "You see ten goblins fistpumping and bootyshaking to the beat of Russian music producer DJ Blyatman. " +
                "The goblins seems pumped up, but not ready to fight... but Blyatman does. CYKA!");
        underTheFountain.addEnemy(new Enemy("DJ Blyatman", "Blyatman", 50,
                new RangedWeapon("Blyat {blaster}","blaster", 15,10, ItemRarity.EPIC)));
        underTheFountain.addItem(new Consumable("Strange [powder]", "powder", -5, ItemRarity.UNCOMMON, 5));
        underTheFountain.addItem(new Consumable("Strange [powder]", "powder", -5,ItemRarity.UNCOMMON, 5));
        underTheFountain.addItem(new Consumable("Strange [powder]", "powder", -5,ItemRarity.UNCOMMON, 5));
        underTheFountain.setAmbientSound(new Audio("blyatmanRave.wav"));

        Room lairOfTyson = new Room("House of Tyson", "Below the goblin rave party, you find a gold-plated mansion. \n" +
                "It is guarded by statues of tigers and from inside the mansion a legendary lisp is heard. \n" +
                "Mike Tyson appears in front of you - gloves on, shirt off - ready to thuck you up.");
        lairOfTyson.addEnemy(new Enemy("Mike [Tyson], Destroyer of Ears", "Tyson", 125,
                new MeleeWeapon("{Earbiter}", "Earbiter", 20,100,5,ItemRarity.EPIC,new Audio("punch.wav")),
                new Consumable("You won the [game]!","game",100,ItemRarity.LEGENDARY)));
        lairOfTyson.setAmbientSound(new Audio("mikeTyson.wav"));

        Room room8 = new Room("Dark Room", "A completely dark room. Nothing is visible, but skittering noises roam the corners of the room.");
        room8.addEnemy(new Enemy("[Gorlock] the Destroyer", "Gorlock", 80,
                new MeleeWeapon("Sharp diamond {axe}", "axe", 10, 15, 8,ItemRarity.RARE,swordHit)));
        room8.addEnemy(new Enemy("Red [spider]","spider", 5,
                new MeleeWeapon("Spider [leg]","leg", 5, 5,0,ItemRarity.UNCOMMON,punch)));
        room8.addEnemy(new Enemy("Green [spider]","spider", 5,
                        new MeleeWeapon("Spider [leg]","leg", 5, 5,0,ItemRarity.UNCOMMON,punch)));
        room8.setAmbientSound(new Audio("darkroom.wav"));

        Room room9 = new Room("Goblin Rave Party", "This is strange. Upon entering the room, repetitive loud noises are heard. This seems to be a goblin rave party.");
        room9.addEnemy(new Enemy("[Goblin] Raver #1", "goblin", 15,
                new MeleeWeapon("{Dagger}","dagger",5,5,5,ItemRarity.COMMON,swordHit),
                new Consumable("Health [Potion]", "potion",20,ItemRarity.COMMON)));
        room9.addEnemy(new Enemy("[Goblin] Raver #2", "goblin", 15,
                new MeleeWeapon("{Dagger}","dagger",5,5,5, ItemRarity.COMMON,swordHit),
                new Consumable("Health [Potion]", "potion",20,ItemRarity.COMMON)));
        room9.addEnemy(new Enemy("[Goblin] Raver #3", "goblin", 15,
                new MeleeWeapon("{Dagger}","dagger",5,5,5, ItemRarity.COMMON, swordHit),
                new Consumable("Health [Potion]", "potion",20,ItemRarity.COMMON)));
        room9.addItem(new Consumable("Strange [powder]", "powder", -5,ItemRarity.UNCOMMON, 5));
        room9.addItem(new MeleeWeapon("Faintly glowing [sticks]", "sticks", 1, 1, 1,ItemRarity.UNCOMMON, punch));
        room9.setAmbientSound(new Audio("goblinRave.wav"));

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
