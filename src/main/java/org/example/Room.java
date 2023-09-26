package org.example;

import java.util.ArrayList;

public class Room {
    String name;
    String description;
    ArrayList<String> loot;

    private Room north;
    private Room south;
    private Room east;
    private Room west;

    //Array<Item> loot;

    public Room(String name, String description) {
        this.name = name;
        this.description = description;
        loot = new ArrayList<String>();
    }

    public void addLoot(String item) {
        loot.add(item);
    }
//Set metoder
    public void setNorth(Room north) {
        this.north = north;
    }

    public void setSouth(Room south) {
        this.south = south;
    }

    public void setEast(Room east) {
        this.east = east;
    }

    public void setWest(Room west) {
        this.west = west;
    }
    //Get metoder


    public String getName() {
        return name;
    }

    public Room getNorth() {
        return north;
    }

    public Room getSouth() {
        return south;
    }

    public Room getEast() {
        return east;
    }

    public Room getWest() {
        return west;
    }

    public String getDescription() {
        return description;
    }

    public ArrayList<String> getLoot() {
        return loot;
    }
}
