package iphone11.apps.call;

import iphone11.Home;
import iphone11.etc.DefaultSetting;
import iphone11.etc.Images;
import iphone11.etc.TimeCount;
import iphone11.etc.north.NorthPanelV2;
import iphone11.panel.BlackPanel;
import iphone11.panel.MainPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Calling extends JPanel {
    private TimeCount timeCount;
    private Call call;
    private int startY;

    public Calling() {
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
        timeCount.start(actionListener);
        setLayout(new BorderLayout());
        setOpaque(true);
        setBackground(Color.black);

        //north part
        add(new NorthPanelV2(), BorderLayout.NORTH);

        JPanel centerPanel = new JPanel(null);
        centerPanel.setOpaque(false);
        add(centerPanel, BorderLayout.CENTER);

        call = Call.getInstance();
        call.setPhoneNum(call.getPhone().getText());
        JLabel phoneNum = call.getPhoneNum();
        phoneNum.setForeground(Color.WHITE);
        phoneNum.setHorizontalAlignment(SwingConstants.CENTER);
        phoneNum.setFont(new Font(DefaultSetting.getInstance().getFontName(), DefaultSetting.getInstance().getFontStyle(), 30));
        phoneNum.setBounds(0, 40, 400, 50);
        centerPanel.add(phoneNum);

        JLabel calling = new JLabel("calling...");
        calling.setForeground(Color.GRAY);
        calling.setHorizontalAlignment(SwingConstants.CENTER);
        calling.setFont(new Font(DefaultSetting.getInstance().getFontName(), DefaultSetting.getInstance().getFontStyle(), 20));
        calling.setBounds(145, 90, 120, 30);
        centerPanel.add(calling);

        JButton[] callingBtns = new JButton[7];
        callingBtns[0] = new JButton(Images.MUTE);
        callingBtns[1] = new JButton(Images.KEYPAD);
        callingBtns[2] = new JButton(Images.SPEAKER);
        callingBtns[3] = new JButton(Images.ADD_CALL);
        callingBtns[4] = new JButton(Images.FACE_TIME);
        callingBtns[5] = new JButton(Images.CONTACTS);
        callingBtns[6] = new JButton(Images.END_OF_CALL);

        for(int i = 0; i < callingBtns.length; i++){
            DefaultSetting.btnSetting(callingBtns[i]);
            centerPanel.add(callingBtns[i]);
        }

        int btnWidth = 80;
        int btnHeight = 80;
        int x = 60; int y = 200;
        for(int i = 0; i < 3; i++) {
            callingBtns[i].setBounds(x + i * 100, y, btnWidth, btnHeight);
        }
        for(int i = 3; i < 6; i++) {
            callingBtns[i].setBounds(x + (i - 3) * 100, y + 120, btnWidth, btnHeight );
        }
        callingBtns[6].setBounds(x + 100, y + 300, btnWidth, btnHeight);

        JLabel[] callingBtnNames = new JLabel[6];
        callingBtnNames[0] = new JLabel("mute");
        callingBtnNames[1] = new JLabel("keypad");
        callingBtnNames[2] = new JLabel("speaker");
        callingBtnNames[3] = new JLabel("add call");
        callingBtnNames[4] = new JLabel("FaceTime");
        callingBtnNames[5] = new JLabel("contacts");

        for(int i = 0; i < callingBtnNames.length; i++) {
            callingBtnNames[i].setOpaque(false);
            callingBtnNames[i].setFont(new Font(DefaultSetting.getInstance().getFontName(), DefaultSetting.getInstance().getFontStyle(), 15));
            callingBtnNames[i].setForeground(Color.WHITE);
            centerPanel.add(callingBtnNames[i]);
        }
        callingBtnNames[3].setForeground(Color.GRAY);
        callingBtnNames[4].setForeground(Color.GRAY);

        for(int i = 0; i < 3; i++){
            callingBtnNames[i].setBounds(82 + i * 95, 280, 100, 20);
        }

        for(int i = 3; i < 6; i++){
            callingBtnNames[i].setBounds(74 + (i - 3) * 99, 400, 100, 20);
        }

        callingBtns[6].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                call.setSavePhoneNum("");
                call.getPhone().setText("");
                call.setNowCalling(false);
                JPanel centerPanel1 = call.getCenterPanel();
                Home home = (Home) getTopLevelAncestor();
                DefaultSetting.setContentPane(home, call);
                timeCount.start(actionListener);
            }
        });

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                startY = e.getY();
                timeCount.start(actionListener);
            }
            @Override
            public void mouseReleased(MouseEvent e) {

                if (startY - e.getY() > 100) {
                    MainPanel mainPanel = null;
                    try {
                        mainPanel = MainPanel.getInstance();
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }
                    Home home = (Home) getTopLevelAncestor();
                    DefaultSetting.setContentPane(home, mainPanel);
                }
            }
        });
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.WHITE);
        g.fillRoundRect(130, 694, 140, 8, 10, 10);
    }
}
