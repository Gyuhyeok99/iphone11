package iphone11.etc;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Time {
    public static JLabel getHourMinute() {
        JLabel hourMinute = new JLabel();
        hourMinute.setFont(new Font("Arial", Font.PLAIN, 60));
        hourMinute.setForeground(Color.WHITE);
        hourMinute.setHorizontalAlignment(SwingConstants.CENTER);
        updateHourMinute(hourMinute);
        return hourMinute;
    }
    public static void updateHourMinute(JLabel hourMinute) {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH : mm");
        hourMinute.setText(now.format(formatter));
    }
}
