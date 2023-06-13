package iphone11.panel;

import iphone11.Home;
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
        onOffBtns[0] = new JButton(Images.AIRPLANE_OFF);
        onOffBtns[1] = new JButton(Images.DATA_ON);
        onOffBtns[2] = new JButton(Images.WIFI_OFF);
        onOffBtns[3] = new JButton(Images.BLUETOOTH_OFF);
        onOffBtns[4] = new JButton(Images.FIX_OFF);
        onOffBtns[5] = new JButton(Images.FLASHLIGHT_OFF);

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

        onOffBtns[0].addActionListener(new onOffActionListener(onOffBtns[0], Images.AIRPLANE_ON, Images.AIRPLANE_OFF));
        onOffBtns[1].addActionListener(new onOffActionListener(onOffBtns[1], Images.DATA_ON,Images.DATA_OFF));
        onOffBtns[2].addActionListener(new onOffActionListener(onOffBtns[2], Images.WIFI_ON, Images.WIFI_OFF));
        onOffBtns[3].addActionListener(new onOffActionListener(onOffBtns[3], Images.BLUETOOTH_ON, Images.BLUETOOTH_OFF));
        onOffBtns[4].addActionListener(new onOffActionListener(onOffBtns[4], Images.FIX_ON, Images.FIX_OFF));
        onOffBtns[5].addActionListener(new onOffActionListener(onOffBtns[5], Images.FLASHLIGHT_ON,Images.FLASHLIGHT_OFF));

        //music-related
        JLabel music;
        JButton audioBtn;
        audios = Audios.getInstance();
        if(!(audios.getClip().isRunning())) {
             music = new JLabel("Not Playing");
             music.setBounds(245, -20, 140, 100);
             audioBtn = new JButton(Images.PLAYBACK);
        }else {
            music = new JLabel("Play Wake Up");
            audioBtn = new JButton(Images.STOP);
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
        JButton mirroringBtn = new JButton(Images.MIRRORING);
        DefaultSetting.btnSetting(mirroringBtn);
        mirroringBtn.setBounds(128, 175, 54, 40);
        centerPanel.add(mirroringBtn);

        //Regarding the Do Not Disturb mode
        JButton disturbBtn = new JButton(Images.NO_DISTURBANCE);
        DefaultSetting.btnSetting(disturbBtn);
        disturbBtn.setBounds(45, 253, 60, 60);
        centerPanel.add(disturbBtn);

        JLabel focus = new JLabel("Focus");
        focus.setForeground(Color.WHITE);
        focus.setFont(new Font(DefaultSetting.getInstance().getFontName(), DefaultSetting.getInstance().getFontStyle(), 18));
        focus.setBounds(115, 235, 100, 100);
        centerPanel.add(focus);

        //volume and bright
        JLabel vo = new JLabel(Images.VOLUME);
        JLabel br = new JLabel(Images.BRIGHTNESS);
        br.setBounds(225 ,260, 33, 40);
        vo.setBounds(320 ,260, 33, 40);
        centerPanel.add(vo);
        centerPanel.add(br);

        //Button to another screen
        JButton[] screenBtns = new JButton[3];
        screenBtns[0] = new JButton(Images.STOPWATCH2);
        screenBtns[1] = new JButton(Images.CALCULATOR2);
        screenBtns[2] = new JButton(Images.CAMERA);
        int screenX = 122, screenY = 337, screenWidth = 71, screenHeight = 72;
        for(int i = 0; i < screenBtns.length; i++) {
            DefaultSetting.btnSetting(screenBtns[i]);
            centerPanel.add(screenBtns[i]);
            screenBtns[i].setBounds(screenX + i * 87, screenY, screenWidth, screenHeight);
        }

        audioBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (audioBtn.getIcon() == Images.PLAYBACK) {
                    audios.getClip().start();
                    audioBtn.setIcon(Images.STOP);
                    music.setText("Play Wake Up");
                    music.setBounds(235, -20, 140, 100);
                } else {
                    audios.getClip().stop();
                    audioBtn.setIcon(Images.PLAYBACK);
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


        JLabel tel = new JLabel(Images.TELECOMMUNICATIONS);
        JLabel bat = new JLabel(Images.BATTERY);


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
