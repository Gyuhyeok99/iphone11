package iphone11.panel;

import iphone11.Home;
import iphone11.etc.DefaultSetting;
import iphone11.etc.Images;
import iphone11.etc.TimeCount;
import iphone11.etc.north.NorthPanelV1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PwPanel extends JPanel {
    private TimeCount timeCount;
    private final ImageIcon background = Images.BACKGROUND;
    private final Image backgroundImage = background.getImage();
    private int count = 0;

    public PwPanel() {
        setLayout(new BorderLayout());

        // North part
        add(new NorthPanelV1(), BorderLayout.NORTH);

        // CENTER part
        JPanel centerPanel = new JPanel(null);
        centerPanel.setOpaque(false);
        add(centerPanel, BorderLayout.CENTER);

        JLabel lockImage = new JLabel(Images.LOCK);
        lockImage.setBounds(165, 0, 75, 75);
        centerPanel.add(lockImage);

        JLabel passcode = new JLabel("Enter Passcode");
        passcode.setForeground(Color.WHITE);
        passcode.setOpaque(false);
        passcode.setHorizontalAlignment(SwingConstants.CENTER);
        passcode.setFont(new Font(DefaultSetting.getInstance().getFontName(), DefaultSetting.getInstance().getFontStyle(), 20));
        passcode.setBounds(100, 80, 200, 30);
        centerPanel.add(passcode);

        int pwX = 90; int pwY = 130; int pwWidth = 24; int pwHeight = 24;
        JLabel[] inputPwLabels = new JLabel[6];
        for(int i = 0; i < 6; i ++) {
            inputPwLabels[i] = new JLabel(Images.INPUT_PW);
            inputPwLabels[i].setBounds(pwX + i * 40, pwY, pwWidth, pwHeight);
            centerPanel.add(inputPwLabels[i]);
        }

        ImageIcon[] numImgs = new ImageIcon[10];
        JButton[] numBtns = new JButton[10];
        int btnWidth = 80;
        int btnHeight = 80;
        int x = 60; int y = 200;
        for (int i = 0; i < numImgs.length; i++) {
            String num = "images/password/" + i + ".png";
            numImgs[i] = new ImageIcon(num);
            numBtns[i] = new JButton(numImgs[i]);
            DefaultSetting.btnSetting(numBtns[i]);
            numBtns[i].addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        for (int j = 0; j < inputPwLabels.length; j++) {
                            if (inputPwLabels[j].getIcon() == Images.INPUT_PW) {
                                inputPwLabels[j].setIcon(Images.PW);
                                if (j < inputPwLabels.length - 1) {
                                    inputPwLabels[j + 1].setIcon(Images.INPUT_PW);
                                }
                                count++;
                                if (count == 6) {
                                    count = 0;
                                    MainPanel mainPanel = null;
                                    try {
                                        mainPanel = MainPanel.getInstance();
                                    } catch (Exception ex) {
                                        throw new RuntimeException(ex);
                                    }
                                    Home home = (Home)getTopLevelAncestor();
                                    home.remove(PwPanel.this);
                                    DefaultSetting.setContentPane(home, mainPanel);
                                }
                                break;
                            }
                        }
                    }
                });
            centerPanel.add(numBtns[i]);
        }
        for(int i = 1; i < 4; i++) {
            numBtns[i].setBounds(x + (i - 1) * 100, y, btnWidth, btnHeight);
        }
        for(int i = 4; i < 7; i++) {
            y = 300;
            numBtns[i].setBounds(x + (i - 4) * 100, y, btnWidth, btnHeight);
        }
        for(int i = 7; i < 10; i++) {
            y = 400;
            numBtns[i].setBounds(x + (i - 7) * 100, y, btnWidth, btnHeight);
        }
        numBtns[0].setBounds(160, 500, btnWidth, btnHeight);

        //SOUTH part
        JPanel southPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 90, 20));
        southPanel.setOpaque(false);
        add(southPanel, BorderLayout.SOUTH);

        JButton[] jBtns = new JButton[2];
        JButton emergencyBtn = new JButton("Emergency");
        JButton cancelBtn = new JButton("Cancel");

        setFocusable(true);
        requestFocus();
        cancelBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                count = 0;
                HomePanel homePanel = null;
                try {
                    homePanel = HomePanel.getInstance();
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
                Home home = (Home)getTopLevelAncestor();
                home.remove(PwPanel.this);
                DefaultSetting.setContentPane(home, homePanel);
            }
        });

        southPanel.add(emergencyBtn);
        southPanel.add(cancelBtn);


        jBtns[0] = cancelBtn;
        jBtns[1] = emergencyBtn;

        for (int i = 0; i < jBtns.length; i++) {
            jBtns[i].setForeground(Color.white);
            DefaultSetting.btnSetting(jBtns[i]);
            jBtns[i].setFont(new Font(DefaultSetting.getInstance().getFontName(), DefaultSetting.getInstance().getFontStyle(), 15));
        }

        JLabel swipe = new JLabel();
        swipe.setFont(new Font(DefaultSetting.getInstance().getFontName(), DefaultSetting.getInstance().getFontStyle(), 15));
        swipe.setForeground(Color.WHITE);
        swipe.setHorizontalAlignment(SwingConstants.CENTER);

        timeCount = TimeCount.getInstance();
        setFocusable(true);
        requestFocus();
        ActionListener actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BlackPanel blackPanel = new BlackPanel();
                Home home = (Home)getTopLevelAncestor();
                if(home != null) {
                    home.remove(PwPanel.this);
                    DefaultSetting.setContentPane(home, blackPanel);
                }
            }
        };
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                timeCount.start(actionListener);
            }
        });

        timeCount.start(actionListener);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);

        g.setColor(Color.WHITE);
        g.fillRoundRect(130, getHeight() - 8, 140, 8, 10, 10);
    }
}