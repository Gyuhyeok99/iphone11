package iphone11.apps.gallery;

import javax.swing.*;
import java.awt.*;

public class Gallery extends JPanel {

    private static Gallery instance;
    public static Gallery getInstance() {
        if(instance == null) {
            instance = new Gallery();
        }
        return instance;
    }

    private Gallery() {

        setLayout(new BorderLayout());
        setOpaque(true);
        setBackground(Color.pink);

    }
}
