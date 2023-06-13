package iphone11;

import iphone11.panel.HomePanel;

import javax.swing.*;
import java.awt.*;

public class Home extends JFrame  {
    private final Container c = getContentPane();
    private final HomePanel homePanel = HomePanel.getInstance();
    public Home() throws Exception {
        setTitle("iphone11");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setSize(400, 730);
        homePanel.setBounds(0, 0, getWidth(), getHeight());

        c.add(homePanel);

        setResizable(false);
        setVisible(true);

    }

    public static void main(String[] args) throws Exception {
        new Home();
    }
}
