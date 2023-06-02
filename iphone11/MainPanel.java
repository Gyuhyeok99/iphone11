package iphone11;

import iphone11.etc.DefaultSetting;
import iphone11.etc.Images;
import iphone11.etc.Time;
import iphone11.etc.TimeCount;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainPanel extends JPanel {
    private final JPanel Calculator;
    private final JPanel DrawingBoard;
    private final JPanel Gallery;
    private final JPanel Stopwatch;
    private final JPanel[] appPanels;
    private final JButton[] centerIcons;
    private TimeCount timeCount;
    private final ImageIcon background = Images.BACKGROUND;
    private final Image backgroundImage = background.getImage();
    private final ImageIcon telecommunications = Images.TELECOMMUNICATIONS;
    private final ImageIcon battery = Images.BATTERY;
    private final ImageIcon gallery = Images.GALLERY;
    private final ImageIcon message = Images.MESSAGE;
    private final ImageIcon phone = Images.PHONE;
    private final ImageIcon setting = Images.SETTING;
    private final ImageIcon notepad = Images.NOTEPAD;
    private final ImageIcon calculator = Images.CALCULATOR;
    private final ImageIcon drawingBoard = Images.DRAWINGBOARD;
    private final ImageIcon stopWatch = Images.STOPWATCH;

    private int startY;

    private static MainPanel instance;
    public static MainPanel getInstance() throws Exception {
        if (instance == null) {
            instance = new MainPanel();
        }
        return instance;
    }
    public MainPanel() throws Exception {
        setLayout(new BorderLayout());
        // North part
        JPanel northPanel = new JPanel(new FlowLayout());
        northPanel.setOpaque(false);
        add(northPanel, BorderLayout.NORTH);

        JLabel minuteHour = Time.getHourMinute();
        minuteHour.setFont(new Font(DefaultSetting.getInstance().getFontName(), DefaultSetting.getInstance().getFontStyle(), 22));
        JLabel whiteSpace = new JLabel("                                                ");

        JLabel lte = new JLabel("LTE");
        lte.setForeground(Color.WHITE);
        lte.setFont(new Font(DefaultSetting.getInstance().getFontName(), DefaultSetting.getInstance().getFontStyle(), 18));

        JLabel tel = new JLabel(telecommunications);
        JLabel bat = new JLabel(battery);

        northPanel.add(minuteHour);
        northPanel.add(whiteSpace);
        northPanel.add(tel);
        northPanel.add(lte);
        northPanel.add(bat);

        // CENTER part
        JPanel centerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 40));
        centerPanel.setOpaque(false);
        add(centerPanel, BorderLayout.CENTER);

         centerIcons = new JButton[4];
        centerIcons[0] = new JButton(notepad);
        centerIcons[1] = new JButton(calculator);
        centerIcons[2] = new JButton(drawingBoard);
        centerIcons[3] = new JButton(stopWatch);

        for(int i = 0; i < centerIcons.length; i++) {
            DefaultSetting.btnSetting(centerIcons[i]);
            centerIcons[i].addActionListener(new AppsActionListener());
            centerPanel.add(centerIcons[i]);
        }

        // SOUTH part
        JPanel southPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 20));
        southPanel.setOpaque(false);
        add(southPanel, BorderLayout.SOUTH);

        JButton[] southIcons = new JButton[4];
        southIcons[0] = new JButton(phone);
        southIcons[1] = new JButton(gallery);
        southIcons[2] = new JButton(message);
        southIcons[3] = new JButton(setting);

        for (int i = 0; i < southIcons.length; i++) {
            DefaultSetting.btnSetting(southIcons[i]);
            southPanel.add(southIcons[i]);
        }
        setFocusable(true);
        requestFocus();

        timeCount = new TimeCount();

        ActionListener actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BlackPanel blackPanel = new BlackPanel();
                Home home = (Home)getTopLevelAncestor();
                if (home != null) {
                    DefaultSetting.setContentPane(home, blackPanel);
                }
            }
        };
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                System.out.println("Main 키입력좀 받아줘~");
                timeCount.start(actionListener);
            }
        });
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                timeCount.start(actionListener);
                startY = e.getY();
            }
            @Override
            public void mouseReleased(MouseEvent e) {
                timeCount.start(actionListener);
                int endY = e.getY();
                if (endY > startY && startY <= 100) {
                    QuickSettingPanel quickSettingPanel = null;
                    try {
                        quickSettingPanel = QuickSettingPanel.getInstance();
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }
                    Home home = (Home) getTopLevelAncestor();
                    DefaultSetting.setContentPane(home, quickSettingPanel);
                }
            }
        });
        timeCount.start(actionListener);

        DrawingBoard = iphone11.apps.DrawingBoard.getInstance();
        Calculator = iphone11.apps.Calculator.getInstance();
        Gallery = iphone11.apps.Gallery.getInstance();
        Stopwatch = iphone11.apps.Stopwatch.getInstance();
        appPanels = new JPanel[]{ Gallery,Calculator,DrawingBoard , Stopwatch};
    }
    class AppsActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton btn = (JButton) e.getSource();

            for (int i = 0; i < centerIcons.length; i++) {
                if (btn == centerIcons[i]) {
                    JPanel targetPanel = appPanels[i];
                    if (targetPanel != null) {
                        try {
                            Home home = (Home) getTopLevelAncestor();
                            DefaultSetting.setContentPane(home, targetPanel);
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                    break;
                }
            }
        }
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        g.setColor(Color.darkGray);
        g.fillRoundRect(10, getHeight() - 118, 380, 110, 30, 30);
        g.setColor(Color.white);
        g.fillRoundRect(330, 30, 70, 5, 10, 10);
        g.fillRoundRect(130, 694, 140, 8, 10, 10);
    }
}
