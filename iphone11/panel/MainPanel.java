package iphone11.panel;

import iphone11.Home;
import iphone11.etc.*;
import iphone11.etc.north.NorthPanelV2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainPanel extends JPanel {
    private final JPanel Calculator;
    private final JPanel DrawingBoard;
    private final JPanel Notepad;
    private final JPanel Stopwatch;
    private final JPanel[] appPanels;
    private final JButton[] centerIcons;
    private TimeCount timeCount;
    private final ImageIcon background = Images.BACKGROUND;
    private final Image backgroundImage = background.getImage();
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
        add(new NorthPanelV2(), BorderLayout.NORTH);

        // CENTER part
        JPanel centerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 40));
        centerPanel.setOpaque(false);
        add(centerPanel, BorderLayout.CENTER);

        centerIcons = new JButton[4];
        centerIcons[0] = new JButton(Images.NOTEPAD);
        centerIcons[1] = new JButton(Images.CALCULATOR);
        centerIcons[2] = new JButton(Images.DRAWINGBOARD);
        centerIcons[3] = new JButton(Images.STOPWATCH);

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
        southIcons[0] = new JButton(Images.PHONE);
        southIcons[1] = new JButton(Images.GALLERY);
        southIcons[2] = new JButton(Images.MESSAGE);
        southIcons[3] = new JButton(Images.SETTING);

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

        DrawingBoard = iphone11.apps.drawingBoard.DrawingBoard.getInstance();
        Calculator = iphone11.apps.calculator.Calculator.getInstance();
        Notepad = iphone11.apps.notepad.Notepad.getInstance();
        Stopwatch = iphone11.apps.stopwatch.Stopwatch.getInstance();
        appPanels = new JPanel[]{Notepad,Calculator,DrawingBoard , Stopwatch};
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
