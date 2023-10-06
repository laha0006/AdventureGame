package game;

import data.Direction;
import data.ReturnAttack;
import data.ReturnConsumable;
import data.Status;
import item.Item;

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

    public boolean movePlayer(Direction direction) {
        return player.movePlayer(direction);
    }

    public String getCurrentRoomDescription() {
        return player.getCurrentRoomDescription();
    }

    public String getCurrentRoomName() {
        return player.getCurrentRoomName();
    }

    public ArrayList<Item> getCurrentRoomLoot() {
        return player.getCurrentRoomLoot();
    }
    public Item takeItem(String itemName){
        return player.takeItem(itemName);
    }
    public Item dropItem(String itemName){
        return player.dropItem(itemName);
    }

    public ReturnConsumable consumeItem(String itemName) {
        return player.consume(itemName);
    }

    public ArrayList<Item> getPlayerInventory() {
        return player.getInventory();
    }

    public int getPlayerHealthPoints() {
        return player.getHealthPoints();
    }

    public int getPlayerMaxHealthPoints() {
        return player.getMaxHealthPoints();
    }

    public ArrayList<Item> getPlayerConsumables() {
        return player.getConsumables();
    }

    public Status equip(String itemName) {
        return player.equip(itemName);
    }

    public ReturnAttack attack() {
        return player.attack();

    }

}


