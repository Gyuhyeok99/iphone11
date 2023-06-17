package iphone11.apps.game;

import iphone11.Home;
import iphone11.etc.DefaultSetting;
import iphone11.etc.north.NorthPanelV2;
import iphone11.panel.MainPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ClickBallGame extends JPanel {
    private int score = 0;
    private int WIDTH = 400;
    private int HEIGHT = 710;
    private int RADIUS = 20;

    private int ballX = WIDTH / 2;
    private int ballY = HEIGHT / 2;
    private int ballDX = 2;
    private int ballDY = -2;
    private int startY;

    public ClickBallGame() {
        setLayout(new BorderLayout());
        setBackground(Color.BLACK);

        // North part
        add(new NorthPanelV2(), BorderLayout.NORTH);

        setFocusable(true);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println(score);
                if (clickBall(e.getX(), e.getY())) {
                    score++;
                    if (ballDX > 0) {
                        ballDX += 1;
                    }else {
                        ballDX -= 1;
                    }
                    if (ballDY > 0) {
                        ballDY += 1;
                    }else {
                        ballDY -= 1;
                    }

                }
            }
        });
        Timer timer = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                movingBall();
                repaint();
            }
        });
        timer.start();
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
                    home.remove(ClickBallGame.this);
                    DefaultSetting.setContentPane(home, mainPanel);
                }
            }
        });
    }
    private boolean clickBall(int clickX, int clickY) {
        return (clickX > (ballX - RADIUS) && clickX < (ballX + RADIUS)) && (clickY > (ballY - RADIUS) && clickY < (ballY + RADIUS));
    }

    private void movingBall() {
        if (ballX + ballDX > WIDTH - RADIUS || ballX + ballDX < RADIUS) {
            ballDX = -ballDX;
        }
        if (ballY + ballDY > HEIGHT - RADIUS || ballY + ballDY < RADIUS) {
            ballDY = -ballDY;
        }

        ballX += ballDX;
        ballY += ballDY;
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.WHITE);
        g.fillOval(ballX - RADIUS, ballY - RADIUS, RADIUS * 2, RADIUS * 2);
        g.setFont(new Font(DefaultSetting.getInstance().getFontName(), DefaultSetting.getInstance().getFontStyle(), 20));
        g.drawString("Score: " + score, 20, 50);
        g.fillRoundRect(130, 694, 140, 8, 10, 10);
    }
}