import java.util.concurrent.BlockingQueue;

/**
 * Coded by Seong Chee Ken on 02/07/2017, 01:56.
 */
public class Producer implements Runnable {
    private BlockingQueue<Aircraft> queue;
    private Airspace airspace;

    Producer(BlockingQueue<Aircraft> queue, Airspace airspace) {
        this.queue = queue;
        this.airspace = airspace;
    }

    @Override
    public void run() {
        int count = 0;
        while (true) {
            try {
                queue.put(new Aircraft(airspace, count + 1));
                count++;
                Thread.sleep(7500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
