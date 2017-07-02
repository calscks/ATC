import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Coded by Seong Chee Ken on 02/07/2017, 02:09.
 */
public class Consumer implements Runnable {
    private BlockingQueue<Aircraft> queue;

    Consumer(BlockingQueue<Aircraft> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Aircraft aircraft = queue.take();
                aircraft.start();
                Thread.sleep(ThreadLocalRandom.current().nextInt(5500, 10000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
