import java.util.ArrayList;

public class Player {
    private Room currentPosition;
    private ArrayList<Item> inventory;
    //inventory

    public Player() {
        inventory = new ArrayList<Item>();
    }

    public void setCurrentPosition(Room position) {
        currentPosition = position;
    }

    public Room getPlayerPosition(){
        return currentPosition;
    }

    public boolean takeItem(String itemToTake){
        Item item = currentPosition.searchRoom(itemToTake);
        if (item != null) {
            currentPosition.removeItem(item);
            inventory.add(item);
            return true;
        }
        return false;
    }

    public boolean dropItem(String itemToDrop){
        Item item = searchInv(itemToDrop);
        if (item != null) {
            inventory.remove(item);
            currentPosition.addItem(item);
            return true;
        }
        return false;
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
}

