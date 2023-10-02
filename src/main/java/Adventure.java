import java.util.ArrayList;

public class Adventure {
    private final Map map;
    private final Player player;

    public Adventure() {
        map = new Map();
        player = new Player();
        start();
    }
//Review below
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
        return player.movePlayer(direction);
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


