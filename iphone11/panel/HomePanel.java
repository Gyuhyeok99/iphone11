package iphone11.panel;

import iphone11.Home;
import iphone11.etc.DefaultSetting;
import iphone11.etc.Images;
import iphone11.etc.Time;
import iphone11.etc.TimeCount;
import iphone11.etc.north.NorthPanelV1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.time.DayOfWeek;
import java.time.LocalDateTime;

public class HomePanel extends JPanel {
    private TimeCount timeCount;
    private final ImageIcon background = Images.BACKGROUND;
    private final Image backgroundImage = background.getImage();
    private final JLabel dayMonth;
    private final JLabel hourMinute;
    private int startY;
    private static HomePanel instance;
    public static HomePanel getInstance() throws Exception {
        if (instance == null) {
            instance = new HomePanel();
        }
        return instance;
    }

    private HomePanel() {
        setLayout(new BorderLayout());
        // North part
        add(new NorthPanelV1(), BorderLayout.NORTH );

        // CENTER part
        JPanel centerPanel = new JPanel(null);
        centerPanel.setOpaque(false);
        add(centerPanel, BorderLayout.CENTER);

        JLabel lockImage = new JLabel(Images.LOCK);
        lockImage.setBounds(165, 0, 75, 75);
        centerPanel.add(lockImage);

        hourMinute = Time.getHourMinute();
        centerPanel.add(hourMinute);

        dayMonth = new JLabel();
        dayMonth.setFont(new Font(DefaultSetting.getInstance().getFontName(), DefaultSetting.getInstance().getFontStyle(), 15));
        dayMonth.setForeground(Color.WHITE);
        dayMonth.setHorizontalAlignment(SwingConstants.CENTER);
        centerPanel.add(dayMonth);

        JLabel swipeUp = new JLabel();
        swipeUp.setFont(new Font(DefaultSetting.getInstance().getFontName(), DefaultSetting.getInstance().getFontStyle(), 15));
        swipeUp.setForeground(Color.WHITE);
        swipeUp.setHorizontalAlignment(SwingConstants.CENTER);
        centerPanel.add(swipeUp);

        setFocusable(true);
        requestFocus();

        timeCount = TimeCount.getInstance();
        ActionListener actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BlackPanel blackPanel = new BlackPanel();
                Home home = (Home) getTopLevelAncestor();
                if (home != null) {
                   DefaultSetting.setContentPane(home, blackPanel);
                }
            }
        };

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                startY = e.getY();
                swipeUp.setText("Swipe up to unlock      ");
                swipeUp.setBounds(110, 630, 200, 30);
                timeCount.start(actionListener);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                swipeUp.setText("");

                if (startY - e.getY() > 100) {
                    PwPanel pwPanel = new PwPanel();
                    Home home = (Home)getTopLevelAncestor();
                    DefaultSetting.setContentPane(home, pwPanel);
                }
            }
        });

        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (startY - e.getY() > 30) {
                    swipeUp.setText("Release to unlock      ");
                } else {
                    swipeUp.setText("");
                }
            }
        });

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                timeCount.start(actionListener);
            }
        });

        timeCount.start(actionListener);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);

        LocalDateTime now = LocalDateTime.now();
        Time.updateHourMinute(hourMinute);
        hourMinute.setBounds(0, 80, getWidth(), 60);

        int month = now.getMonthValue();
        String[] months = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
        String nowMonth = months[month - 1];
        DayOfWeek dayOfWeek = now.getDayOfWeek();

        String day = Integer.toString(now.getDayOfMonth());
        String days = dayOfWeek + ", " + nowMonth + " " + day;

        dayMonth.setText(days);
        dayMonth.setBounds((getWidth() - 200) / 2, 120, 200, 60);

        g.setColor(Color.WHITE);
        g.fillRoundRect(130, 694, 140, 8, 10, 10);
    }
}