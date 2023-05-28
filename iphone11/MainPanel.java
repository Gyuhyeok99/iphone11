package iphone11;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainPanel extends JPanel {
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


    public MainPanel() {
        setLayout(new BorderLayout());
        // North part
        JPanel northPanel = new JPanel(new FlowLayout());
        northPanel.setOpaque(false);
        add(northPanel, BorderLayout.NORTH);

        JLabel minuteHour = Time.getHourMinute();
        minuteHour.setFont(new Font("Arial", Font.PLAIN, 22));
        JLabel whiteSpace = new JLabel("                                                ");

        JLabel lte = new JLabel("LTE");
        lte.setForeground(Color.WHITE);
        lte.setFont(new Font("Arial", Font.PLAIN, 18));

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

        JButton[] centerIcons = new JButton[4];
        centerIcons[0] = new JButton(notepad);
        centerIcons[1] = new JButton(calculator);
        centerIcons[2] = new JButton(drawingBoard);
        centerIcons[3] = new JButton(stopWatch);

        for(int i = 0; i < centerIcons.length; i++) {
            centerIcons[i].setOpaque(false);
            centerIcons[i].setContentAreaFilled(false);
            centerIcons[i].setBorderPainted(false);
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
            southIcons[i].setOpaque(false);
            southIcons[i].setContentAreaFilled(false);
            southIcons[i].setBorderPainted(false);
            southPanel.add(southIcons[i]);
        }

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                startY = e.getY();
            }
            @Override
            public void mouseReleased(MouseEvent e) {
                int endY = e.getY();
                if (endY > startY && startY <= 100) {
                    QuickSettingPanel quickSettingPanel = new QuickSettingPanel();
                    Home home = (Home) getTopLevelAncestor();
                    home.remove(MainPanel.this);
                    home.setContentPane(quickSettingPanel);
                    home.revalidate();
                    home.repaint();
                }
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        g.setColor(Color.darkGray);
        g.fillRoundRect(10, getHeight() - 118, 380, 110, 30, 30);
        g.setColor(Color.white);
        g.fillRoundRect(330, 30, 70, 5, 10, 10);
    }
}
