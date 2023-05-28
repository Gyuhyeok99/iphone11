package iphone11.etc;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

class Test extends JPanel implements LineListener {
    Clip clip = null;

    public Test() {
        try {
            clip = AudioSystem.getClip();
            File file = new File("audios/Wake_Up.wav");

            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(file);
            clip.open(audioInputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }

        clip.addLineListener(this);
        JButton play = new JButton("Play");
        JButton stop = new JButton("Stop");
        JButton next = new JButton("Next");

        play.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                clip.setFramePosition(0);
                clip.start();
            }
        });
        stop.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                clip.stop();
            }
        });
        next.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                clip.stop();
            }
        });

        add(play);
        add(stop);
        add(next);
    }

    public void update(LineEvent event) {
        if (event.getType() == LineEvent.Type.STOP) {
            setBackground(Color.BLACK);
        }
        if (event.getType() == LineEvent.Type.START) {
            setBackground(Color.ORANGE);
        }
    }
}

