package game;

import javax.sound.sampled.LineUnavailableException;
import java.io.IOException;

public class AudioController {
    Player player;
    Room currentRoom;

    public AudioController(Player player) throws LineUnavailableException, IOException {
        this.player = player;
        currentRoom = player.getPlayerPosition();
        start();
    }


    public void start() throws LineUnavailableException, IOException {
        currentRoom.playAmbient();
    }

    public void updateAmbient() throws LineUnavailableException, IOException {
        if (currentRoom.getAmbientSound() != null) {
            currentRoom.stopAmbient();
        }
        currentRoom = player.getPlayerPosition();
        if (currentRoom.getAmbientSound() != null) {
            currentRoom.playAmbient();
        }
    }

    public void playAttackSound() throws LineUnavailableException, IOException {
        if(player.getWeaponSlot1() == null) return;
        if(player.getWeaponSlot1().getAttackSound() != null) {
            player.getWeaponSlot1().playAttackSound();
        }
    }
}
