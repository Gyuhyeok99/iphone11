package iphone11;

import iphone11.etc.Images;
import iphone11.etc.Time;
import iphone11.etc.TimeCount;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.time.DayOfWeek;
import java.time.LocalDateTime;

public class HomePanel extends JPanel {
    private TimeCount timeCount;
    private final ImageIcon background = Images.BACKGROUND;
    private final Image backgroundImage = background.getImage();
    private final ImageIcon telecommunications = Images.TELECOMMUNICATIONS;
    private final ImageIcon battery = Images.BATTERY;
    private final ImageIcon lock = Images.LOCK;
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
        JPanel northPanel = new JPanel(new FlowLayout());
        northPanel.setOpaque(false);
        add(northPanel, BorderLayout.NORTH);

        JLabel lg = new JLabel("LG U+");
        lg.setFont(new Font("Arial", Font.PLAIN, 18));
        lg.setForeground(Color.white);
        JLabel whiteSpace = new JLabel("                                                ");

        JLabel lte = new JLabel("LTE");
        lte.setForeground(Color.WHITE);
        lte.setFont(new Font("Arial", Font.PLAIN, 18));

        JLabel tel = new JLabel(telecommunications);
        JLabel bat = new JLabel(battery);

        northPanel.add(lg);
        northPanel.add(whiteSpace);
        northPanel.add(tel);
        northPanel.add(lte);
        northPanel.add(bat);

        // CENTER part
        JPanel centerPanel = new JPanel(null);
        centerPanel.setOpaque(false);
        add(centerPanel, BorderLayout.CENTER);

        JLabel lockImage = new JLabel(lock);
        lockImage.setBounds(165, 0, 75, 75);
        centerPanel.add(lockImage);

        hourMinute = Time.getHourMinute();
        centerPanel.add(hourMinute);

        dayMonth = new JLabel();
        dayMonth.setFont(new Font("Arial", Font.PLAIN, 15));
        dayMonth.setForeground(Color.WHITE);
        dayMonth.setHorizontalAlignment(SwingConstants.CENTER);
        centerPanel.add(dayMonth);

        JLabel swipeUp = new JLabel();
        swipeUp.setFont(new Font("Arial", Font.PLAIN, 15));
        swipeUp.setForeground(Color.WHITE);
        swipeUp.setHorizontalAlignment(SwingConstants.CENTER);
        centerPanel.add(swipeUp);
        setFocusable(true);
        requestFocus();
        timeCount = new TimeCount();
        ActionListener actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BlackPanel blackPanel = new BlackPanel();
                Home home = (Home) getTopLevelAncestor();
                if (home != null) {
                    home.remove(HomePanel.this);
                    home.setContentPane(blackPanel);
                    home.revalidate();
                    home.repaint();
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
                    home.remove(HomePanel.this);
                    home.setContentPane(pwPanel);
                    home.revalidate();
                    home.repaint();
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