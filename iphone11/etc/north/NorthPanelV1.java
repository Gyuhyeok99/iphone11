package iphone11.etc.north;


import iphone11.etc.DefaultSetting;
import iphone11.etc.Images;
import iphone11.etc.Time;

import javax.swing.*;
import java.awt.*;

public class NorthPanelV1 extends JPanel {

    public NorthPanelV1() {
        setOpaque(false);
        setLayout(new FlowLayout());

        JLabel lg = new JLabel("LG U+");
        lg.setFont(new Font(DefaultSetting.getInstance().getFontName(), DefaultSetting.getInstance().getFontStyle(), 18));
        lg.setForeground(Color.white);
        JLabel whiteSpace = new JLabel("                                                ");

        JLabel lte = new JLabel("LTE");
        lte.setForeground(Color.WHITE);
        lte.setFont(new Font(DefaultSetting.getInstance().getFontName(), DefaultSetting.getInstance().getFontStyle(), 18));

        JLabel tel = new JLabel(Images.TELECOMMUNICATIONS);
        JLabel bat = new JLabel(Images.BATTERY);

        add(lg);
        add(whiteSpace);
        add(tel);
        add(lte);
        add(bat);
    }

}
