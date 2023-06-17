package iphone11.etc;
import javax.swing.*;
import java.awt.*;

public class DefaultSetting {
    private static DefaultSetting instance;
    private String[] fontNames;
    private int[] fontStyles;
    private int index1;
    private int index2;

    public void setIndex1(int index1) {
        this.index1 = index1;
    }

    public void setIndex2(int index2) {
        this.index2 = index2;
    }

    private DefaultSetting() {

        fontNames = new String[]{"Arial", "Verdana", "Times New Roman"};
        fontStyles = new int[]{Font.PLAIN, Font.BOLD, Font.ITALIC};
        index1 = 0;
        index2 = 0;
    }
    public static DefaultSetting getInstance() {
        if (instance == null) {
            instance = new DefaultSetting();
        }
        return instance;
    }
    public String getFontName() {
        return fontNames[index1];
    }

    public int getFontStyle() {
        return fontStyles[index2];
    }
    public static void btnSetting(JButton btn) {
        btn.setOpaque(false);
        btn.setContentAreaFilled(false);
        btn.setBorderPainted(false);
    }

    public static void setContentPane(JFrame frame, JPanel panel) {
        frame.setContentPane(panel);
        frame.revalidate();
        frame.repaint();
    }
}

