package iphone11.apps.notepad;
import iphone11.Home;
import iphone11.etc.DefaultSetting;
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

public class NoteWritingPanel extends JPanel {
    private TimeCount timeCount;
    private static NoteWritingPanel instance;
    private JTextArea textArea;
    private int startY;
    private Notepad notepad;
    private int titleY;

    public static NoteWritingPanel getInstance() throws Exception {
        if (instance == null) {
            instance = new NoteWritingPanel();
        }
        return instance;
    }

    private NoteWritingPanel() throws Exception {
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

        // North part
        add(new NorthPanelV2(), BorderLayout.NORTH);

        // Center part
        JPanel centerPanel = new JPanel(null);
        centerPanel.setOpaque(false);

        JButton backBtn = new JButton("< Notes");
        JButton writeBtn = new JButton("Done");
        JButton[] NoteWritingBtns = new JButton[2];
        NoteWritingBtns[0] = backBtn;
        NoteWritingBtns[1] = writeBtn;

        for(int i = 0; i < NoteWritingBtns.length; i++) {
            DefaultSetting.btnSetting(NoteWritingBtns[i]);
            centerPanel.add(NoteWritingBtns[i]);
            NoteWritingBtns[i].setForeground(Color.ORANGE);
            NoteWritingBtns[i].setFont(new Font(DefaultSetting.getInstance().getFontName(), DefaultSetting.getInstance().getFontStyle(), 20));
        }
        backBtn.setBounds(-10, 10, 120, 30);
        writeBtn.setBounds(300, 10, 100, 30);

        notepad = Notepad.getInstance();

        backBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Home home = (Home) getTopLevelAncestor();
                DefaultSetting.setContentPane(home, notepad);
            }
        });

        JPanel NotepadCenterPanel = notepad.getCenterPanel();
        titleY = 140;
        writeBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String textContents = textArea.getText();
                String[] textContent = textContents.split("\\n");
                String textTitle = textContent[0];

                JLabel title = new JLabel(textTitle);
                title.setFont(new Font(DefaultSetting.getInstance().getFontName(), DefaultSetting.getInstance().getFontStyle(), 20));
                title.setForeground(Color.white);
                title.setOpaque(true);
                title.setBackground(Color.DARK_GRAY);
                title.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
                title.setBounds(23, titleY, 354, 40 );
                titleY += 45;
                title.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {
                        NoteContentPanel noteContentPanel = new NoteContentPanel();
                        noteContentPanel.setTextArea(textContents);
                        Home home = null;
                        try {
                            home = (Home) Notepad.getInstance().getTopLevelAncestor();
                        } catch (Exception ex) {
                            throw new RuntimeException(ex);
                        }
                        DefaultSetting.setContentPane(home, noteContentPanel);
                    }
                });
                NotepadCenterPanel.add(title);
                textArea.setText("");


            }
        });
        textArea = new JTextArea();
        textArea.setFont(new Font(DefaultSetting.getInstance().getFontName(), DefaultSetting.getInstance().getFontStyle(), 30));
        textArea.setForeground(Color.white);
        textArea.setBounds(20, 50, 380, 700);
        textArea.setOpaque(false);
        centerPanel.add(textArea);

        add(centerPanel, BorderLayout.CENTER);


        textArea.addMouseListener(new MouseAdapter() {
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

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.white);
        g.fillRoundRect(130, getHeight() - 8, 140, 8, 10, 10);
    }
}