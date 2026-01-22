package src;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class SoundPlayer {

    public SoundPlayer() {
        playSound("assets/foxy.wav");
        System.out.println("Sound playing in background");
    }

    public static void playSound(String path) {
        new Thread(() -> { // run in background
            try {
                AudioInputStream audioIn = AudioSystem.getAudioInputStream(new File(path));
                Clip clip = AudioSystem.getClip();
                clip.open(audioIn);
                clip.start();
            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
