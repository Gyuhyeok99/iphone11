package iphone11.etc;
import iphone11.Home;
import iphone11.panel.MainPanel;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GoMainPanel extends MouseAdapter {
    private Container c;
    private int startY;
    public GoMainPanel(Container c) {
        this.c = c;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        startY = e.getY();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if(startY - e.getY() > 100) {
            MainPanel mainPanel = null;
            try {
                mainPanel = MainPanel.getInstance();
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
            Home home = (Home)c;
            DefaultSetting.setContentPane(home, mainPanel);
        }
    }
}

