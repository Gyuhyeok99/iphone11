package iphone11.apps.gallery;

import iphone11.Home;
import iphone11.etc.DefaultSetting;
import iphone11.etc.north.NorthPanelV2;
import iphone11.panel.MainPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

public class GalleryImage extends JPanel {

    private static GalleryImage instance;

    public static GalleryImage getInstance() {
        if(instance == null) {
            instance = new GalleryImage();
        }
        return instance;
    }
    private Gallery gallery;
    private int index;
    private JPanel centerPanel;
    private int startX;
    private int startY;
    private JButton backBtn;


    public void setIndex(int index) {
        this.index = index;
        Gallery gallery = Gallery.getInstance();
        Vector<Image> imageVector = gallery.getImageVector();
        JLabel selectImage = new JLabel(new ImageIcon(imageVector.get(index)));
        centerPanel.removeAll();
        centerPanel.add(backBtn);
        centerPanel.add(selectImage);
        centerPanel.revalidate();
        centerPanel.repaint();
    }

    private GalleryImage() {

        setLayout(new BorderLayout());
        setOpaque(true);
        setBackground(Color.black);

        //north part
        add(new NorthPanelV2(), BorderLayout.NORTH);


        //center part
        centerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        centerPanel.setOpaque(false);


        backBtn = new JButton("<");
        DefaultSetting.btnSetting(backBtn);
        backBtn.setForeground(Color.blue);
        backBtn.setFont(new Font(DefaultSetting.getInstance().getFontName(), DefaultSetting.getInstance().getFontStyle(), 30));
        backBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                Home home = (Home) getTopLevelAncestor();
                DefaultSetting.setContentPane(home, gallery);
            }
        });
        centerPanel.add(backBtn);

        gallery = Gallery.getInstance();
        Vector<Image> imageVector = gallery.getImageVector();
        JLabel selectImage = new JLabel(new ImageIcon(imageVector.get(index)));
        add(centerPanel, BorderLayout.CENTER);
        centerPanel.add(selectImage);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                startX = e.getX();
                startY = e.getY();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if(startX  - e.getX() > 100){
                    index++;
                    index %= imageVector.size();
                    setIndex(index);
                }
                if(e.getX() - startX > 100) {
                    index--;
                    if(index == -1)
                        index = imageVector.size() - 1;
                    setIndex(index);
                }
                if (startY - e.getY() > 100) {
                    MainPanel mainPanel = null;
                    try {
                        mainPanel = MainPanel.getInstance();
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
        g.fillRoundRect(130, 694, 140, 8, 10, 10);
    }
}
