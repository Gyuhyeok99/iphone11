package home;

import javax.swing.*;
import java.awt.*;

public class Home extends JFrame  {
    private final Container c = getContentPane();

    private final HomePanel homePanel = new HomePanel();

    public Home() {
        setTitle("iphone11");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        createMenu();
        setSize(407, 730);
        setVisible(true);


        homePanel.setBounds(0, 0, getWidth(), getHeight());
        c.add(homePanel);
    }
    private void createMenu() {
        JMenuBar mb = new JMenuBar();

        mb.setOpaque(true);
        mb.setBorderPainted(false);
        mb.setBackground(new Color(25, 25, 112));

        mb.add(new JMenu("  LG U+"));
        mb.add(new JMenu("                                                      "));

        JLabel telecommunications = new JLabel();
        telecommunications.setIcon(new ImageIcon("images/telecommunications.jpg"));
        mb.add(telecommunications);

        mb.add(new JMenu("LTE"));

        JLabel battery = new JLabel();
        battery.setIcon(new ImageIcon("images/battery.jpg"));
        mb.add(battery);

        setJMenuBar(mb);
    }

    public static void main(String[] args) {
        new Home();
    }
}
