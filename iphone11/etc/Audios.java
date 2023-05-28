package iphone11.etc;

import javax.sound.sampled.*;
import java.io.File;

public class Audios implements LineListener {

    private static final String WAKE_UP_WAV = "audios/Wake_Up.wav";

    private static Audios instance;
    private Clip clip;

    private Audios() throws Exception {
        clip = AudioSystem.getClip();
        File audioFile = new File(WAKE_UP_WAV);
        AudioInputStream ais = AudioSystem.getAudioInputStream(audioFile);
        clip.open(ais);
        clip.addLineListener(this);
    }

    public static Audios getInstance() throws Exception {
        if (instance == null) {
            instance = new Audios();
        }
        return instance;
    }

    public void update(LineEvent event) {}

    public Clip getClip() {
        return clip;
    }
}