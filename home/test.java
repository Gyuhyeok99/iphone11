package home;

import javax.swing.*;
import java.awt.*;

public class test extends JFrame {

    public test() {
        setTitle("iphone11");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        ImageIcon icon1 = new ImageIcon("images/appIcon/message.png");
        JLabel label1 = new JLabel(icon1);
        ImageIcon icon2 = new ImageIcon("images/appIcon/phone.png");
        JLabel label2 = new JLabel(icon2);
        ImageIcon icon3 = new ImageIcon("images/appIcon/setting.png");
        JLabel label3 = new JLabel(icon3);
        ImageIcon icon4 = new ImageIcon("images/appIcon/gallery.png");
        JLabel label4 = new JLabel(icon4);


        setBackground(Color.BLACK);
        getContentPane().setBackground(Color.BLACK);
        add(label1);
        add(label2);
        add(label3);
        add(label4);


        setSize(400, 730);



        setVisible(true);

    }

    public static void main(String[] args) {
        new test();
    }


}
