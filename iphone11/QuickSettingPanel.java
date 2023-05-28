package iphone11;

import iphone11.etc.Images;
import iphone11.etc.TimeCount;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class QuickSettingPanel extends JPanel {
    private TimeCount timeCount;
    private final ImageIcon background = Images.BACKGROUND;
    private final Image backgroundImage = background.getImage();
    private final ImageIcon telecommunications = Images.TELECOMMUNICATIONS;
    private final ImageIcon battery = Images.BATTERY;
    private final ImageIcon airplaneOn = Images.AIRPLANE_ON;
    private final ImageIcon airplaneOff = Images.AIRPLANE_OFF;
    private final ImageIcon bluetoothOn = Images.BLUETOOTH_ON;
    private final ImageIcon bluetoothOff = Images.BLUETOOTH_OFF;
    private final ImageIcon dataOn = Images.DATA_ON;
    private final ImageIcon dataOff = Images.DATA_OFF;
    private final ImageIcon wifiOn = Images.WIFI_ON;
    private final ImageIcon wifiOff = Images.WIFI_OFF;
    private int startY;
    public QuickSettingPanel() {
        setLayout(new BorderLayout());


        // North part
        JPanel northPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0 , 60));
        northPanel.setOpaque(false);
        add(northPanel, BorderLayout.NORTH);

        JLabel whiteSpace = new JLabel("                                 ");

        JLabel lg = new JLabel("LG U+ ");
        lg.setFont(new Font("Arial", Font.PLAIN, 18));
        lg.setForeground(Color.white);

        JLabel lte = new JLabel("LTE");
        lte.setForeground(Color.WHITE);
        lte.setFont(new Font("Arial", Font.PLAIN, 18));

        JLabel intBattery = new JLabel("100%");
        intBattery.setForeground(Color.WHITE);
        intBattery.setFont(new Font("Arial", Font.PLAIN, 18));


        JLabel tel = new JLabel(telecommunications);
        JLabel bat = new JLabel(battery);


        northPanel.add(tel);
        northPanel.add(lg);
        northPanel.add(lte);
        northPanel.add(whiteSpace);
        northPanel.add(intBattery);
        northPanel.add(bat);

        //CENTER part
        JPanel centerPanel = new JPanel(null);
        centerPanel.setOpaque(false);
        add(centerPanel, BorderLayout.CENTER);

        JButton[] CommunicationBtns = new JButton[4];
        CommunicationBtns[0] = new JButton(airplaneOff);
        CommunicationBtns[1] = new JButton(dataOn);
        CommunicationBtns[2] = new JButton(wifiOff);
        CommunicationBtns[3] = new JButton(bluetoothOff);

        for(int i = 0; i < CommunicationBtns.length; i++) {
            CommunicationBtns[i].setOpaque(false);
            CommunicationBtns[i].setContentAreaFilled(false);
            CommunicationBtns[i].setBorderPainted(false);
            centerPanel.add(CommunicationBtns[i]);
        }

        CommunicationBtns[0].setBounds(45, 0, 60, 60);
        CommunicationBtns[1].setBounds(115, 0, 60, 60);
        CommunicationBtns[2].setBounds(45, 65, 60, 60);
        CommunicationBtns[3].setBounds(115, 65, 60, 60);

        CommunicationBtns[0].addActionListener(new MyActionListener(CommunicationBtns[0], airplaneOn, airplaneOff));
        CommunicationBtns[1].addActionListener(new MyActionListener(CommunicationBtns[1], dataOn, dataOff));
        CommunicationBtns[2].addActionListener(new MyActionListener(CommunicationBtns[2], wifiOn, wifiOff));
        CommunicationBtns[3].addActionListener(new MyActionListener(CommunicationBtns[3], bluetoothOn, bluetoothOff));
        timeCount = new TimeCount();
        setFocusable(true);
        requestFocus();

        ActionListener actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BlackPanel blackPanel = new BlackPanel();
                Home home = (Home)getTopLevelAncestor();
                home.remove(QuickSettingPanel.this);
                home.setContentPane(blackPanel);
                home.revalidate();
                home.repaint();
            }
        };
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                System.out.println("왜 키입력을 못받니");
            }
            @Override
            public void keyPressed(KeyEvent e) {
                System.out.println("이유를 알고싶어");
                System.out.println("Key pressed: " + e.getKeyCode());
            }

            @Override
            public void keyReleased(KeyEvent e) {
                System.out.println("나 너무 힘들어 ㅜㅜ");
            }
        });

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                System.out.println("Timer reset");
                startY = e.getY();
                timeCount.start(actionListener);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (startY - e.getY() > 100) {
                    MainPanel mainPanel = new MainPanel();
                    Home home = (Home)getTopLevelAncestor();
                    home.remove(QuickSettingPanel.this);
                    home.setContentPane(mainPanel);
                    home.revalidate();
                    home.repaint();
                }
            }
        });


        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                timeCount.start(actionListener);
                if (startY - e.getY() > 30) {

                } else {
                }
            }
        });
        timeCount.start(actionListener);
        setFocusable(true);
        requestFocus();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int LEFT_X = 30; int RIGHT_X = 210; int TOP_Y = 120; int BOTTOM_Y = 300; int INTERVAL = 90;
        int BIG_WIDTH = 160; int SMALL_WIDTH = 70; int BIG_HEIGHT = 160; int SMALL_HEIGHT = 70; int ARC = 30;

        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        g.setColor(new Color(12, 12, 12, 200));
        g.fillRoundRect(LEFT_X, TOP_Y, BIG_WIDTH, BIG_HEIGHT, ARC, ARC);
        g.fillRoundRect(RIGHT_X, TOP_Y, BIG_WIDTH, BIG_HEIGHT, ARC, ARC);
        g.fillRoundRect(LEFT_X, BOTTOM_Y, SMALL_WIDTH, SMALL_HEIGHT, ARC, ARC);
        g.fillRoundRect(LEFT_X + INTERVAL, BOTTOM_Y, SMALL_WIDTH, SMALL_HEIGHT, ARC, ARC);
        g.fillRoundRect(LEFT_X, BOTTOM_Y + INTERVAL, BIG_WIDTH, SMALL_HEIGHT, ARC, ARC);
        g.fillRoundRect(RIGHT_X, BOTTOM_Y, SMALL_WIDTH, BIG_HEIGHT, ARC, ARC);
        g.fillRoundRect(RIGHT_X + INTERVAL, BOTTOM_Y, SMALL_WIDTH, BIG_HEIGHT, ARC, ARC);
        g.fillRoundRect(LEFT_X, BOTTOM_Y + 2*INTERVAL, SMALL_WIDTH, SMALL_HEIGHT, ARC, ARC);
        g.fillRoundRect(LEFT_X + INTERVAL, BOTTOM_Y + 2*INTERVAL, SMALL_WIDTH, SMALL_HEIGHT, ARC, ARC);
        g.fillRoundRect(RIGHT_X, BOTTOM_Y + 2*INTERVAL, SMALL_WIDTH, SMALL_HEIGHT, ARC, ARC);
        g.fillRoundRect(RIGHT_X + INTERVAL, BOTTOM_Y + 2*INTERVAL, SMALL_WIDTH, SMALL_HEIGHT, ARC, ARC);

        g.setColor(Color.white);
        g.fillRoundRect(130, getHeight() - 8, 140, 8, 10, 10);
    }

    private class MyActionListener implements ActionListener {
        private final JButton button;
        private final ImageIcon onIcon;
        private final ImageIcon offIcon;

        public MyActionListener(JButton button, ImageIcon onIcon, ImageIcon offIcon) {
            this.button = button;
            this.onIcon = onIcon;
            this.offIcon = offIcon;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            button.setIcon(button.getIcon() == offIcon ? onIcon : offIcon);
        }
    }
}
