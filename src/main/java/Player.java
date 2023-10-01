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

    public boolean takeItem(String itemName){
        Item itemToTake = currentPosition.searchRoom(itemName);
        if (itemToTake != null) {
            currentPosition.removeItem(itemToTake);
            inventory.add(itemToTake);
            return true;
        }
        return false;
    }

    public boolean dropItem(String itemName){
        Item itemToDrop = searchInv(itemName);
        if (itemToDrop != null) {
            inventory.remove(itemToDrop);
            currentPosition.addItem(itemToDrop);
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

