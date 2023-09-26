package org.example;

public class Adventure {
    Room current;
    UserInterface ui;

    public Adventure() {
        ui = new UserInterface();

    }

    public Adventure(Room current) {
        this.current = current;
        ui = new UserInterface();
    }

    public void buildWorld() {
        Room room1 = new Room("Room 1", "The start of your adventures begins here. The room is empty and two doors are visible to the east and south.");
        Room room2 = new Room("Room 2", " The floor tiles are burning hot, with a smell of cooked flesh around the room.");
        Room room3 = new Room("Room 3","Troll market - here you can purchase cheese and hobbit-sandwiches.");
        Room room4 = new Room("Room 4","A lush environment, the sound of running water echoes.");
        Room treasureRoom = new Room("Treasure Room","Piles of gemstones, gold, and relics are stacked as far as the eye can see");
        Room room6 = new Room("Room 6","Old tracks for minecarts rusted on the ground, the stink of metal and rust lingers.");
        Room room7 = new Room("Room 7", "In the middle of the room a water fountain is placed. Next to it is a bucket and some rope.");
        Room room8 = new Room("Room 8", "A completely dark room. Nothing is visible, but skittering noises roam the corners of the room.");
        Room room9 = new Room("Room 9", "This is strange. Upon entering the room, repetetive loud noises are heard. This seems to be a goblin rave party.");
        //set up connections
        room1.setEast(room2);
        room1.setSouth(room4);
        room2.setWest(room1);
        room2.setEast(room3);
        room3.setWest(room2);
        room3.setSouth(room6);
        room4.setNorth(room1);
        room4.setSouth(room7);
        treasureRoom.setSouth(room8);
        room6.setNorth(room3);
        room6.setSouth(room9);
        room7.setEast(room8);
        room7.setNorth(room4);
        room8.setEast(room9);
        room8.setNorth(treasureRoom);
        room8.setWest(room7);
        room9.setNorth(room6);
        room9.setWest(room8);
        current = room1;
    }

    public void setCurrentRoom(Room room) {
        current = room;
    }

    public void start() {
        buildWorld();
        System.out.println("Weclome to " + current.getName());
        while (true) {
            ui.handleInput(this);
        }
    }
}
