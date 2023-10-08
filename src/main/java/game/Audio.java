package game;


import javax.sound.sampled.*;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;


public class Audio {
    Clip audioClip;
    public Audio(String audioFilePath) throws UnsupportedAudioFileException, IOException, LineUnavailableException {

        InputStream inputStream = getClass().getResourceAsStream("/sounds/"+audioFilePath);
        if (inputStream != null) { // if inputStream is not null, means jar file, and hence used getResource as stream.
            InputStream bufferedInputStream = new BufferedInputStream(inputStream);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(bufferedInputStream);
            audioClip = AudioSystem.getClip();
            audioClip.open(audioStream);
        } else { // if inputStream is null, means we're not in a jar, and need absolute file path.
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(new File("sounds/"+audioFilePath).getAbsoluteFile());
            audioClip = AudioSystem.getClip();
            audioClip.open(audioStream);
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
        System.out.println("Play sound");
    }


}
