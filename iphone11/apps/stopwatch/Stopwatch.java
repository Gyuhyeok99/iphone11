package iphone11.apps.stopwatch;
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

public class Stopwatch extends JPanel {
    private TimeCount timeCount;
    private static Stopwatch instance;
    private boolean starting = false;
    private final JButton lapBtn;
    private final JButton startBtn;
    private final JLabel time;
    private Timer timer;
    private long startTime;
    private long stopwatch;
    private long stopTime;
    private int startCount = 0;
    private int labCount = 1;
    private int labX;
    private int labY;
    private long labTime;
    private int startY;

    public static Stopwatch getInstance() throws Exception {
        if (instance == null) {
            instance = new Stopwatch();
        }
        return instance;
    }

    private Stopwatch() {
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
        setBackground(Color.BLACK);

        // north part
        add(new NorthPanelV2(), BorderLayout.NORTH);

        // center part
        JPanel centerPanel = new JPanel(null);
        centerPanel.setOpaque(false);
        add(centerPanel, BorderLayout.CENTER);

        time = new JLabel("00:00.00");
        time.setFont(new Font(DefaultSetting.getInstance().getFontName(), DefaultSetting.getInstance().getFontStyle(), 70));
        time.setForeground(Color.WHITE);
        time.setBounds(70, 40, 300, 200);
        centerPanel.add(time);

        JButton[] stopwatchBtns = new JButton[2];
        lapBtn = new JButton(Images.LAP_OFF);
        startBtn = new JButton(Images.START);
        stopwatchBtns[0] = lapBtn;
        stopwatchBtns[1] = startBtn;

        for (int i = 0; i < stopwatchBtns.length; i++) {
            DefaultSetting.btnSetting(stopwatchBtns[i]);
            centerPanel.add(stopwatchBtns[i]);
            stopwatchBtns[i].setBounds(i * 300 + 10, 250, 80, 80);
        }

        startBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!starting) {
                    startBtn.setIcon(Images.STOP_2);
                    lapBtn.setIcon(Images.LAP_ON);
                    starting = true;
                    startTimer();
                } else {
                    startBtn.setIcon(Images.START);
                    lapBtn.setIcon(Images.RESET);
                    starting = false;
                    stopTimer();
                }
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
                        mainPanel = new MainPanel();
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }
                    Home home = (Home)getTopLevelAncestor();
                    DefaultSetting.setContentPane(home, mainPanel);
                }
            }
        });
        labX = 10;
        labY = 360;
        labTime = 0;
        lapBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!starting) {
                    lapBtn.setIcon(Images.LAP_OFF);
                }
                if (startCount >= 1 && !starting) {
                    startCount = 0;
                    time.setText("00:00.00");
                    labY = 360;
                    labTime = 0;
                    labCount = 1;

                    Component[] components = centerPanel.getComponents();
                    for(Component c : components) {
                        if(c instanceof JLabel && c != time){
                            centerPanel.remove(c);
                        }
                    }
                    centerPanel.revalidate();
                    centerPanel.repaint();
                }
                if (starting) {
                    long currentLabTime = stopwatch - labTime;
                    JLabel lab = new JLabel("Lap" + labCount + "                                          " + formatTime((currentLabTime)));
                    lab.setForeground(Color.white);
                    lab.setFont(new Font(DefaultSetting.getInstance().getFontName(), DefaultSetting.getInstance().getFontStyle(), 20));
                    lab.setBounds(labX, labY, 400, 30);
                    centerPanel.add(lab);
                    centerPanel.revalidate();
                    centerPanel.repaint();

                    labY += 40;
                    labCount++;
                    labTime = stopwatch;
                }
            }
        });
    }
    private void startTimer() {
        if (startCount == 0) {
            startTime = System.currentTimeMillis();
        } else {
            startTime = System.currentTimeMillis() - stopwatch;
        }
        startCount++;
        timer = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stopwatch = System.currentTimeMillis() - startTime;
                time.setText(formatTime(stopwatch));
            }
        });
        timer.start();
    }
    private void stopTimer() {
        if (timer != null) {
            stopTime = stopwatch;
            timer.stop();
        }
    }
    private String formatTime(long stopwatch) {
        long minutes = (stopwatch / 60000) % 60;
        long seconds = (stopwatch / 1000) % 60;
        long milliseconds = stopwatch % 1000;
        return String.format("%02d:%02d.%02d", minutes, seconds, milliseconds / 10);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.DARK_GRAY);
        g.fillRoundRect(10, 380, 380, 2, 3, 3);
        g.setColor(Color.white);
        g.fillRoundRect(130, getHeight() - 8, 140, 8, 10, 10);
    }
}
