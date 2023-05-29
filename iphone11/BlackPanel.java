package iphone11;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class BlackPanel extends JPanel {

    public BlackPanel() {
        setLayout(null);
        setOpaque(true);
        setBackground(Color.BLACK);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    goHomePanel();
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                System.out.println("키입력좀 제발 이해해줘~~");
                try {
                    goHomePanel();
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        setFocusable(true);
        requestFocus();

    }

    private void goHomePanel() throws Exception {
        HomePanel homePanel = HomePanel.getInstance();
        Home home = (Home)getTopLevelAncestor();
        //home.remove(BlackPanel.this);
        home.setContentPane(homePanel);
        home.revalidate();
        home.repaint();
    }
}