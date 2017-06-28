import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Coded by Seong Chee Ken on 24/06/2017, 20:46.
 */
@SuppressWarnings("InfiniteLoopStatement")
public class Controller {
    public static void main(String[] args) {
        Timer timer = new Timer();
        Runway runways[] = new Runway[5];

        for (int i = 0; i < 5; i++){
            runways[i] = new Runway(i + 101);
        }

        Airspace airspace = new Airspace(runways);

        ArrayList<Aircraft> aircrafts = new ArrayList<>();
        for (int i = 1; i <= 10; i++){
            aircrafts.add(new Aircraft(airspace, i));
        }

        for (Aircraft aircraft : aircrafts){
            aircraft.start();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
