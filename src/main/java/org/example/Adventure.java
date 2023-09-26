package org.example;

public class Adventure {
    private Room current;
    private final UserInterface ui;

    public Adventure() {
        ui = new UserInterface(this);

    }

    public Adventure(Room current) {
        this.current = current;
        ui = new UserInterface(this);
    }

    public void buildWorld() {
        Room room1 = new Room("Room 1", "The start of your adventures begins here. The room is empty and two doors are visible to the east and south.");
        room1.addLoot("Dead adventurer's backpack");
        Room room2 = new Room("Room 2", " The floor tiles are burning hot, with a smell of cooked flesh around the room.");
        room2.addLoot("A big strange bone");
        Room room3 = new Room("Room 3","Troll market - here you can purchase cheese and hobbit-sandwiches.");
        room3.addLoot("Freshly made hobbit-sandwich");
        Room room4 = new Room("Room 4","A lush environment, the sound of running water echoes.");
        Room treasureRoom = new Room("Treasure Room","Piles of gemstones, gold, and relics are stacked as far as the eye can see");
        treasureRoom.addLoot("The Treasure of Adventure Game");
        Room room6 = new Room("Room 6","Old tracks for minecarts rusted on the ground, the stench of metal and rust lingers.");
        room6.addLoot("A rusty and dusty key");
        Room room7 = new Room("Room 7", "In the middle of the room a water fountain is placed. Next to it is a bucket and some rope.");
        room7.addLoot("Bucket");
        room7.addLoot("Rope");
        Room room8 = new Room("Room 8", "A completely dark room. Nothing is visible, but skittering noises roam the corners of the room.");
        Room room9 = new Room("Room 9", "This is strange. Upon entering the room, repetetive loud noises are heard. This seems to be a goblin rave party.");
        room9.addLoot("Strange powder");
        room9.addLoot("Faintly glowing sticks");

        //set up connections
        room1.setEast(room2);
        room1.setSouth(room4);
        room2.setEast(room3);
        room3.setSouth(room6);
        room4.setSouth(room7);
        treasureRoom.setSouth(room8);
        room6.setSouth(room9);
        room7.setEast(room8);
        room8.setEast(room9);
        current = room1;
    }

    public void setCurrentRoom(Room room) {
        current = room;
    }

    public Room getCurrentRoom() {
        return current;
    }

    public void start() {
        buildWorld();
        System.out.println("Welcome to the The Game currently know as Adventure Game");
        System.out.println("Write 'help' to show commands");
        System.out.println("You've entered:  " + current.getName());
        System.out.println(current.getDescription());
        while (true) {
            ui.handleInput();
        }
    }
}
