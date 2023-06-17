package iphone11.apps.setting;

import iphone11.Home;
import iphone11.apps.calculator.Calculator;
import iphone11.apps.call.Call;
import iphone11.apps.call.Calling;
import iphone11.apps.drawingBoard.DrawingBoard;
import iphone11.apps.gallery.Gallery;
import iphone11.apps.gallery.GalleryImage;
import iphone11.apps.game.ClickBallHome;
import iphone11.apps.notepad.NoteWritingPanel;
import iphone11.apps.notepad.Notepad;
import iphone11.apps.stopwatch.Stopwatch;
import iphone11.etc.DefaultSetting;
import iphone11.etc.TimeCount;
import iphone11.etc.north.NorthPanelV2;
import iphone11.panel.HomePanel;
import iphone11.panel.MainPanel;
import iphone11.panel.QuickSettingPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Setting extends JPanel {
    private DefaultSetting defaultSetting = DefaultSetting.getInstance();
    private Calculator calculator = Calculator.getInstance();
    private Call  call = Call.getInstance();
    private DrawingBoard drawingBoard = DrawingBoard.getInstance();
    private Gallery gallery = Gallery.getInstance();
    private GalleryImage galleryImage = GalleryImage.getInstance();
    private ClickBallHome clickBallHome = ClickBallHome.getInstance();
    private Notepad notepad = Notepad.getInstance();
    private NoteWritingPanel noteWritingPanel = NoteWritingPanel.getInstance();
    private Stopwatch stopwatch = Stopwatch.getInstance();
    private HomePanel homePanel = HomePanel.getInstance();
    private QuickSettingPanel quickSettingPanel = QuickSettingPanel.getInstance();
    private JPanel[] panels = new JPanel[11];
    private TimeCount timeCount = TimeCount.getInstance();
    private int startY;
    private JRadioButton[] fontNameBtns;
    private JRadioButton[] fontStyleBtns;
    private JRadioButton[] screenSaverBtns;
    private static Setting instance;
    public static Setting getInstance() throws Exception {
        if(instance == null) {
            instance = new Setting();
        }
        return instance;
    }

    private Setting() throws Exception {

        setLayout(new BorderLayout());
        setOpaque(true);
        setBackground(Color.black);

        //north part
        add(new NorthPanelV2(), BorderLayout.NORTH);

        //center part
        JPanel centerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 20));
        centerPanel.setOpaque(false);
        add(centerPanel, BorderLayout.CENTER);
        JLabel title = new JLabel("General");
        title.setFont(new Font(DefaultSetting.getInstance().getFontName(), DefaultSetting.getInstance().getFontStyle(), 20));
        title.setForeground(Color.WHITE);
        centerPanel.add(title);

        JPanel about = new JPanel(new GridLayout(5, 2, 10, 20));
        about.setBackground(Color.DARK_GRAY);
        centerPanel.add(about);

        JLabel[] abouts = new JLabel[10];
        abouts[0] = new JLabel("Name");
        abouts[1] = new JLabel("GyuHyeok's iPhone");
        abouts[2] = new JLabel("Software Version");
        abouts[3] = new JLabel("15.6.1");
        abouts[4] = new JLabel("Model Name");
        abouts[5] = new JLabel("iPhone 11");
        abouts[6] = new JLabel("Model Number");
        abouts[7] = new JLabel("MWM22KH/A");
        abouts[8] = new JLabel("Serial Number");
        abouts[9] = new JLabel("DNPCRSGVN73F");

        for(int i = 0; i < abouts.length; i++) {
            abouts[i].setForeground(Color.WHITE);
            abouts[i].setFont(new Font(DefaultSetting.getInstance().getFontName(), DefaultSetting.getInstance().getFontStyle(), 20));
            about.add(abouts[i]);
        }
        for(int i = 1; i < abouts.length; i += 2) {
            abouts[i].setForeground(Color.GRAY);
        }

        JLabel changeFontName = new JLabel("                Change FontName                ");
        changeFontName.setForeground(Color.white);
        changeFontName.setFont(new Font(DefaultSetting.getInstance().getFontName(), DefaultSetting.getInstance().getFontStyle(), 20));
        centerPanel.add(changeFontName);

        ButtonGroup fontNameGroup = new ButtonGroup();

        JRadioButton arial = new JRadioButton("Arial");
        JRadioButton verdana = new JRadioButton("Verdana");
        JRadioButton times_New_Roman = new JRadioButton("times New Roman");

        fontNameBtns = new JRadioButton[3];
        fontNameBtns[0] = arial;
        fontNameBtns[1] = verdana;
        fontNameBtns[2] = times_New_Roman;

        for(int i = 0; i < fontNameBtns.length; i++) {
            fontNameBtns[i].setFont(new Font(DefaultSetting.getInstance().getFontName(), DefaultSetting.getInstance().getFontStyle(), 15));
            fontNameBtns[i].setForeground(Color.WHITE);
            fontNameBtns[i].addItemListener(new FontNameListener());
        }
        fontNameGroup.add(arial);
        fontNameGroup.add(verdana);
        fontNameGroup.add(times_New_Roman);

        centerPanel.add(arial);
        centerPanel.add(verdana);
        centerPanel.add(times_New_Roman);

        JLabel changeFontStyle = new JLabel("               Change FontStyle               ");
        changeFontStyle.setForeground(Color.white);
        changeFontStyle.setFont(new Font(DefaultSetting.getInstance().getFontName(), DefaultSetting.getInstance().getFontStyle(), 20));
        centerPanel.add(changeFontStyle);

        ButtonGroup fontStyleGroup  = new ButtonGroup();

        JRadioButton plain = new JRadioButton("PLAIN");
        JRadioButton bold = new JRadioButton("BOLD");
        JRadioButton italic = new JRadioButton("ITALIC");

        fontStyleBtns = new JRadioButton[3];
        fontStyleBtns[0] = plain;
        fontStyleBtns[1] = bold;
        fontStyleBtns[2] = italic;

        for(int i = 0; i < fontStyleBtns.length; i++) {
            fontStyleBtns[i].setFont(new Font(DefaultSetting.getInstance().getFontName(), DefaultSetting.getInstance().getFontStyle(), 15));
            fontStyleBtns[i].setForeground(Color.WHITE);
            fontStyleBtns[i].addItemListener(new FontStyleListener());
        }
        fontStyleGroup.add(plain);
        fontStyleGroup.add(bold);
        fontStyleGroup.add(italic);

        centerPanel.add(plain);
        centerPanel.add(bold);
        centerPanel.add(italic);

        JLabel screenSaver = new JLabel("             Change screenSaver             ");
        screenSaver.setForeground(Color.white);
        screenSaver.setFont(new Font(DefaultSetting.getInstance().getFontName(), DefaultSetting.getInstance().getFontStyle(), 20));
        centerPanel.add(screenSaver);

        ButtonGroup screenSaverGroup  = new ButtonGroup();

        JRadioButton _15 = new JRadioButton("15 seconds");
        JRadioButton _30 = new JRadioButton("30 seconds");
        JRadioButton _60 = new JRadioButton("60 seconds");

        screenSaverBtns = new JRadioButton[3];
        screenSaverBtns[0] = _15;
        screenSaverBtns[1] = _30;
        screenSaverBtns[2] = _60;

        for(int i = 0; i < screenSaverBtns.length; i++) {
            screenSaverBtns[i].setFont(new Font(DefaultSetting.getInstance().getFontName(), DefaultSetting.getInstance().getFontStyle(), 15));
            screenSaverBtns[i].setForeground(Color.WHITE);
            screenSaverBtns[i].addItemListener(new ScreenSaverListener());
        }
        screenSaverGroup.add(_15);
        screenSaverGroup.add(_30);
        screenSaverGroup.add(_60);

        centerPanel.add(_15);
        centerPanel.add(_30);
        centerPanel.add(_60);
        JPanel sdPanel = new JPanel(new FlowLayout());
        sdPanel.setBackground(Color.DARK_GRAY);
        JButton shutDown = new JButton("Shut Down");
        sdPanel.add(shutDown);
        DefaultSetting.btnSetting(shutDown);
        shutDown.setFont(new Font(DefaultSetting.getInstance().getFontName(), DefaultSetting.getInstance().getFontStyle(), 30));
        shutDown.setForeground(Color.BLUE);
        shutDown.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                System.exit(0);
            }
        });
        centerPanel.add(sdPanel);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                startY = e.getY();
            }
            @Override
            public void mouseReleased(MouseEvent e) {
                MainPanel mainPanel = null;
                try {
                    mainPanel = MainPanel.getInstance();
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
                if (startY - e.getY() > 100) {
                    Home home = (Home)getTopLevelAncestor();
                    DefaultSetting.setContentPane(home, mainPanel);
                }
            }
        });
        panels[0] = calculator;
        panels[1] = call;
        panels[2] = drawingBoard;
        panels[3] = gallery;
        panels[4] = galleryImage;
        panels[5] = clickBallHome;
        panels[6] = notepad;
        panels[7] = noteWritingPanel;
        panels[8] = stopwatch;
        panels[9] = homePanel;
        panels[10] = quickSettingPanel;

    }
    private void panelRepaint() {
        for(int i = 0; i < panels.length; i++) {
            panels[i].repaint();
            panels[i].revalidate();
        }
    }
    private class FontNameListener implements ItemListener{
        @Override
        public void itemStateChanged(ItemEvent e) {
            if(e.getStateChange() == ItemEvent.DESELECTED)
                return;
            if(fontNameBtns[0].isSelected()){
                defaultSetting.setIndex1(0);
                panelRepaint();
            }
            else if(fontNameBtns[1].isSelected()){
                defaultSetting.setIndex1(1);
                panelRepaint();
            }else {
                defaultSetting.setIndex1(2);
                panelRepaint();
            }
        }
    }
    private class FontStyleListener implements ItemListener{
        @Override
        public void itemStateChanged(ItemEvent e) {
            if(e.getStateChange() == ItemEvent.DESELECTED)
                return;
            if(fontStyleBtns[0].isSelected()){
                defaultSetting.setIndex2(0);
                panelRepaint();
            }
            else if(fontNameBtns[1].isSelected()){
                defaultSetting.setIndex2(1);
                panelRepaint();
            }else {
                defaultSetting.setIndex2(2);
                panelRepaint();
            }
        }
    }
    private class ScreenSaverListener implements ItemListener{
        @Override
        public void itemStateChanged(ItemEvent e) {
            if(e.getStateChange() == ItemEvent.DESELECTED)
                return;
            if(fontStyleBtns[0].isSelected()){
                timeCount.setMillisecond(15000);
                panelRepaint();
            }
            else if(fontNameBtns[1].isSelected()){
                timeCount.setMillisecond(30000);
                panelRepaint();
            }else {
                timeCount.setMillisecond(60000);
                panelRepaint();
            }
        }

    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.WHITE);
        g.fillRoundRect(130, 694, 140, 8, 10, 10);
    }
}