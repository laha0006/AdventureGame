import java.util.ArrayList;

public class Adventure {
    private final Map map;
    private final Player player;

    public Adventure() {
        map = new Map();
        player = new Player();
        start();
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
    public boolean takeItem(String itemName){
        return player.takeItem(itemName);
    }
    public boolean dropItem(String itemName){
        return player.dropItem(itemName);
    }

    public ArrayList<Item> getPlayerInventory() {
        return player.getInventory();
    }
}


