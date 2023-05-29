package iphone11;

import iphone11.etc.Audios;
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
    private final ImageIcon fixOn = Images.FIX_ON;
    private final ImageIcon fixOff = Images.FIX_OFF;
    private final ImageIcon playback = Images.PLAYBACK;
    private final ImageIcon stop = Images.STOP;
    private final Audios audios;

    private int startY;
    private static QuickSettingPanel instance;

    public static QuickSettingPanel getInstance() throws Exception {
        if (instance == null) {
            instance = new QuickSettingPanel();
        }
        return instance;
    }
    private QuickSettingPanel() throws Exception {
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

        JButton[] CommunicationBtns = new JButton[5];
        CommunicationBtns[0] = new JButton(airplaneOff);
        CommunicationBtns[1] = new JButton(dataOn);
        CommunicationBtns[2] = new JButton(wifiOff);
        CommunicationBtns[3] = new JButton(bluetoothOff);
        CommunicationBtns[4] = new JButton(fixOff);

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
        CommunicationBtns[4].setBounds(30, 158, 70, 70);

        CommunicationBtns[0].addActionListener(new ComActionListener(CommunicationBtns[0], airplaneOn, airplaneOff));
        CommunicationBtns[1].addActionListener(new ComActionListener(CommunicationBtns[1], dataOn, dataOff));
        CommunicationBtns[2].addActionListener(new ComActionListener(CommunicationBtns[2], wifiOn, wifiOff));
        CommunicationBtns[3].addActionListener(new ComActionListener(CommunicationBtns[3], bluetoothOn, bluetoothOff));
        CommunicationBtns[4].addActionListener(new ComActionListener(CommunicationBtns[4], fixOn, fixOff));

        JLabel music;
        JButton audioBtn;
        audios = Audios.getInstance();
        if(!(audios.getClip().isRunning())) {
             music = new JLabel("Not Playing");
             music.setBounds(245, -20, 140, 100);
             audioBtn = new JButton(playback);
        }else {
            music = new JLabel("Play Wake Up");
            audioBtn = new JButton(stop);
            music.setBounds(235, -20, 140, 100);
        }
        music.setOpaque(false);
        music.setForeground(Color.WHITE);
        music.setFont(new Font("Arial", Font.PLAIN, 18));


        audioBtn.setOpaque(false);
        audioBtn.setContentAreaFilled(false);
        audioBtn.setBorderPainted(false);
        audioBtn.setBounds(265, 65, 49, 49);
        centerPanel.add(music);
        centerPanel.add(audioBtn);

        audioBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (audioBtn.getIcon() == playback) {
                    audios.getClip().start();
                    audioBtn.setIcon(stop);
                    music.setText("Play Wake Up");
                    music.setBounds(235, -20, 140, 100);
                } else {
                    audios.getClip().stop();
                    audioBtn.setIcon(playback);
                    music.setText("Not Playing");
                    music.setBounds(245, -20, 140, 100);
                }
            }
        });

        timeCount = new TimeCount();
        setFocusable(true);
        requestFocus();

        ActionListener actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BlackPanel blackPanel = new BlackPanel();
                Home home = (Home)getTopLevelAncestor();
                if(home != null) {
                    home.remove(QuickSettingPanel.this);
                    home.setContentPane(blackPanel);
                    home.revalidate();
                    home.repaint();
                }
            }
        };
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                timeCount.start(actionListener);
                System.out.println("왜 키입력을 못받니");
            }
            @Override
            public void keyPressed(KeyEvent e) {
                timeCount.start(actionListener);
                System.out.println("이유를 알고싶어");
                System.out.println("Key pressed: " + e.getKeyCode());
            }

            @Override
            public void keyReleased(KeyEvent e) {
                timeCount.start(actionListener);
                System.out.println("나 너무 힘들어 ㅜㅜ");
            }
        });

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                startY = e.getY();
                timeCount.start(actionListener);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (startY - e.getY() > 100) {
                    MainPanel mainPanel = null;
                    try {
                        mainPanel = MainPanel.getInstance();
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }
                    Home home = (Home)getTopLevelAncestor();
                    home.setContentPane(mainPanel);
                    //home.remove(QuickSettingPanel.this);
                    home.revalidate();
                    home.repaint();
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

    private class ComActionListener implements ActionListener {
        private final JButton btn;
        private final ImageIcon on;
        private final ImageIcon off;

        public ComActionListener(JButton btn, ImageIcon on, ImageIcon off) {
            this.btn = btn;
            this.on = on;
            this.off = off;
        }
        @Override
        public void actionPerformed(ActionEvent e) {

            if (btn.getIcon() == off) {
                btn.setIcon(on);
            } else {
                btn.setIcon(off);
            }
        }
    }
}
