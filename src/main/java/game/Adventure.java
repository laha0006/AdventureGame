package game;

import data.Direction;
import data.ReturnAttack;
import data.ReturnConsumable;
import data.Status;
import item.Item;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.util.ArrayList;

public class Adventure {
    private final Map map;
    private final Player player;
    private final AudioController audioController;

    public Adventure() throws LineUnavailableException, IOException, UnsupportedAudioFileException {
        map = new Map();
        map.buildWorld();

        player = new Player();
        player.setCurrentPosition(map.getStartingRoom());

        audioController = new AudioController(player);
    }
//Review below



    public boolean movePlayer(Direction direction) throws LineUnavailableException, IOException {
        boolean moved = player.movePlayer(direction);
        audioController.updateAmbient();
        return moved;
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

    public void playAmbient() throws LineUnavailableException, IOException {
        player.getPlayerPosition().playAmbient();
    }
    public void stopAmbient() throws LineUnavailableException, IOException {
        player.getPlayerPosition().stopAmbient();
    }
    public Status equip(String itemName) {
        return player.equip(itemName);
    }

    public ReturnAttack attack(String enemyName) {
        return player.attack(enemyName);

    }

}


