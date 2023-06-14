package iphone11.apps.setting;

import iphone11.apps.gallery.Gallery;

import javax.swing.*;
import java.awt.*;

public class Setting extends JPanel {

    private static Setting instance;
    public static Setting getInstance() {
        if(instance == null) {
            instance = new Setting();
        }
        return instance;
    }

    private Setting() {

        setLayout(new BorderLayout());
        setOpaque(true);
        setBackground(Color.blue);

    }
}
