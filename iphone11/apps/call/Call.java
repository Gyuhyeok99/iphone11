package iphone11.apps.call;


import iphone11.Home;
import iphone11.etc.DefaultSetting;
import iphone11.etc.Images;
import iphone11.etc.north.NorthPanelV2;
import iphone11.panel.MainPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Call extends JPanel {

    private static Call instance;
    public static Call getInstance() {
        if(instance == null) {
            instance = new Call();
        }
        return instance;
    }
    private JLabel phoneNum;
    private String savePhoneNum = "";
    private int startY;
    private boolean nowCalling;
    private JPanel centerPanel;

    public JLabel getPhoneNum() {
        return phoneNum;
    }

    public void setNowCalling(boolean nowCalling) {
        this.nowCalling = nowCalling;
    }

    public boolean isNowCalling() {
        return nowCalling;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum.setText(phoneNum);
        this.phoneNum.repaint();
    }
    public void refreshPhoneNum() {
        phoneNum.setText(savePhoneNum);
        phoneNum.revalidate();
        phoneNum.repaint();
    }
    public void setSavePhoneNum(String savePhoneNum) {
        this.savePhoneNum = savePhoneNum;
        repaint();
    }

    public JPanel getCenterPanel() {
        return centerPanel;
    }

    private Call() {

        setLayout(new BorderLayout());
        setOpaque(true);
        setBackground(Color.black);

        //north part
        add(new NorthPanelV2(), BorderLayout.NORTH);

        //center part
        centerPanel = new JPanel(null);
        centerPanel.setOpaque(false);
        add(centerPanel, BorderLayout.CENTER);

        phoneNum = new JLabel("afds");
        phoneNum.setForeground(Color.WHITE);
        phoneNum.setHorizontalAlignment(SwingConstants.CENTER);
        phoneNum.setFont(new Font(DefaultSetting.getInstance().getFontName(), DefaultSetting.getInstance().getFontStyle(), 30));
        phoneNum.setBounds(0, 0, 400, 100);
        centerPanel.add(phoneNum);

        int btnWidth = 80;
        int btnHeight = 80;
        int x = 60; int y = 100;
        ImageIcon[] numImgs = new ImageIcon[10];
        JButton[] numBtns = new JButton[14];
        for(int i = 1; i <= 9; i++) {
            String num = "images/password/" + i + ".png";
            numImgs[i] = new ImageIcon(num);
            numBtns[i] = new JButton(numImgs[i]);
            DefaultSetting.btnSetting(numBtns[i]);
            centerPanel.add(numBtns[i]);
        }
        numBtns[0] = new JButton(Images._0_1);
        numBtns[10] = new JButton(Images.ASTERISK);
        numBtns[11] = new JButton(Images.HASH);
        numBtns[12] = new JButton(Images.CALL);
        numBtns[13] = new JButton(Images.CLEAR);

        JButton[] btns = new JButton[] {numBtns[10],numBtns[0], numBtns[11]};
        for(int i = 1; i < 4; i++) {
            numBtns[i].setBounds(x + (i - 1) * 100, y, btnWidth, btnHeight);
        }
        for(int i = 4; i < 7; i++) {
            y = 200;
            numBtns[i].setBounds(x + (i - 4) * 100, y, btnWidth, btnHeight);
        }
        for(int i = 7; i < 10; i++) {
            y = 300;
            numBtns[i].setBounds(x + (i - 7) * 100, y, btnWidth, btnHeight);
        }
        for(int i = 0; i < btns.length; i++) {
            y = 400;
            btns[i].setBounds(x + i * 100, y, btnWidth, btnHeight);
            DefaultSetting.btnSetting(btns[i]);
            centerPanel.add(btns[i]);
        }
        numBtns[12].setBounds(x + 100, 500, btnWidth, btnHeight);
        DefaultSetting.btnSetting(numBtns[12]);
        centerPanel.add(numBtns[12]);
        numBtns[13].setBounds(x + 215, 520, 40, 28);
        DefaultSetting.btnSetting(numBtns[13]);
        centerPanel.add(numBtns[13]);

        for (int i = 0; i <= 9; i++) {
            int num = i;
            numBtns[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String inputText = phoneNum.getText();
                    phoneNum.setText(inputText + num);
                    savePhoneNum += num;
                    System.out.println(phoneNum.getText());
                    System.out.println(savePhoneNum);
                }
            });
        }
        JLabel south = new JLabel(Images.SOUTH);
        south.setBounds(0, 590,400, 67);
        centerPanel.add(south);

        numBtns[10].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String inputText = phoneNum.getText();
                phoneNum.setText(inputText + "*");
                savePhoneNum += "*";
            }
        });
        numBtns[11].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String inputText = phoneNum.getText();
                phoneNum.setText(inputText + "#");
                savePhoneNum += "#";
            }
        });
        numBtns[12].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Calling calling = Calling.getInstance();
                Home home = (Home) getTopLevelAncestor();
                DefaultSetting.setContentPane(home, calling);
                nowCalling = true;
            }
        });
        numBtns[13].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                phoneNum.setText("");
                savePhoneNum = "";
            }
        });
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                startY = e.getY();
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
        g.setColor(Color.white);
        g.fillRoundRect(130, 694, 140, 8, 10, 10);
    }
}
