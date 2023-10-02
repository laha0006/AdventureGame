public class Map {
    private Room startingRoom;

    public Map() {
    }

    public void buildWorld() {
        Room room1 = new Room("Room 1", "The start of your adventures begins here. The room is empty and two doors are visible to the east and south.");
        room1.addItem(new Item("Dead adventurer's [backpack]","backpack"));
        Room room2 = new Room("Room 2", " The floor tiles are burning hot, with a smell of cooked flesh around the room.");
        room2.addItem(new Item("A big strange [bone]", "Bone"));
        Room room3 = new Room("Room 3","Troll market - here you can purchase cheese and hobbit-sandwiches.");
        room3.addItem(new Item("Freshly made hobbit-[sandwich]", "sandwich"));
        Room room4 = new Room("Room 4","A lush environment, the sound of running water echoes.");
        Room treasureRoom = new Room("Treasure Room","Piles of gemstones, gold, and relics are stacked as far as the eye can see");
        treasureRoom.addItem(new Item ("The [Treasure] of Adventure Game", "Treasure"));
        Room room6 = new Room("Room 6","Old tracks for minecarts rusted on the ground, the stench of metal and rust lingers.");
        room6.addItem(new Item ("A rusty and dusty key","Key"));
        Room room7 = new Room("Room 7", "In the middle of the room a water fountain is placed. Next to it is a bucket and some rope.");
        room7.addItem(new Item("[Bucket]","Bucket"));
        room7.addItem(new Item("[Rope]","Rope"));
        Room room8 = new Room("Room 8", "A completely dark room. Nothing is visible, but skittering noises roam the corners of the room.");
        Room room9 = new Room("Room 9", "This is strange. Upon entering the room, repetetive loud noises are heard. This seems to be a goblin rave party.");
        room9.addItem(new Item ("Strange [powder]", "Powder"));
        room9.addItem(new Item("Faintly glowing [sticks]", "sticks"));

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
        startingRoom = room1;
    }

    public void setStartingRoom(Room room) {
        startingRoom = room;
    }

    public Room getStartingRoom() {
        return startingRoom;
    }
}
