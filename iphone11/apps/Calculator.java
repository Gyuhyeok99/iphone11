package iphone11.apps;

import iphone11.etc.DefaultSetting;
import iphone11.etc.Images;
import iphone11.etc.Time;

import javax.swing.*;
import java.awt.*;

public class Calculator extends JPanel {
    private ImageIcon[] calculatorImgs;
    private static Calculator instance;
    public static Calculator getInstance() throws Exception {
        if (instance == null) {
            instance = new Calculator();
        }
        return instance;
    }
    private final ImageIcon telecommunications = Images.TELECOMMUNICATIONS;
    private final ImageIcon battery = Images.BATTERY;
    private final JLabel result;
    private Calculator() {
        setLayout(new BorderLayout());
        setOpaque(true);
        setBackground(Color.BLACK);
        //NORTH prat
        JPanel northPanel = new JPanel(new FlowLayout());
        northPanel.setOpaque(false);
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


        add(northPanel, BorderLayout.NORTH);


        //CENTER part
        JPanel centerPanel = new JPanel(null);
        centerPanel.setOpaque(false);
        add(centerPanel, BorderLayout.CENTER);

        result = new JLabel("0");
        result.setForeground(Color.WHITE);
        result.setHorizontalAlignment(SwingConstants.LEFT);  // 왼쪽 정렬로 변경
        result.setFont(new Font(DefaultSetting.getInstance().getFontName(), DefaultSetting.getInstance().getFontStyle(), 50));
        result.setBounds(40, 80, 400, 100);
        centerPanel.add(result);

        calculatorImgs = new ImageIcon[19];
        JButton[] calculatorBtns = new JButton[19];
        calculatorImgs[0] = Images.AC;calculatorImgs[1] = Images.PLUS_MINUS;calculatorImgs[2] = Images.DEVIDE_100;
        calculatorImgs[3] = Images.DIVISION;calculatorImgs[4] = Images._7;calculatorImgs[5] = Images._8;
        calculatorImgs[6] = Images._9;calculatorImgs[7] = Images.MULTIPLICATION;calculatorImgs[8] = Images._4;
        calculatorImgs[9] = Images._5;calculatorImgs[10] = Images._6;calculatorImgs[11] = Images.MINUS;
        calculatorImgs[12] = Images._1;calculatorImgs[13] = Images._2;calculatorImgs[14] = Images._3;
        calculatorImgs[15] = Images.PLUS;calculatorImgs[16] = Images._0;calculatorImgs[17] = Images.MINORITY_POINT;calculatorImgs[18] = Images.EQUAL;

        for(int i = 0; i < calculatorImgs.length; i++) {
            calculatorBtns[i] = new JButton(calculatorImgs[i]);
            DefaultSetting.btnSetting(calculatorBtns[i]);
            centerPanel.add(calculatorBtns[i]);
        }
        int calX = 25, calY = 180, calWidth =60, calHeight = 60, interval = 90;
        for(int i = 0; i < 4; i++) {
            calculatorBtns[i].setBounds(calX + i * interval, calY, 80, 80);
        }
        for(int i = 4; i < 8; i++) {
            calculatorBtns[i].setBounds(calX + (i - 4) * interval, calY + interval, 80, 80);
        }
        for(int i = 8; i < 12; i++) {
            calculatorBtns[i].setBounds(calX + (i - 8) * interval, calY + interval * 2, 80, 80);
        }
        for(int i = 12; i < 16; i++) {
            calculatorBtns[i].setBounds(calX + (i - 12) * interval, calY + interval * 3, 80, 80);
        }
        calculatorBtns[16].setBounds(calX, calY + interval * 4, 173, 80);

        for(int i = 17; i < 19; i++) {
            calculatorBtns[i].setBounds(calX + (i - 15) * interval, calY + interval * 4, 80, 80);
        }


    }
}
