package iphone11;

import javax.swing.*;
import java.awt.*;

public class test extends JFrame {
    private final ImageIcon airplaneOn = Images.AIRPLANE_ON;
    private final ImageIcon airplaneOff = Images.AIRPLANE_OFF;
    private final ImageIcon bluetoothOn = Images.BLUETOOTH_ON;
    private final ImageIcon bluetoothOff = Images.BLUETOOTH_OFF;
    private final ImageIcon dataOn = Images.DATA_ON;
    private final ImageIcon dataOff = Images.DATA_OFF;
    private final ImageIcon wifiOn = Images.WIFI_ON;
    private final ImageIcon wifiOff = Images.WIFI_OFF;

    public test() {
        setTitle("iphone11");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        JButton[] CommunicationBtns = new JButton[8];
        CommunicationBtns[0] = new JButton(airplaneOff);
        CommunicationBtns[1] = new JButton(bluetoothOff);
        CommunicationBtns[2] = new JButton(dataOff);
        CommunicationBtns[3] = new JButton(wifiOff);
        CommunicationBtns[4] = new JButton(airplaneOn);
        CommunicationBtns[5] = new JButton(bluetoothOn);
        CommunicationBtns[6] = new JButton(dataOn);
        CommunicationBtns[7] = new JButton(wifiOn);

        for(int i = 0; i < CommunicationBtns.length; i++) {
            CommunicationBtns[i].setOpaque(false);
            CommunicationBtns[i].setContentAreaFilled(false);
            CommunicationBtns[i].setBorderPainted(false);
            add(CommunicationBtns[i]);
        }
        //CommunicationBtns[0].setBounds(35, 105, 60, 60);



        setSize(400, 730);



        setVisible(true);

    }

    public static void main(String[] args) {
        new test();
    }


}
