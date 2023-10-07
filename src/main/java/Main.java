import game.Adventure;
import ui.UserInterface;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;


public class Main {
    public static void main(String[] args) throws LineUnavailableException, IOException, UnsupportedAudioFileException {
        Adventure game = new Adventure();
        UserInterface ui = new UserInterface(game);
        ui.start();
    }
}