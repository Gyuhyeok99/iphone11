package iphone11.apps.game;

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

public class ClickBallHome extends JPanel {
    private int startY;
    private final ImageIcon background = Images.BACKGROUND;
    private final Image backgroundImage = background.getImage();
    private static ClickBallHome instance;

    public static ClickBallHome getInstance() {
        if(instance == null) {
            instance = new ClickBallHome();
        }
        return instance;
    }

    private ClickBallHome() {

        setLayout(new BorderLayout());
        // North part
        add(new NorthPanelV2(), BorderLayout.NORTH);

        //center part
        JPanel centerPanel = new JPanel(null);
        centerPanel.setOpaque(false);
        add(centerPanel, BorderLayout.CENTER);

        JLabel title = new JLabel("Click the Ball!!!");
        title.setForeground(Color.WHITE);
        title.setFont(new Font(DefaultSetting.getInstance().getFontName(), DefaultSetting.getInstance().getFontStyle(), 40));
        title.setBounds(65, 100, 300,40);
        centerPanel.add(title);

        JButton gameStart = new JButton("Game Start");
        DefaultSetting.btnSetting(gameStart);
        gameStart.setForeground(Color.WHITE);
        gameStart.setBounds(100, 395,200,50);
        gameStart.setFont(new Font(DefaultSetting.getInstance().getFontName(), DefaultSetting.getInstance().getFontStyle(), 30));
        centerPanel.add(gameStart);

        gameStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ClickBallGame clickBallGame = new ClickBallGame();
                Home home = (Home) getTopLevelAncestor();
                DefaultSetting.setContentPane(home, clickBallGame);
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

                    Home home = (Home)getTopLevelAncestor();
                    DefaultSetting.setContentPane(home, mainPanel);
                }
            }
        });
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        g.setColor(Color.white);
        g.fillRoundRect(130, 694, 140, 8, 10, 10);
        g.setColor(Color.darkGray);
        g.fillRoundRect(100, 430, 200, 50, 30, 30);
    }

}
