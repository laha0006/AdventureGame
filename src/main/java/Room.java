import java.util.ArrayList;

public class Room {
    private final String name;
    private final String description;
    private ArrayList<Item> loot;

    private Room north;
    private Room south;
    private Room east;
    private Room west;

    //Array<Item> loot;

    public Room(String name, String description) {
        this.name = name;
        this.description = description;
        loot = new ArrayList<Item>();
    }

    public void addItem(Item item) {
        loot.add(item);
    }

    public void removeItem(Item item) {
        loot.remove(item);
    }

    public Item searchRoom(String itemName) {
        for(Item item : loot) {
            if(item.getShortName().equalsIgnoreCase(itemName)) {
                return item;
            }
        }
        return null;
    }

    //Set metoder
    public void setNorth(Room north) {
        this.north = north;
        if(north.getSouth() == null) {
            north.setSouth(this);
        }
    }

    public void setSouth(Room south) {
        this.south = south;
        if(south.getNorth() == null) {
            south.setNorth(this);
        }
    }

    public void setEast(Room east) {
        this.east = east;
        if( east.getWest() == null) {
            east.setWest(this);
        }
    }

    public void setWest(Room west) {
        this.west = west;
        if( west.getEast() == null) {
            west.setEast(this);
        }
    }

    public void setUp(Room up) {
        this.up = up;
        if(up.getDown() == null) {
            up.setDown(this);
        }
    }

    public void setDown(Room down){
        this.down = down;
        if (down.getUp() == null){
            down.setUp(this);
        }
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
    public Room getUp() {
        return up;
    }
    public Room getDown() {
        return down;
    }

    public String getDescription() {
        return description;
    }

    public ArrayList<Item> getLoot() {
        return loot;
    }
}
