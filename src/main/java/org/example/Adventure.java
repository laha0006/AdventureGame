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
        Room room1 = new Room("Room 1");
        Room room2 = new Room("Room 2");
        Room room3 = new Room("Room 3");
        Room room4 = new Room("Room 4");
        Room treasureRoom = new Room("Treasure Room");
        Room room6 = new Room("Room 6");
        Room room7 = new Room("Room 7");
        Room room8 = new Room("Room 8");
        Room room9 = new Room("Room 9");
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
