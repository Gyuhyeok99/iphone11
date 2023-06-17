package iphone11.apps.gallery;

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
import java.time.LocalDateTime;
import java.util.Vector;

public class Gallery extends JPanel {
    private TimeCount timeCount;
    private Vector<Image> imageVector = new Vector<>();
    private static Gallery instance;
    public static Gallery getInstance() {
        if(instance == null) {
            instance = new Gallery();
        }
        return instance;
    }

    private int startY;

    private Gallery() {
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
        setBackground(Color.black);

        //north part
        add(new NorthPanelV2(), BorderLayout.NORTH);

        //center part
        JPanel centerPanel = new JPanel(new FlowLayout());
        centerPanel.setOpaque(false);
        add(centerPanel, BorderLayout.CENTER);


        LocalDateTime now = LocalDateTime.now();
        int year = now.getYear();
        int month = now.getMonthValue();
        String[] months = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
        String nowMonth = months[month - 1];
        String day = Integer.toString(now.getDayOfMonth());
        String days = "June 14 - " + nowMonth + " " + day + ", " + " " + year;

        JLabel dayMonth = new JLabel(days);
        dayMonth.setForeground(Color.WHITE);
        dayMonth.setFont(new Font(DefaultSetting.getInstance().getFontName(), DefaultSetting.getInstance().getFontStyle(), 20));
        dayMonth.setPreferredSize(new Dimension(400, 120));
        centerPanel.add(dayMonth);

        Image[] imgs = new Image[20];

        imgs[0] = Images.IU_1.getImage();imgs[1] = Images.IU_2.getImage();imgs[2] = Images.IU_3.getImage();
        imgs[3] = Images.IU_4.getImage();imgs[4] = Images.IU_5.getImage();imgs[5] = Images.IU_6.getImage();
        imgs[6] = Images.IU_7.getImage();imgs[7] = Images.IU_8.getImage();imgs[8] = Images.IU_9.getImage();
        imgs[9] = Images.IU_10.getImage();imgs[10] = Images.IU_11.getImage();imgs[11] = Images.IU_12.getImage();
        imgs[12] = Images.IU_13.getImage();imgs[13] = Images.IU_14.getImage();imgs[14] = Images.IU_15.getImage();
        imgs[15] = Images.IU_16.getImage();imgs[16] = Images.IU_17.getImage();imgs[17] = Images.IU_18.getImage();
        imgs[18] = Images.IU_19.getImage();imgs[19] = Images.IU_20.getImage();

        for(int i = 0; i < imgs.length; i++) {
            JLabel iuImg = new JLabel(new ImageIcon(imgs[i].getScaledInstance(95, 95, Image.SCALE_DEFAULT)));
            centerPanel.add(iuImg);
            imageVector.add(imgs[i]);
            final int index = i;
            iuImg.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    GalleryImage galleryImage = GalleryImage.getInstance();
                    galleryImage.setIndex(index);
                    Home home = (Home)getTopLevelAncestor();
                    DefaultSetting.setContentPane(home, galleryImage);
                }
            });
        }
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
    public Vector<Image> getImageVector() {
        return imageVector;
    }
}
