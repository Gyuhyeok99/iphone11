package iphone11.apps.drawingBoard;

import iphone11.Home;
import iphone11.etc.TimeCount;
import iphone11.panel.BlackPanel;
import iphone11.panel.MainPanel;
import iphone11.etc.DefaultSetting;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class DrawingBoard extends JPanel {
    private TimeCount timeCount;
    private static DrawingBoard instance;
    public static DrawingBoard getInstance() {
        if (instance == null) {
            instance = new DrawingBoard();
        }
        return instance;
    }

    private List<List<Point>> lines;
    private List<Color> lineColors;
    private final String[] colors = {"Black", "Blue", "Red", "Orange", "Yellow", "White", "Green"};
    private Color[] color = {Color.BLACK, Color.BLUE, Color.RED, Color.ORANGE, Color.YELLOW, Color.WHITE, Color.GREEN};
    private int index;

    private ActionListener actionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            BlackPanel blackPanel = new BlackPanel();
            Home home = (Home) getTopLevelAncestor();
            if (home != null) {
                DefaultSetting.setContentPane(home, blackPanel);
            }
        }
    };
    private DrawingBoard() {
        timeCount = TimeCount.getInstance();
        timeCount.start(actionListener);
        lines = new ArrayList<>();
        lineColors = new ArrayList<>();
        index = 0;

        setLayout(new BorderLayout());
        setOpaque(true);
        setBackground(Color.WHITE);
        createToolBar();

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (e.getY() >= 40) {
                    List<Point> line = new ArrayList<>();
                    line.add(e.getPoint());
                    lines.add(line);
                    lineColors.add(color[index]);
                }
            }
        });

        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (e.getY() >= 40 && !lines.isEmpty()) {
                    List<Point> currentLine = lines.get(lines.size() - 1);
                    currentLine.add(e.getPoint());
                    repaint();
                }
            }
        });
    }
    private void createToolBar() {
        JToolBar toolBar = new JToolBar("DrawingBoard Menu");
        toolBar.setBackground(Color.LIGHT_GRAY);
        JButton[] DrawingBtns = new JButton[5];

        DrawingBtns[0] = new JButton("Home");
        DrawingBtns[1] = new JButton("New");
        DrawingBtns[2] = new JButton("Save");
        DrawingBtns[3] = new JButton("Open");
        DrawingBtns[4] = new JButton("Delete");

        toolBar.setFloatable(false);
        for (int i = 0; i < DrawingBtns.length; i++) {
            DrawingBtns[i].setFont(new Font(DefaultSetting.getInstance().getFontName(), DefaultSetting.getInstance().getFontStyle(), 22));
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
                Home home = (Home) getTopLevelAncestor();
                DefaultSetting.setContentPane(home, mainPanel);
            }
        });

        DrawingBtns[4].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lines.clear();
                lineColors.clear();
                repaint();

            }
        });

        JComboBox<String> colorCombo = new JComboBox<>(colors);
        colorCombo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JComboBox<String> cb = (JComboBox<String>) e.getSource();
                index = cb.getSelectedIndex();
            }
        });
        toolBar.add(colorCombo);
        add(toolBar, BorderLayout.NORTH);
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g1 = (Graphics2D) g;

        for (int i = 0; i < lines.size(); i++) {
            List<Point> line = lines.get(i);
            Color lineColor = lineColors.get(i);
            g1.setColor(lineColor);
            g1.setStroke(new BasicStroke(2));

            if (line.size() > 1) {
                Point startPoint = line.get(0);
                for (int j = 1; j < line.size(); j++) {
                    Point endPoint = line.get(j);
                    g1.drawLine(startPoint.x, startPoint.y, endPoint.x, endPoint.y);
                    startPoint = endPoint;
                }
            }
        }
        timeCount.start(actionListener);
    }
}