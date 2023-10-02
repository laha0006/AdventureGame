import java.util.ArrayList;

public class Player {
    private Room currentPosition;
    private ArrayList<Item> inventory;
    //inventory

    public Player() {
        inventory = new ArrayList<Item>();
    }

    public boolean movePlayer(String direction) {
        switch (direction) {
            case "north":
                Room northRoom = currentPosition.getNorth();
                if (northRoom != null) {
                    currentPosition = northRoom;
                    return true;
                } else return false;

            case "south":
                Room southRoom = currentPosition.getSouth();
                if (southRoom != null) {
                    currentPosition = southRoom;
                    return true;
                } else return false;

            case "east":
                Room eastRoom = currentPosition.getEast();
                if (eastRoom != null) {
                    currentPosition = eastRoom;
                    return true;
                } else return false;

            case "west":
                Room westRoom = currentPosition.getWest();
                if (westRoom != null) {
                    currentPosition = westRoom;
                    return true;
                } else return false;
        }
        return false;
    }

    public void setCurrentPosition(Room room) {
        currentPosition = room;
    }

    public Room getPlayerPosition(){
        return currentPosition;
    }

    public Item takeItem(String itemName){
        Item itemToTake = currentPosition.searchRoom(itemName);
        if (itemToTake != null) {
            currentPosition.removeItem(itemToTake);
            inventory.add(itemToTake);
            return itemToTake;
        }
        return null;
    }

    public Item dropItem(String itemName){
        Item itemToDrop = searchInv(itemName);
        if (itemToDrop != null) {
            inventory.remove(itemToDrop);
            currentPosition.addItem(itemToDrop);
            return itemToDrop;
        }
        return null;
    }

    public Item searchInv(String itemName) {
        for(Item item : inventory) {
            if(item.getShortName().equalsIgnoreCase(itemName)) {
                return item;
            }
        }
        return null;
    }

    public ArrayList<Item> getInventory() {
        return inventory;
    }

    public String getCurrentRoomDescription() {
        return currentPosition.getDescription();
    }

    public String getCurrentRoomName() {
        return currentPosition.getName();
    }

    public ArrayList<Item> getCurrentRoomLoot() {
        return currentPosition.getLoot();
    }
}

