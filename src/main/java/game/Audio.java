package game;


import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;


public class Audio {
    Clip audioClip;
    public Audio(String audioFilePath) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        AudioInputStream audioStream = AudioSystem.getAudioInputStream(new File(audioFilePath).getAbsoluteFile());
        audioClip = AudioSystem.getClip();
        audioClip.open(audioStream);
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
            audioClip.setMicrosecondPosition(0);
            audioClip.start();
        } else {
            audioClip.setMicrosecondPosition(0);
            audioClip.start();
        }

    }


}
