package game;

import messages.Direction;
import messages.ReturnAttack;
import messages.ReturnConsumable;
import messages.Status;
import item.Item;

import java.util.ArrayList;

public class Adventure {
    private final Map map;
    private final Player player;

    public Adventure() {
        map = new Map();
        map.buildWorld();
        map.startSound();

        player = new Player();
        player.setCurrentPosition(map.getStartingRoom());
    }
//Review below



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

    public ArrayList<Item> getPlayerWeapons() {
        return player.getWeapons();
    }

    public ArrayList<Item> getPlayerItems() {
        return player.getItems();
    }

    public Status equip(String itemName) {
        return player.equip(itemName);
    }

    public ReturnAttack attack(String enemyName) {
        return player.attack(enemyName);
    }

}


