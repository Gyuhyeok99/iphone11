package iphone11.apps;

import javax.swing.*;
import java.awt.*;

public class DrawingBoard extends JPanel {
    private static DrawingBoard instance;
    public static DrawingBoard getInstance() throws Exception {
        if (instance == null) {
            instance = new DrawingBoard();
        }
        return instance;
    }

    private DrawingBoard() {
        setLayout(null);
        setOpaque(true);
        setBackground(Color.PINK);
    }
}
