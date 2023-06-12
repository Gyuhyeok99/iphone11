package iphone11.etc;

import java.awt.event.ActionListener;
import javax.swing.Timer;

public class TimeCount {
    private final int millisecond = 60000;
    private Timer timer;
    public TimeCount(){}
    public void start(ActionListener actionListener) {
        stop();
        timer = new Timer(millisecond, actionListener);
        timer.setRepeats(false);
        timer.start();
    }
    public void stop() {
        if (timer != null) {
            timer.stop();
            timer = null;
        }
        }
    }
