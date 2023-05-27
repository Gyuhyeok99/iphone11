package home;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class HomePanel extends JPanel {
    private final ImageIcon background = new ImageIcon("images/backgroundImage.png");
    private final Image backgroundImage = background.getImage();
    private final JLabel hourMinute;
    private final JLabel dayMonth;
    private final JLabel clickMessage;
    private int startY;

    public HomePanel() {
        setLayout(null);
        hourMinute = new JLabel();
        hourMinute.setFont(new Font("Arial", Font.PLAIN, 60));
        hourMinute.setForeground(Color.WHITE);
        hourMinute.setHorizontalAlignment(SwingConstants.CENTER);
        add(hourMinute);

        dayMonth = new JLabel();
        dayMonth.setFont(new Font("Arial", Font.PLAIN, 15));
        dayMonth.setForeground(Color.WHITE);
        dayMonth.setHorizontalAlignment(SwingConstants.CENTER);
        add(dayMonth);

        clickMessage = new JLabel();
        clickMessage.setFont(new Font("Arial", Font.PLAIN, 15));
        clickMessage.setForeground(Color.WHITE);
        clickMessage.setHorizontalAlignment(SwingConstants.CENTER);
        add(clickMessage);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                startY = e.getY();
                clickMessage.setText("Swipe up to unlock");
                clickMessage.setBounds(110, 630, 200, 30);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                clickMessage.setText("");
            }
        });

        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (startY - e.getY() > 30) {
                    clickMessage.setText("Release to unlock");
                } else {
                    clickMessage.setText("");
                }
            }
        });
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH : mm");

        hourMinute.setText(now.format(formatter));
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
        g.fillRoundRect(140, getHeight() - 60, 140, 8, 10, 10);
    }
}