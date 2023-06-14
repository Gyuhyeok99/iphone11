package iphone11.apps.phone;

import iphone11.apps.gallery.Gallery;

import javax.swing.*;
import java.awt.*;

public class Phone extends JPanel {

    private static Phone instance;
    public static Phone getInstance() {
        if(instance == null) {
            instance = new Phone();
        }
        return instance;
    }

    private Phone() {

        setLayout(new BorderLayout());
        setOpaque(true);
        setBackground(Color.green);

    }
}
