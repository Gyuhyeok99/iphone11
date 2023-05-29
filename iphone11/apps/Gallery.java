package iphone11.apps;

import javax.swing.*;
import java.awt.*;

public class Gallery extends JPanel {
    private static Gallery instance;
    public static Gallery getInstance() throws Exception {
        if (instance == null) {
            instance = new Gallery();
        }
        return instance;
    }
    private Gallery() {
        setLayout(null);
        setOpaque(true);
        setBackground(Color.YELLOW);
    }
}
