package iphone11.etc;

import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import javax.swing.Timer;

public class TimeCount {
    private static TimeCount instance;
    public static TimeCount getInstance() {
        if(instance == null) {
            instance = new TimeCount();
        }
        return instance;
    }
    private int millisecond = 60000;
    private Timer timer;
    private TimeCount(){}

    public void setMillisecond(int millisecond) {
        this.millisecond = millisecond;
    }

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
