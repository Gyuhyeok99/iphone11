package iphone11.apps.notepad;


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

public class NoteContentPanel extends JPanel {
    private JTextArea textArea;
    private int startY;
    private String content;

    public void setTextArea(String content) {
        this.content = content;
        textArea.setText(content);
    }

    public NoteContentPanel() {
        setLayout(new BorderLayout());
        setOpaque(true);
        setBackground(Color.BLACK);
        //north part
        add(new NorthPanelV2(), BorderLayout.NORTH);

        //center part
        JPanel centerPanel = new JPanel(null);
        centerPanel.setOpaque(false);

        JButton backBtn = new JButton("< Notes");
        DefaultSetting.btnSetting(backBtn);centerPanel.add(backBtn);
        backBtn.setForeground(Color.ORANGE);
        backBtn.setFont(new Font(DefaultSetting.getInstance().getFontName(), DefaultSetting.getInstance().getFontStyle(), 20));
        backBtn.setBounds(-10, 10, 120, 30);


        backBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Notepad notepad = null;
                try {
                    notepad = Notepad.getInstance();
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
                Home home = (Home) getTopLevelAncestor();
                home.remove(NoteContentPanel.this);
                DefaultSetting.setContentPane(home, notepad);
            }
        });

        textArea = new JTextArea();
        textArea.setFont(new Font(DefaultSetting.getInstance().getFontName(), DefaultSetting.getInstance().getFontStyle(), 30));
        textArea.setForeground(Color.white);
        textArea.setBounds(20, 50, 380, 700);
        textArea.setOpaque(false);
        textArea.setEditable(false);
        centerPanel.add(textArea);

        add(centerPanel, BorderLayout.CENTER);


        textArea.addMouseListener(new MouseAdapter() {
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
                    home.remove(NoteContentPanel.this);
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
}
