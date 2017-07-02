import java.util.Timer;
import java.util.TimerTask;

/**
 * Coded by Seong Chee Ken on 01/07/2017, 16:48.
 * Weather is a TimerTask class. It runs a timer in it, every 10 seconds
 */
public class Weather extends TimerTask {
    private volatile int value;

    Weather() {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(this, 2000, 10000);
    }

    @Override
    public void run() {
        value = (int) (Math.random() * 10 + 1);
        System.out.println("Weather: " + value);
    }

    int getValue() {
        return value;
    }
}
