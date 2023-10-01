import java.util.ArrayList;

public class Adventure {
    private final Map map;
    private final Player player;

    public Adventure() {
        map = new Map();
        player = new Player();
    }

    public void start() {
        map.buildWorld();
        player.setCurrentPosition(map.getStartingRoom());
    }

    public Room getStartingRoom() {
        return map.getStartingRoom();
    }

    public Room getPlayerPosition() {
        return player.getPlayerPosition();
    }

    public boolean movePlayer(String direction) {
        switch (direction) {
            case "north":
                if (player.getPlayerPosition().getNorth() != null) {
                    player.setCurrentPosition(player.getPlayerPosition().getNorth());
                    return true;
                } else return false;

            case "south":
                if (player.getPlayerPosition().getSouth() != null) {
                    player.setCurrentPosition(player.getPlayerPosition().getSouth());
                    return true;
                } else return false;

            case "east":
                if (player.getPlayerPosition().getEast() != null) {
                    player.setCurrentPosition(player.getPlayerPosition().getEast());
                    return true;
                } else return false;

            case "west":
                if (player.getPlayerPosition().getWest() != null) {
                    player.setCurrentPosition(player.getPlayerPosition().getWest());
                    return true;
                } else return false;
        }
        return false;
    }

    public String getCurrentRoomDescription() {
        return player.getPlayerPosition().getDescription();
    }

    public String getCurrentRoomName() {
        return player.getPlayerPosition().getName();
    }

    public ArrayList<Item> getCurrentRoomLoot() {
        return player.getPlayerPosition().getLoot();
    }
    public boolean takeItem(String itemToTake){
        ArrayList<Item> items = player.getPlayerPosition().getLoot();
        for(Item item : items) {
            if(item.getShortName().equalsIgnoreCase(itemToTake)) {
                player.getPlayerPosition().removeItem(item);
                player.addItem(item);
                return true;
            }
        }
        System.out.println("I'm here.");
        return false;
    }
    public boolean dropItem(String itemToDrop){
        ArrayList<Item> items = player.getInventory();
        if(items.isEmpty()) return false;
        for(Item item : items) {
            if(item.getShortName().equalsIgnoreCase(itemToDrop)) {
                player.removeItem(item);
                player.getPlayerPosition().addItem(item);
                return true;
            }
        }
        return false;
    }

    public ArrayList<Item> getPlayerInventory() {
        return player.getInventory();
    }
}


