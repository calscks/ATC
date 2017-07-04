import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Coded by Seong Chee Ken on 24/06/2017, 20:46.
 */
public class Main {
    public static void main(String[] args) {
        Weather weather = new Weather();
        Runway runways[] = new Runway[4];
        Runway emergencyRunways[] = new EmergencyRunway[2];
        BlockingQueue<Aircraft> queue = new LinkedBlockingQueue<>(); //unbounded blocking queue

        Gate gates[] = new Gate[8];

        for (int i = 0; i < 8; i++){
            gates[i] = new Gate(i + 1);
        }

        for (int i = 0; i < 4; i++){
            runways[i] = new Runway(i + 101);
        }

        for (int i = 0; i < 2; i++){
            emergencyRunways[i] = new EmergencyRunway(i + 201);
        }

        Airspace airspace = new Airspace(runways, emergencyRunways, gates, weather);

        Producer producer = new Producer(queue, airspace);
        Consumer consumer = new Consumer(queue, airspace);
        new Thread(producer).start();
        new Thread(consumer).start();
    }
}
