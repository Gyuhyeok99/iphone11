package iphone11;

import javax.swing.*;
import java.awt.*;

public class Home extends JFrame  {
    private final Container c = getContentPane();
    private final HomePanel homePanel = new HomePanel();
    public Home() {
        setTitle("iphone11");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setSize(400, 730);
        setVisible(true);
        homePanel.setBounds(0, 0, getWidth(), getHeight());
        c.add(homePanel);
    }

    public static void main(String[] args) {
        new Home();
    }
}
