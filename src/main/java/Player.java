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

    public void addItem(Item item) {
        inventory.add(item);
    }

    public void removeItem(Item item) {
        inventory.remove(item);
    }

    public boolean takeItem(String itemToTake){
        ArrayList<Item> items = currentPosition.getLoot();
        for(Item item : items) {
            if(item.getShortName().equalsIgnoreCase(itemToTake)) {
                currentPosition.removeItem(item);
                addItem(item);
                return true;
            }
        }
        return false;
    }

    public ArrayList<Item> getInventory() {
        return inventory;
    }
}

