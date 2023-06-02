package iphone11.apps;

import com.sun.tools.javac.Main;
import iphone11.Home;
import iphone11.MainPanel;
import iphone11.PwPanel;
import iphone11.etc.DefaultSetting;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class DrawingBoard extends JPanel {
    private static DrawingBoard instance;
    public static DrawingBoard getInstance() throws Exception {
        if (instance == null) {
            instance = new DrawingBoard();
        }
        return instance;
    }

    private boolean dragging;
    private Point start;
    private DrawingBoard() {
        setLayout(new FlowLayout());
        setOpaque(true);
        setBackground(Color.WHITE);
        createToolBar();
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
               start = e.getPoint();
            }
            @Override
            public void mouseReleased(MouseEvent e) {
            }
        });
        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                    Point cur = e.getPoint();
                    if (cur.y >= 40) {
                        Graphics2D g = (Graphics2D) getGraphics();
                        g.drawLine(start.x, start.y, cur.x, cur.y);
                        start.x = cur.x;
                        start.y = cur.y;
                }

            }
        });
    }

    private void createToolBar() {
        JToolBar toolBar = new JToolBar("DrawingBoard Menu");
        toolBar.setBackground(Color.LIGHT_GRAY);
        JButton[] DrawingBtns = new JButton[5];

        DrawingBtns[0] =  new JButton("Home");
        DrawingBtns[1] =  new JButton("New");
        DrawingBtns[2] =   new JButton("Save");
        DrawingBtns[3] =  new JButton("Open");
        DrawingBtns[4] =  new JButton("Delete");
        toolBar.setFloatable(false);
        for(int i = 0; i < DrawingBtns.length; i++) {
            DrawingBtns[i].setFont(new Font(DefaultSetting.getInstance().getFontName(),DefaultSetting.getInstance().getFontStyle(), 22));
            toolBar.add(DrawingBtns[i]);

        }
        DrawingBtns[0].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainPanel mainPanel = null;
                try {
                    mainPanel = MainPanel.getInstance();
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
                Home home = (Home)getTopLevelAncestor();
                DefaultSetting.setContentPane(home, mainPanel);

            }
        });

        DrawingBtns[4].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getGraphics().clearRect(0, 40, getWidth(), getHeight());
            }
        });

        JComboBox<String> combo = new JComboBox<>();
        combo.addItem("Black");
        combo.addItem("Blue");
        combo.addItem("Red");
        toolBar.add(combo);
        add(toolBar);

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke(2));
    }


}