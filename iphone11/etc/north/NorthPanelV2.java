package iphone11.etc.north;


import iphone11.etc.DefaultSetting;
import iphone11.etc.Images;
import iphone11.etc.Time;

import javax.swing.*;
import java.awt.*;

public class NorthPanelV2 extends JPanel {

    public NorthPanelV2() {
        setOpaque(false);
        setLayout(new FlowLayout());

        JLabel minuteHour = Time.getHourMinute();
        minuteHour.setFont(new Font(DefaultSetting.getInstance().getFontName(), DefaultSetting.getInstance().getFontStyle(), 22));
        JLabel whiteSpace = new JLabel("                                         ");
        JLabel lte = new JLabel("LTE");
        lte.setForeground(Color.WHITE);
        lte.setFont(new Font(DefaultSetting.getInstance().getFontName(), DefaultSetting.getInstance().getFontStyle(), 18));

        ImageIcon telecommunications = Images.TELECOMMUNICATIONS;
        ImageIcon battery = Images.BATTERY;

        JLabel tel = new JLabel(telecommunications);
        JLabel bat = new JLabel(battery);

        add(minuteHour);
        add(whiteSpace);
        add(tel);
        add(lte);
        add(bat);
    }

}
