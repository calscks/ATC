import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Coded by Seong Chee Ken on 22/06/2017, 20:45.
 */
public class Airspace {
    private Runway runways[];
    private boolean full;

    Airspace(Runway runways[]) {
        this.runways = runways;
    }

    void enter(Aircraft aircraft) {
        for (Runway runway : runways) {
            if (runway.isOccupied())
                full = true;
            if (!runway.isOccupied()) {
                full = false;
                break;
            }
        }
        boolean x = false;
        int count = 0;

        while (!x) {
            for (count = 0; count < runways.length; count++) {
                try {
                    x = runways[count].land(aircraft);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (x)
                    break;
            }
        }
        try {
            Thread.sleep(ThreadLocalRandom.current().nextInt(100, 200) * 100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            runways[count].takeOff(aircraft);
        }
    }

    boolean isFull() {
        return full;
    }
}
