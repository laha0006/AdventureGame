package game;


import javax.sound.sampled.*;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;


public class Audio {
    Clip audioClip;
    public Audio(String audioFilePath) {

        InputStream inputStream = getClass().getResourceAsStream("/sounds/"+audioFilePath);
        if (inputStream != null) { // if inputStream is not null, means jar file, and hence used getResource as stream.
            InputStream bufferedInputStream = new BufferedInputStream(inputStream);
            AudioInputStream audioStream = null;
            try {
                audioStream = AudioSystem.getAudioInputStream(bufferedInputStream);
            } catch (UnsupportedAudioFileException | IOException e) {
                throw new RuntimeException(e);
            }
            try {
                audioClip = AudioSystem.getClip();
            } catch (LineUnavailableException e) {
                throw new RuntimeException(e);
            }
            try {
                audioClip.open(audioStream);
            } catch (LineUnavailableException | IOException e) {
                throw new RuntimeException(e);
            }
        } else { // if inputStream is null, means we're not in a jar, and need absolute file path.
            AudioInputStream audioStream = null;
            try {
                audioStream = AudioSystem.getAudioInputStream(new File("sounds/"+audioFilePath).getAbsoluteFile());
            } catch (UnsupportedAudioFileException | IOException e) {
                throw new RuntimeException(e);
            }
            try {
                audioClip = AudioSystem.getClip();
            } catch (LineUnavailableException e) {
                throw new RuntimeException(e);
            }
            try {
                audioClip.open(audioStream);
            } catch (LineUnavailableException | IOException e) {
                throw new RuntimeException(e);
            }
        }





        FloatControl volumeControl = (FloatControl) audioClip.getControl(FloatControl.Type.MASTER_GAIN);
        volumeControl.setValue(-40f);
    }

    public void play() {
        audioClip.loop(Clip.LOOP_CONTINUOUSLY);
        audioClip.start();
    }

    public void stop() {
        audioClip.stop();
    }

    public void playOnce() {
        if (audioClip.isActive()) {
            audioClip.stop();
        }
        audioClip.setMicrosecondPosition(1);
        audioClip.start();
    }


}
