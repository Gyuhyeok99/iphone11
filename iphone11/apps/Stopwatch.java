package iphone11.apps;

import javax.swing.*;
import java.awt.*;

public class Stopwatch extends JPanel {
    private static Stopwatch instance;
    public static Stopwatch getInstance() throws Exception {
        if (instance == null) {
            instance = new Stopwatch();
        }
        return instance;
    }
    private Stopwatch() {
        setLayout(new BorderLayout());
        setOpaque(true);
        setBackground(Color.BLACK);
    }
}
