package iphone11.apps.notepad;

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

public class Notepad extends JPanel {
    private static Notepad instance;
    public static Notepad getInstance() throws Exception {
        if (instance == null) {
            instance = new Notepad();
        }
        return instance;
    }
    private int startY;
    private JPanel centerPanel;
    private Notepad() {
        setLayout(new BorderLayout());
        setOpaque(true);
        setBackground(Color.BLACK);

        //north part
        add(new NorthPanelV2(), BorderLayout.NORTH);

        //center part
        centerPanel = new JPanel(null);
        centerPanel.setOpaque(false);
        add(centerPanel, BorderLayout.CENTER);

        JLabel Notes = new JLabel("Notes");
        Notes.setForeground(Color.white);
        Notes.setFont(new Font(DefaultSetting.getInstance().getFontName(), DefaultSetting.getInstance().getFontStyle(), 30));
        Notes.setBounds(15, 30, 200, 30);
        centerPanel.add(Notes);

        JTextField search = new JTextField();
        search.setBounds(20, 80, 360 , 40);
        search.setFont(new Font(DefaultSetting.getInstance().getFontName(), DefaultSetting.getInstance().getFontStyle(), 20));
        search.setForeground(Color.white);
        search.setBackground(Color.DARK_GRAY);
        centerPanel.add(search);

        JButton writing = new JButton(Images.WRITING);
        DefaultSetting.btnSetting(writing);
        writing.setBounds(340, 600, 40, 40);
        centerPanel.add(writing);
        writing.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    NoteWritingPanel noteWritingPanel = null;
                    try {
                        noteWritingPanel = NoteWritingPanel.getInstance();
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }
                    Home home = (Home) getTopLevelAncestor();
                    DefaultSetting.setContentPane(home, noteWritingPanel);
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
                        mainPanel = new MainPanel();
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
        g.setColor(Color.white);
        g.fillRoundRect(130, getHeight() - 8, 140, 8, 10, 10);
    }

    public JPanel getCenterPanel() {
        return centerPanel;
    }
}
