package iphone11.etc;
import javax.swing.*;
import java.awt.*;

public class DefaultSetting {
    private static DefaultSetting instance;
    private String[] fontNames;
    private int[] fontStyles;
    private int index;

    private DefaultSetting() {
        // Set default values
        fontNames = new String[]{"Arial", "Verdana", "Times New Roman"};
        fontStyles = new int[]{Font.PLAIN, Font.BOLD, Font.ITALIC};
        index = 0;
    }
    public static DefaultSetting getInstance() {
        if (instance == null) {
            instance = new DefaultSetting();
        }
        return instance;
    }
    public String getFontName() {
        return fontNames[index];
    }

    public int getFontStyle() {
        return fontStyles[index];
    }
    public void changeFont() {
        index++;
        if (index >= fontNames.length) {
            index = 0;
        }
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

