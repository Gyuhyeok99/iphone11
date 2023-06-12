package iphone11;

import iphone11.etc.Audios;
import iphone11.etc.DefaultSetting;
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
    private final ImageIcon mirroring = Images.MIRRORING;
    private final ImageIcon noDisturbance = Images.NO_DISTURBANCE;
    private final ImageIcon brightness = Images.BRIGHTNESS;
    private final ImageIcon volume = Images.VOLUME;
    private final ImageIcon flashlightOn = Images.FLASHLIGHT_ON;
    private final ImageIcon flashlightOff = Images.FLASHLIGHT_OFF;
    private final ImageIcon stopwatch2 = Images.STOPWATCH2;
    private final ImageIcon calculator2 = Images.CALCULATOR2;
    private final ImageIcon camera = Images.CAMERA;
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
        northPanel();

        //CENTER part
        JPanel centerPanel = new JPanel(null);
        centerPanel.setOpaque(false);
        add(centerPanel, BorderLayout.CENTER);

        //Communication button related
        JButton[] onOffBtns = new JButton[6];
        onOffBtns[0] = new JButton(airplaneOff);
        onOffBtns[1] = new JButton(dataOn);
        onOffBtns[2] = new JButton(wifiOff);
        onOffBtns[3] = new JButton(bluetoothOff);
        onOffBtns[4] = new JButton(fixOff);
        onOffBtns[5] = new JButton(flashlightOff);

        for(int i = 0; i < onOffBtns.length; i++) {
            DefaultSetting.btnSetting(onOffBtns[i]);
            centerPanel.add(onOffBtns[i]);
        }

        onOffBtns[0].setBounds(45, 0, 60, 60);
        onOffBtns[1].setBounds(115, 0, 60, 60);
        onOffBtns[2].setBounds(45, 65, 60, 60);
        onOffBtns[3].setBounds(115, 65, 60, 60);
        onOffBtns[4].setBounds(30, 158, 70, 70);
        onOffBtns[5].setBounds(30, 337, 71, 72);

        onOffBtns[0].addActionListener(new onOffActionListener(onOffBtns[0], airplaneOn, airplaneOff));
        onOffBtns[1].addActionListener(new onOffActionListener(onOffBtns[1], dataOn, dataOff));
        onOffBtns[2].addActionListener(new onOffActionListener(onOffBtns[2], wifiOn, wifiOff));
        onOffBtns[3].addActionListener(new onOffActionListener(onOffBtns[3], bluetoothOn, bluetoothOff));
        onOffBtns[4].addActionListener(new onOffActionListener(onOffBtns[4], fixOn, fixOff));
        onOffBtns[5].addActionListener(new onOffActionListener(onOffBtns[5], flashlightOn,flashlightOff));

        //music-related
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
        music.setFont(new Font(DefaultSetting.getInstance().getFontName(), DefaultSetting.getInstance().getFontStyle(), 18));

        audioBtn.setOpaque(false);
        audioBtn.setContentAreaFilled(false);
        audioBtn.setBorderPainted(false);
        audioBtn.setBounds(265, 65, 49, 49);
        centerPanel.add(music);
        centerPanel.add(audioBtn);

        //About screen mirroring
        JButton mirroringBtn = new JButton(mirroring);
        DefaultSetting.btnSetting(mirroringBtn);
        mirroringBtn.setBounds(128, 175, 54, 40);
        centerPanel.add(mirroringBtn);

        //Regarding the Do Not Disturb mode
        JButton disturbBtn = new JButton(noDisturbance);
        DefaultSetting.btnSetting(disturbBtn);
        disturbBtn.setBounds(45, 253, 60, 60);
        centerPanel.add(disturbBtn);

        JLabel focus = new JLabel("Focus");
        focus.setForeground(Color.WHITE);
        focus.setFont(new Font(DefaultSetting.getInstance().getFontName(), DefaultSetting.getInstance().getFontStyle(), 18));
        focus.setBounds(115, 235, 100, 100);
        centerPanel.add(focus);

        //volume and bright
        JLabel vo = new JLabel(volume);
        JLabel br = new JLabel(brightness);
        br.setBounds(225 ,260, 33, 40);
        vo.setBounds(320 ,260, 33, 40);
        centerPanel.add(vo);
        centerPanel.add(br);

        //Button to another screen
        JButton[] screenBtns = new JButton[3];
        screenBtns[0] = new JButton(stopwatch2);
        screenBtns[1] = new JButton(calculator2);
        screenBtns[2] = new JButton(camera);
        int screenX = 122, screenY = 337, screenWidth = 71, screenHeight = 72;
        for(int i = 0; i < screenBtns.length; i++) {
            DefaultSetting.btnSetting(screenBtns[i]);
            centerPanel.add(screenBtns[i]);
            screenBtns[i].setBounds(screenX + i * 87, screenY, screenWidth, screenHeight);
        }

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
                    DefaultSetting.setContentPane(home, blackPanel);
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
                    DefaultSetting.setContentPane(home, mainPanel);
                }
            }
        });

        timeCount.start(actionListener);
        setFocusable(true);
        requestFocus();
    }

    private void northPanel() {
        JPanel northPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0 , 60));
        northPanel.setOpaque(false);
        add(northPanel, BorderLayout.NORTH);

        JLabel whiteSpace = new JLabel("                                 ");

        JLabel lg = new JLabel("LG U+ ");
        lg.setFont(new Font(DefaultSetting.getInstance().getFontName(), DefaultSetting.getInstance().getFontStyle(), 18));
        lg.setForeground(Color.white);

        JLabel lte = new JLabel("LTE");
        lte.setForeground(Color.WHITE);
        lte.setFont(new Font(DefaultSetting.getInstance().getFontName(), DefaultSetting.getInstance().getFontStyle(), 18));

        JLabel intBattery = new JLabel("100%");
        intBattery.setForeground(Color.WHITE);
        intBattery.setFont(new Font(DefaultSetting.getInstance().getFontName(), DefaultSetting.getInstance().getFontStyle(), 18));


        JLabel tel = new JLabel(telecommunications);
        JLabel bat = new JLabel(battery);


        northPanel.add(tel);
        northPanel.add(lg);
        northPanel.add(lte);
        northPanel.add(whiteSpace);
        northPanel.add(intBattery);
        northPanel.add(bat);
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

        g.setColor(Color.white);
        g.fillRoundRect(310, 500, 50, 50, 10, 10);
        g.fillRoundRect(130, getHeight() - 8, 140, 8, 10, 10);
    }

    private class onOffActionListener implements ActionListener {
        private final JButton btn;
        private final ImageIcon on;
        private final ImageIcon off;

        public onOffActionListener(JButton btn, ImageIcon on, ImageIcon off) {
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
