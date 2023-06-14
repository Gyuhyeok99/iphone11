package iphone11.apps.message;

import iphone11.apps.gallery.Gallery;

import javax.swing.*;
import java.awt.*;

public class Message extends JPanel {
    private static Message instance;
    public static Message getInstance() {
        if(instance == null) {
            instance = new Message();
        }
        return instance;
    }

    private Message() {

        setLayout(new BorderLayout());
        setOpaque(true);
        setBackground(Color.yellow);

    }
}
