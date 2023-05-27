package home;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PwPanel extends JPanel {
    private final ImageIcon background = Images.BACKGROUND;
    private final Image backgroundImage = background.getImage();
    private final ImageIcon telecommunications = Images.TELECOMMUNICATIONS;
    private final ImageIcon battery = Images.BATTERY;
    private final ImageIcon lock = Images.LOCK;
    private final ImageIcon inputPw = Images.INPUT_PW;
    private final ImageIcon pw = Images.PW;

    public PwPanel() {
        setLayout(new BorderLayout());

        // North part
        JPanel northPanel = new JPanel(new FlowLayout());
        northPanel.setOpaque(false);
        add(northPanel, BorderLayout.NORTH);

        JLabel minuteHour = Time.getHourMinute();
        minuteHour.setFont(new Font("Arial", Font.PLAIN, 22));
        JLabel whiteSpace = new JLabel("                                                ");

        JLabel lte = new JLabel("LTE");
        lte.setForeground(Color.WHITE);
        lte.setFont(new Font("Arial", Font.PLAIN, 22));

        JLabel tel = new JLabel(telecommunications);
        JLabel bat = new JLabel(battery);

        northPanel.add(minuteHour);
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

        JLabel passcode = new JLabel("Enter Passcode");
        passcode.setForeground(Color.WHITE);
        passcode.setOpaque(false);
        passcode.setHorizontalAlignment(SwingConstants.CENTER);
        passcode.setFont(new Font("Arial", Font.PLAIN, 20));
        passcode.setBounds(100, 80, 200, 30);
        centerPanel.add(passcode);

        int pwX = 90; int pwY = 130; int pwWidth = 24; int pwHeight = 24;
        JLabel[] inputPwLabels = new JLabel[6];
        for(int i = 0; i < 6; i ++) {
            inputPwLabels[i] = new JLabel(inputPw);
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
            numBtns[i].setOpaque(false);
            numBtns[i].setContentAreaFilled(false);
            numBtns[i].setBorderPainted(false);
            numBtns[i].addActionListener(new ActionListener() {
                int count = 0;
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        for (int j = 0; j < inputPwLabels.length; j++) {
                            if (inputPwLabels[j].getIcon() == inputPw) {
                                inputPwLabels[j].setIcon(pw);
                                if (j < inputPwLabels.length - 1) {
                                    inputPwLabels[j + 1].setIcon(inputPw);
                                }
                                count++;
                                if (count == 6) {
                                    MainPanel mainPanel = new MainPanel();
                                    Home home = (Home)getTopLevelAncestor();
                                    home.remove(PwPanel.this);
                                    home.setContentPane(mainPanel);
                                    home.revalidate();
                                    home.repaint();
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
        JPanel actionPanel = new JPanel();
        actionPanel.setOpaque(false);
        add(actionPanel, BorderLayout.SOUTH);

        JButton[] jBtns = new JButton[2];
        JButton emergencyBtn = new JButton("Emergency");
        JButton cancelBtn = new JButton("Cancel");

        actionPanel.add(emergencyBtn);
        actionPanel.add(cancelBtn);


        jBtns[0] = cancelBtn;
        jBtns[1] = emergencyBtn;

        for (int i = 0; i < jBtns.length; i++) {
            jBtns[i].setForeground(Color.white);
            jBtns[i].setOpaque(false);
            jBtns[i].setContentAreaFilled(false);
            jBtns[i].setBorderPainted(false);
            jBtns[i].setFont(new Font("Arial", Font.PLAIN, 15));
        }

        JLabel clickMessage = new JLabel();
        clickMessage.setFont(new Font("Arial", Font.PLAIN, 15));
        clickMessage.setForeground(Color.WHITE);
        clickMessage.setHorizontalAlignment(SwingConstants.CENTER);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);

        g.setColor(Color.WHITE);
        g.fillRoundRect(140, getHeight() - 8, 140, 8, 10, 10);
    }
}